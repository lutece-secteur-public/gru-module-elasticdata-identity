/*
 * Copyright (c) 2002-2022, City of Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.elasticdata.modules.identity.business;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonFormat;

import fr.paris.lutece.plugins.elasticdata.business.AbstractDataObject;

public class IdentityDataObject extends AbstractDataObject
{

    private Date _dateLastUpdate;
    private Date _dateCreation;
    private String _strUserAccessCode;
    private Map<String, String> _mapAttributes;

    public IdentityDataObject( int nIdIdentity, String strCustomerId, Date dateCreation )
    {
        this.setId( String.valueOf( nIdIdentity ) );
        this._mapAttributes = new HashMap<>( );
        this._dateCreation = dateCreation;
        this._strUserAccessCode = strCustomerId;
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

    public Map<String, String> getListAttribute( )
    {
        return _mapAttributes;
    }

    public void setListAttribute( Map<String, String> _mapAttributes )
    {
        this._mapAttributes = _mapAttributes;
    }

    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm" )
    public Date getDateCreation( )
    {
        return _dateCreation;
    }

    public void setDateCreation( Date _dateCreation )
    {
        this._dateCreation = _dateCreation;
    }

    public String getUserAccessCode( )
    {
        return _strUserAccessCode;
    }

    public void setUserAccessCode( String _strUserAccessCode )
    {
        this._strUserAccessCode = _strUserAccessCode;
    }

}
