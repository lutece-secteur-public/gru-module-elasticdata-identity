package fr.paris.lutece.plugins.elasticdata.modules.identity.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import fr.paris.lutece.plugins.elasticdata.business.DataObject;

public class IdentityDataObjectIterator implements Iterator<DataObject> {

	
	private  static final int NB_TMP_DATA_OBJ_LOAD=1000;
	private Collection<IdentityDataObject> _listIdentitiesToExport;
	private Iterator<IdentityDataObject> _iterator;
	private LinkedHashMap<Integer,IdentityDataObject> _mapTmpIdentityDataObject;

	public IdentityDataObjectIterator(Collection<IdentityDataObject> listIdentitiesToExport) {
		super();
		this._listIdentitiesToExport = listIdentitiesToExport;
		this._iterator=this._listIdentitiesToExport.iterator( );
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub

	    return (_mapTmpIdentityDataObject!=null && _mapTmpIdentityDataObject.size()>0)||_iterator.hasNext( );
		
		
	}

	@Override
	public synchronized DataObject next() {

		IdentityDataObject dataObject = null;
		

			if (_mapTmpIdentityDataObject==null || _mapTmpIdentityDataObject.size()==0) {
				loadNextTmpIdentityDataObject();

			}
			if(_mapTmpIdentityDataObject != null) {
				
				Map.Entry<Integer,IdentityDataObject> mapEntry=null;
				if (_mapTmpIdentityDataObject.size() > 0) {
					
				     mapEntry=_mapTmpIdentityDataObject.entrySet().iterator().next();
				     dataObject=mapEntry.getValue();
					_mapTmpIdentityDataObject.remove(mapEntry.getKey());
				}
			}

		

		return dataObject;

	}

	private synchronized void loadNextTmpIdentityDataObject() {
		
	    Collection<IdentityDataObject> lisIdTmpToLoad=null;
		if(_iterator.hasNext( ))
		{
			
			    lisIdTmpToLoad=new ArrayList<>( );
			    while(_iterator.hasNext( ))
                {
                    lisIdTmpToLoad.add(new IdentityDataObject( _iterator.next( ).getIdIdentity(),_iterator.next().getDateCreation()));
                    if(lisIdTmpToLoad.size( )==NB_TMP_DATA_OBJ_LOAD)
                    {
                        break;
                        
                    }
                }
		    
		}
			
			
			List<IdentityAttributeDataObject> attributes= IdentityAttributeHome.selectAttributesByIdentities(lisIdTmpToLoad);

			System.out.println( "attribut size "+attributes.size( ));
			_mapTmpIdentityDataObject=new LinkedHashMap<Integer,IdentityDataObject> ();
			for(IdentityDataObject identity:lisIdTmpToLoad)
			{
				_mapTmpIdentityDataObject.put(identity.getIdIdentity(), identity);
				
			}
			
			for(IdentityAttributeDataObject attribute:attributes)
			{
				
				IdentityDataObject identDataObject=_mapTmpIdentityDataObject.get(attribute.getIdIdentity());
				if(identDataObject.getLastUpdate()==null || identDataObject.getLastUpdate().before(attribute.getLastUpdateDate()))
				{
					identDataObject.setLastUpdate(attribute.getLastUpdateDate());
				}
				//anonymize data attribute
				anonymiseDataAttribute(attribute);
				identDataObject.getListAttribute().add(attribute);
		}
		
	}
	
	
	private void anonymiseDataAttribute (IdentityAttributeDataObject attribute)
	{
		
		if(StringUtils.isBlank(attribute.getValue()))
		{
			
			attribute.setValue("BLK");
		}
		else
		{
			attribute.setValue("NBLK");
		
		}
	}
	
	
	 
	

}
