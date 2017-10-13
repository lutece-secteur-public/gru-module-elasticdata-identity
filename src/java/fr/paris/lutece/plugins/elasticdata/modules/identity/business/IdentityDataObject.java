package fr.paris.lutece.plugins.elasticdata.modules.identity.business;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import fr.paris.lutece.plugins.elasticdata.business.AbstractDataObject;

public class IdentityDataObject  extends AbstractDataObject {
	
	

	private int _nIdIdentity;
	private Date _dateLastUpdate;
	private Date _dateCreation;
	
	private List<IdentityAttributeDataObject> _listAttribute; 
	  

		public IdentityDataObject(int nIdIdentity,Date dateCreation)
		{
			this._nIdIdentity=nIdIdentity;
			this._listAttribute=new ArrayList<>();
			this._dateCreation=dateCreation;
		}
	  
	  public int getIdIdentity() {
			return _nIdIdentity;
		}
		public void setIdIdentity(int _nIdIdentity) {
			this._nIdIdentity = _nIdIdentity;
		}
	   @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm" )
	    public Date getLastUpdate( )
	    {
	        return _dateLastUpdate;
	    }
	    
	    
	   public void setLastUpdate( Date _dateLastUpdate )
	    {
	        this._dateLastUpdate = _dateLastUpdate;
	    }
	public List<IdentityAttributeDataObject> getListAttribute() {
		return _listAttribute;
	}
	public void setListAttribute(List<IdentityAttributeDataObject> _listAttribute) {
		this._listAttribute = _listAttribute;
	}
	@JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm" )
	public Date getDateCreation() {
		return _dateCreation;
	}

	public void setDateCreation(Date _dateCreation) {
		this._dateCreation = _dateCreation;
	}	
	        
}
