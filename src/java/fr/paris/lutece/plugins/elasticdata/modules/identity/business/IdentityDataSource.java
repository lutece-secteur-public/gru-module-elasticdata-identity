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

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import fr.paris.lutece.plugins.elasticdata.business.AbstractDataSource;
import fr.paris.lutece.plugins.elasticdata.business.DataObject;
import fr.paris.lutece.portal.service.util.AppPropertiesService;

public class IdentityDataSource extends AbstractDataSource
{
    Map<Integer, String> _mapAttributes = null;
    public static final String PROPERTY_SELECT_IN_SIZE = "elasticdata-identity.select_in_size";
    public static final int SQL_MAX_SELECT_IN = AppPropertiesService.getPropertyInt( PROPERTY_SELECT_IN_SIZE, 80 );

    @Override
    public List<String> getIdDataObjects( )
    {
        return IdentityAttributeHome.selectIdIdentitiesToExport( ).stream( ).map( s -> String.valueOf( s ) ).collect( Collectors.toList( ) );
    }

    @Override
     public synchronized List<DataObject> getDataObjects( List<String> listIdIdentity )
    {
        List<DataObject> collResult = new ArrayList<>( );
        getAttributesList( );

        AtomicInteger counter = new AtomicInteger( );
        // split for db performance
        Map<Integer, List<String>> listIdDataObjectSplited = listIdIdentity.stream( )
                .collect( Collectors.groupingBy( it -> counter.getAndIncrement( ) / SQL_MAX_SELECT_IN ) );

        listIdDataObjectSplited.entrySet( ).stream( ).forEach( e -> {

            List<IdentityDataObject> listIdentityDataObject = IdentityAttributeHome.selectIdentitiesToExport( e.getValue( ) );
            List<IdentityAttributeDataObject> attributes = IdentityAttributeHome.selectAttributesByIdentities( listIdentityDataObject );

            for ( IdentityDataObject identity : listIdentityDataObject )
            {
                List<IdentityAttributeDataObject> listIdentityAttributes = attributes.stream( )
                        .filter( at -> at.getIdIdentity( ) == Integer.valueOf( identity.getId( ) ) )
                        .sorted( Comparator.comparing( IdentityAttributeDataObject::getLastUpdateDate, Comparator.nullsLast( Comparator.reverseOrder( ) ) ) )
                        .collect( Collectors.toList( ) );
                if ( listIdentityAttributes.size( ) > 0 )
                {
                    Timestamp lastUpdateAttribute = listIdentityAttributes.get( 0 ).getLastUpdateDate( );
                    if ( identity.getLastUpdate( ) == null || identity.getLastUpdate( ).before( lastUpdateAttribute ) )
                    {
                        identity.setLastUpdate( lastUpdateAttribute );
                    }
                }
                identity.getListAttribute( ).putAll( getAttributes( listIdentityAttributes ) );
            }
            collResult.addAll( listIdentityDataObject );
        } );

        return collResult;
    }

    private Map<String, IdentityAttributeDataObject> getAttributes( List<IdentityAttributeDataObject> attributes )
    {
        Map<String, IdentityAttributeDataObject> mapAttributes = new HashMap<>( );
        for ( IdentityAttributeDataObject attribute : attributes )
        {
            String strAttributeName = _mapAttributes.get( attribute.getIdAttribute( ) );
            mapAttributes.put( strAttributeName, attribute );
        }
        return mapAttributes;
    }

    private void getAttributesList( )
    {
        if ( _mapAttributes == null )
        {
            _mapAttributes = IdentityAttributeHome.selectAllAttributes( );
        }
    }

}
