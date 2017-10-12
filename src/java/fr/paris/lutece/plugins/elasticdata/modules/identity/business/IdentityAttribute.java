/*
 * Copyright (c) 2002-2016, Mairie de Paris
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

import fr.paris.lutece.portal.business.file.File;

import org.hibernate.validator.constraints.NotEmpty;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.validation.constraints.Size;

/**
 * This is the business class for the object IdentityAttribute
 */
public class IdentityAttribute implements Serializable {
	private static final long serialVersionUID = 1L;
	private int _nIdIdentity;
	private int _nIdAttribute;
	private String _strValue;
	private int _nIdCertificate;
	private File _file;
	private int _nIdCertifier;

	private Timestamp _dateLastUpdate;

	/**
	 * Returns the IdIdentity
	 *
	 * @return The IdIdentity
	 */
	public int getIdIdentity() {
		return _nIdIdentity;
	}

	/**
	 * Sets the IdIdentity
	 *
	 * @param nIdIdentity
	 *            The IdIdentity
	 */
	public void setIdIdentity(int nIdIdentity) {
		_nIdIdentity = nIdIdentity;
	}

	/**
	 * Returns the value
	 *
	 * @return The value
	 */
	public String getValue() {
		return _strValue;
	}

	/**
	 * Sets the value
	 *
	 * @param strValue
	 *            The value
	 */
	public void setValue(String strValue) {
		_strValue = strValue;
	}

	/**
	 * Returns the IdCertification
	 *
	 * @return The IdCertification
	 */
	public int getIdCertificate() {
		return _nIdCertificate;
	}

	/**
	 * Sets the IdCertification
	 *
	 * @param nIdCertificate
	 *            The IdCertification
	 */
	public void setIdCertificate(int nIdCertificate) {
		_nIdCertificate = nIdCertificate;
	}

	/**
	 * @return the file
	 */
	public File getFile() {
		return _file;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(File file) {
		this._file = file;
	}

	/**
	 * @return the _dateLastUpdate
	 */
	public Timestamp getLastUpdateDate() {
		return _dateLastUpdate;
	}

	/**
	 * @param dateLastUpdate
	 *            the _dateLastUpdate to set
	 */
	public void setLastUpdateDate(Timestamp dateLastUpdate) {
		this._dateLastUpdate = dateLastUpdate;
	}

	public int getIdAttribute() {
		return _nIdAttribute;
	}

	public void setIdAttribute(int _nIdAttribute) {
		this._nIdAttribute = _nIdAttribute;
	}

	public int getIdCertifier() {
		return _nIdCertifier;
	}

	public void setIdCertifier(int nIdCertifier) {
		this._nIdCertifier = nIdCertifier;
	}
}
