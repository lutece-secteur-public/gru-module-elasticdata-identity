package fr.paris.lutece.plugins.elasticdata.modules.identity.business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import fr.paris.lutece.plugins.elasticdata.business.DataObject;

public class IdentityDataObjectIterator implements Iterator<DataObject> {

	
	private  static final int NB_TMP_DATA_OBJ_LOAD=1000;
	private Collection<Integer> _listIdentitiesToExport;
	private Iterator<Integer> _iterator;
	private List<IdentityDataObject> _listTmpIdentityDataObject;

	public IdentityDataObjectIterator(Collection<Integer> listIdentitiesToExport) {
		super();
		this._listIdentitiesToExport = listIdentitiesToExport;
		this._iterator=this._listIdentitiesToExport.iterator( );
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub

	    return _iterator.hasNext( );
		
		
	}

	@Override
	public synchronized DataObject next() {

		IdentityDataObject dataObject = null;
		

			if (CollectionUtils.isEmpty(_listTmpIdentityDataObject)) {
				loadNextTmpIdentityDataObject();

			}
			if (_listTmpIdentityDataObject != null) {
				// TODO Auto-generated method stub

				if (_listTmpIdentityDataObject.size() > 0) {
					dataObject = _listTmpIdentityDataObject.get(0);
					_listTmpIdentityDataObject.remove(0);
				}
			}

		

		return dataObject;

	}

	private synchronized void loadNextTmpIdentityDataObject() {
		
	    Collection<Integer> lisIdTmpToLoad=null;
		if(_iterator.hasNext( ))
		{
			
			    lisIdTmpToLoad=new ArrayList<>( );
			    while(_iterator.hasNext( ))
                {
                    lisIdTmpToLoad.add( _iterator.next( ) );
                    if(lisIdTmpToLoad.size( )==NB_TMP_DATA_OBJ_LOAD)
                    {
                        break;
                        
                    }
                }
		    
		}
			
			
			List<IdentityAttribute> attributes= IdentityAttributeHome.selectAttributesByIdentities(lisIdTmpToLoad);

			System.out.println( "attribut size "+attributes.size( ));
			_listTmpIdentityDataObject=new ArrayList<>();
			for(IdentityAttribute attribute:attributes)
			{
				
				_listTmpIdentityDataObject.add(new IdentityDataObject(attribute.getIdIdentity(), attribute.getLastUpdateDate(), attribute.getIdAttribute(), getIndic(attribute.getValue())));
				
			}
		
	}
	
	
	private String getIndic(String strVAlue)
	{
		
		String strIndic="NBLK";
		if(StringUtils.isBlank(strVAlue))
		{
			
			strIndic="BLK";
		}
		return strIndic;
		
		
	}
	
	
	 
	

}
