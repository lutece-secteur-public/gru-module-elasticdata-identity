package fr.paris.lutece.plugins.elasticdata.modules.identity.business;

import java.util.Collection;
import java.util.Iterator;

import fr.paris.lutece.plugins.elasticdata.business.AbstractDataSource;
import fr.paris.lutece.plugins.elasticdata.business.DataObject;
import fr.paris.lutece.plugins.elasticdata.business.DataSource;

public class IdentityDataSource extends AbstractDataSource implements DataSource
{

	   @Override
	    public Collection<DataObject> getDataObjects( )
	    {

	        
	        return null;
	    }
    
    
    
    @Override
    public  Iterator<DataObject> getDataObjectsIterator()
    {
    	
    		return new IdentityDataObjectIterator(IdentityAttributeHome.selectIdIdentitiesToExport());
    }
    
    
    

}
