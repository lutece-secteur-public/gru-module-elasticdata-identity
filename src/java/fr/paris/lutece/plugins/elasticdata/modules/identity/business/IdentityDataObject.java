package fr.paris.lutece.plugins.elasticdata.modules.identity.business;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import fr.paris.lutece.plugins.elasticdata.business.AbstractDataObject;

public class IdentityDataObject extends AbstractDataObject
{
    
	
	public IdentityDataObject(int nIdIdentity, Date dateLastUpdate, int nIdAttribute, String strIndic) {
		super();
		this._nIdIdentity = nIdIdentity;
		this._dateLastUpdate = dateLastUpdate;
		this._nIdAttribute = nIdAttribute;
		this._strIndic = strIndic;
	}
	private int _nIdIdentity;
    private Date _dateLastUpdate;
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd" )
    public Date getLastUpdate( )
    {
        return _dateLastUpdate;
    }
    
    
    public void setLastUpdate( Date _dateLastUpdate )
    {
        this._dateLastUpdate = _dateLastUpdate;
    }
    private int _nIdAttribute;
    private String _strIndic;
	
    
    public String getIndic() {
		return _strIndic;
	}
	public void setIndic(String _strIndic) {
		this._strIndic = _strIndic;
	}
	public int getIdIdentity() {
		return _nIdIdentity;
	}
	public void setIdIdentity(int _nIdIdentity) {
		this._nIdIdentity = _nIdIdentity;
	}
	public int getIdAttribute() {
		return _nIdAttribute;
	}
	public void setIdAttribute(int _nIdAttribute) {
		this._nIdAttribute = _nIdAttribute;
	}
    
    
   

   
}
