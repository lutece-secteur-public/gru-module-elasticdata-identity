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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.collections.CollectionUtils;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;

/**
 * This class provides Data Access methods for IdentityAttribute objects
 */
public final class IdentityDataObjectDAO implements IIdentityDataObjectDAO
{
    // Constants
    private static final String SQL_QUERY_SELECT_ID_IDENTITY_TO_EXPORT = "SELECT id_identity FROM identitystore_identity";
    private static final String SQL_QUERY_SELECT_IDENTITY_TO_EXPORT = "SELECT id_identity, customer_id, date_create, date_delete FROM identitystore_identity";
    private static final String SQL_QUERY_SELECT_ATTRIBUTE = "SELECT id_attribute, key_name FROM identitystore_attribute";
    private static final String SQL_QUERY_SELECT_IDENTITY_ATTRIBUTE = "SELECT id_identity, id_attribute, attribute_value, lastupdate_date,certifier_code,certificate_date,certificate_level,expiration_date FROM identitystore_identity_attribute LEFT JOIN identitystore_attribute_certificate on(id_certification=id_attribute_certificate) ";
    private static final String SQL_QUERY_SELECT_FILTER = "WHERE id_identity  in ( ";
    private static final String SQL_QUERY_SELECT_IDENTITY_FILTER = "WHERE id_identity in (?";
    private static final String SQL_CLOSE_PARENTHESIS = " ) ";
    private static final String SQL_ADITIONAL_PARAMETER = ",?";

    /**
     * {@inheritDoc }
     */
    @Override
    public List<IdentityAttributeDataObject> selectAttributes( Collection<IdentityDataObject> lIdIdentity, Plugin plugin )
    {
        List<IdentityAttributeDataObject> ListIdentityAttributes = new ArrayList<>( );
        StringBuffer strQuery = new StringBuffer( SQL_QUERY_SELECT_IDENTITY_ATTRIBUTE );
        strQuery.append( SQL_QUERY_SELECT_FILTER );
        if ( !CollectionUtils.isEmpty( lIdIdentity ) )
            for ( IdentityDataObject id : lIdIdentity )
            {
                strQuery.append( "?," );
            }
        strQuery.deleteCharAt( strQuery.length( ) - 1 );
        strQuery.append( ")" );
        DAOUtil daoUtil = new DAOUtil( strQuery.toString( ), plugin );
        int ncpt = 1;
        for ( IdentityDataObject id : lIdIdentity )
        {
            daoUtil.setInt( ncpt++, Integer.valueOf( id.getId( ) ) );
        }
        daoUtil.executeQuery( );
        int nIndex;
        while ( daoUtil.next( ) )
        {
            IdentityAttributeDataObject identityAttribute = new IdentityAttributeDataObject( );
            nIndex = 1;
            identityAttribute.setIdIdentity( daoUtil.getInt( nIndex++ ) );
            identityAttribute.setIdAttribute( daoUtil.getInt( nIndex++ ) );
            identityAttribute.setValue( daoUtil.getString( nIndex++ ) );
            identityAttribute.setLastUpdateDate( daoUtil.getTimestamp( nIndex++ ) );
            identityAttribute.setCertifierCode( daoUtil.getString( nIndex++ ) );
            identityAttribute.setCertificateDate( daoUtil.getTimestamp( nIndex++ ) );
            identityAttribute.setCertificateLevel( daoUtil.getString( nIndex++ ) );
            identityAttribute.setCertificateExpirationDate( daoUtil.getTimestamp( nIndex++ ) );
            ListIdentityAttributes.add( identityAttribute );
        }
        daoUtil.free( );
        return ListIdentityAttributes;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<IdentityDataObject> selectAllIdentity( List<Integer> listIdIdentity, Plugin plugin )
    {
        List<IdentityDataObject> listIdentity = new ArrayList<>( );
        int nlistIdIdentitySize = listIdIdentity.size( );
        if ( nlistIdIdentitySize > 0 )
        {
            StringBuilder sbSQL = new StringBuilder( SQL_QUERY_SELECT_IDENTITY_TO_EXPORT + " " + SQL_QUERY_SELECT_IDENTITY_FILTER );
            for ( int i = 1; i < nlistIdIdentitySize; i++ )
            {
                sbSQL.append( SQL_ADITIONAL_PARAMETER );
            }
            sbSQL.append( SQL_CLOSE_PARENTHESIS );
            try ( DAOUtil daoUtil = new DAOUtil( sbSQL.toString( ), plugin ) )
            {
                for ( int i = 0; i < nlistIdIdentitySize; i++ )
                {
                    daoUtil.setInt( i + 1, listIdIdentity.get( i ) );
                }
                daoUtil.executeQuery( );
                while ( daoUtil.next( ) )
                {
                    listIdentity
                            .add( new IdentityDataObject( daoUtil.getInt( 1 ), daoUtil.getString( 2 ), daoUtil.getTimestamp( 3 ), daoUtil.getTimestamp( 4 ) ) );
                }
            }
        }
        return listIdentity;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public List<Integer> selectAllIdIdentity( Plugin plugin )
    {
        List<Integer> listIdentity = new ArrayList<>( );
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_ID_IDENTITY_TO_EXPORT, plugin ) )
        {
            daoUtil.executeQuery( );
            while ( daoUtil.next( ) )
            {
                listIdentity.add( daoUtil.getInt( 1 ) );
            }
        }
        return listIdentity;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public Map<Integer, String> selectAllAttributes( Plugin plugin )
    {
        Map<Integer, String> mapAttribute = new HashMap<>( );
        try ( DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_ATTRIBUTE, plugin ) )
        {
            daoUtil.executeQuery( );
            while ( daoUtil.next( ) )
            {
                mapAttribute.put( daoUtil.getInt( 1 ), daoUtil.getString( 2 ) );
            }
        }
        return mapAttribute;
    }
}
