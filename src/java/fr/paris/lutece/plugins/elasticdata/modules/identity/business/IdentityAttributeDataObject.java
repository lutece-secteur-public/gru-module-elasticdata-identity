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

import java.io.Serializable;
import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * This is the business class for the object IdentityAttribute
 */
public class IdentityAttributeDataObject implements Serializable
{
    private static final long serialVersionUID = 1L;
    private int _nIdIdentity;
    private int _nIdAttribute;
    private String _strValue;
    private int _nIdCertificate;
    private Timestamp _dateLastUpdate;
    private String _strCertifierCode;
    private Timestamp _certificateDate;
    private String _strCertificateLevel;
    private Timestamp _certificateExpirationDate;

    /**
     * Returns the IdIdentity
     *
     * @return The IdIdentity
     */
    public int getIdIdentity( )
    {
        return _nIdIdentity;
    }

    /**
     * Sets the IdIdentity
     *
     * @param nIdIdentity
     *            The IdIdentity
     */
    public void setIdIdentity( int nIdIdentity )
    {
        _nIdIdentity = nIdIdentity;
    }

    /**
     * Returns the value
     *
     * @return The value
     */
    public String getValue( )
    {
        return _strValue;
    }

    /**
     * Sets the value
     *
     * @param strValue
     *            The value
     */
    public void setValue( String strValue )
    {
        _strValue = strValue;
    }

    /**
     * Returns the IdCertification
     *
     * @return The IdCertification
     */
    public int getIdCertificate( )
    {
        return _nIdCertificate;
    }

    /**
     * Sets the IdCertification
     *
     * @param nIdCertificate
     *            The IdCertification
     */
    public void setIdCertificate( int nIdCertificate )
    {
        _nIdCertificate = nIdCertificate;
    }

    /**
     * @return the _dateLastUpdate
     */
    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm" )
    public Timestamp getLastUpdateDate( )
    {
        return _dateLastUpdate;
    }

    /**
     * @param dateLastUpdate
     *            the _dateLastUpdate to set
     */
    public void setLastUpdateDate( Timestamp dateLastUpdate )
    {
        this._dateLastUpdate = dateLastUpdate;
    }

    public int getIdAttribute( )
    {
        return _nIdAttribute;
    }

    public void setIdAttribute( int _nIdAttribute )
    {
        this._nIdAttribute = _nIdAttribute;
    }

    public String getCertifierCode( )
    {
        return _strCertifierCode;
    }

    public void setCertifierCode( String _strCertifierCode )
    {
        this._strCertifierCode = _strCertifierCode;
    }

    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm" )
    public Timestamp getCertificateDate( )
    {
        return _certificateDate;
    }

    public void setCertificateDate( Timestamp _certificateDate )
    {
        this._certificateDate = _certificateDate;
    }

    @JsonFormat( shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm" )
    public Timestamp getCertificateExpirationDate( )
    {
        return _certificateExpirationDate;
    }

    public void setCertificateExpirationDate( Timestamp _certificateExpirationDate )
    {
        this._certificateExpirationDate = _certificateExpirationDate;
    }

    public String getCertificateLevel( )
    {
        return _strCertificateLevel;
    }

    public void setCertificateLevel( String _strCertificateLevel )
    {
        this._strCertificateLevel = _strCertificateLevel;
    }
}
