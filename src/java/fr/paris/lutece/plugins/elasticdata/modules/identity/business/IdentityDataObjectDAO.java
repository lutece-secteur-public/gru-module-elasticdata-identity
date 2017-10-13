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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

/**
 * This class provides Data Access methods for IdentityAttribute objects
 */
public final class IdentityDataObjectDAO implements IIdentityDataObjectDAO
{
    // Constants
	private static final String SQL_QUERY_SELECT_IDENTITY_TO_EXPORT = "SELECT id_identity,date_create FROM identitystore_identity";
	private static final String SQL_QUERY_SELECT_IDENTITY_ATTRIBUTE = "SELECT id_identity, id_attribute, attribute_value, lastupdate_date,certifier_code,certificate_date,certificate_level,expiration_date FROM identitystore_identity_attribute LEFT JOIN identitystore_attribute_certificate on(id_certification=id_attribute_certificate) ";
	private static final String SQL_QUERY_SELECT_FILTER = "WHERE id_identity  in ( ";
	

    /**
     * {@inheritDoc }
     */
    @Override
    public List<IdentityAttributeDataObject> selectAttributes(Collection<IdentityDataObject> lIdIdentity, Plugin plugin )
    {
        
    	
    	List<IdentityAttributeDataObject> ListIdentityAttributes=new ArrayList<>();
    	
    	
    	StringBuffer strQuery=new StringBuffer( SQL_QUERY_SELECT_IDENTITY_ATTRIBUTE );
    	strQuery.append( SQL_QUERY_SELECT_FILTER );
    	
    	if(!CollectionUtils.isEmpty( lIdIdentity ))
    	
    	for(IdentityDataObject id:lIdIdentity)
    	{
    	    strQuery.append( "?," );
    	 }
    	strQuery.deleteCharAt( strQuery.length( )-1 );
    	strQuery.append( ")" );
    	DAOUtil daoUtil = new DAOUtil( strQuery.toString( ), plugin );
    	int ncpt=1;
    	for(IdentityDataObject id:lIdIdentity)
        {
    	    daoUtil.setInt( ncpt++, id.getIdIdentity() );
         }
        daoUtil.executeQuery( );
        int nIndex;
        while ( daoUtil.next( ) )
        {
            IdentityAttributeDataObject identityAttribute = new IdentityAttributeDataObject( );
            nIndex = 1;
            identityAttribute.setIdIdentity( daoUtil.getInt(nIndex++) );
            identityAttribute.setIdAttribute(daoUtil.getInt(nIndex++) );
            identityAttribute.setValue( daoUtil.getString( nIndex++ ) );
            identityAttribute.setLastUpdateDate( daoUtil.getTimestamp( nIndex++ ) );
            identityAttribute.setCertifierCode( daoUtil.getString( nIndex++ ) );
            identityAttribute.setCertificateDate(daoUtil.getObject( nIndex++ )!=null ?daoUtil.getTimestamp( nIndex ):null);
            identityAttribute.setCertificateLevel(daoUtil.getString( nIndex++ ));
            identityAttribute.setCertificateExpirationDate(daoUtil.getObject( nIndex++ )!=null ?daoUtil.getTimestamp( nIndex ):null);
            ListIdentityAttributes.add(identityAttribute);

        }
        
        daoUtil.free( );

        

        return ListIdentityAttributes;
    }
    

    /**
     * {@inheritDoc }
     */
    @Override
    public Collection<IdentityDataObject> selectAllIdIdentity( Plugin plugin )
    {
        
       Collection<IdentityDataObject> listIdentity=new HashSet<IdentityDataObject>();
    	//SELECT id_identity, id_attribute, attribute_value, id_certification, id_file, lastupdate_date 
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_IDENTITY_TO_EXPORT, plugin );
        daoUtil.executeQuery( );

        while ( daoUtil.next( ) )
        {
           listIdentity.add(new IdentityDataObject(daoUtil.getInt(1),daoUtil.getTimestamp(2)));
        }
        
        daoUtil.free( );

        

        return listIdentity;
    }

  
}
