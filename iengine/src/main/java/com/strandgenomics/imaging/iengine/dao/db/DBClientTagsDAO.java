/*
 * DBClientTagsDAO.java
 *
 * Product:  AvadisIMG Server
 *
 * Copyright 2007-2012, Strand Life Sciences
 * 5th Floor, Kirloskar Business Park, 
 * Bellary Road, Hebbal,
 * Bangalore 560024
 * India
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Strand Life Sciences., ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Strand Life Sciences.
 */
package com.strandgenomics.imaging.iengine.dao.db;

import java.sql.Types;
import java.util.logging.Level;

import com.strandgenomics.imaging.icore.db.ConnectionProvider;
import com.strandgenomics.imaging.icore.db.DataAccessException;
import com.strandgenomics.imaging.icore.db.SQLQuery;
import com.strandgenomics.imaging.iengine.dao.ClientTagsDAO;
import com.strandgenomics.imaging.iengine.dao.ImageSpaceDAOFactory;
import com.strandgenomics.imaging.iengine.models.ClientTags;

/**
 * DB implementation of {@link ClientTagsDAO}
 * 
 * @author navneet
 */
public class DBClientTagsDAO extends ImageSpaceDAO<ClientTags> implements ClientTagsDAO {

    DBClientTagsDAO(ImageSpaceDAOFactory factory, ConnectionProvider connectionProvider) {
        super(factory, connectionProvider, "ClientTagsDAO.xml");
    }

	@Override
	public ClientTags createObject(Object[] columnValues) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getClientIdsByTag(String tag) throws DataAccessException {
        logger.logp(Level.INFO, "DBClientTagsDAO", "getClientIdsByTag", "Get all clients by tag");
        
        SQLQuery sqlQuery = queryDictionary.createQueryGenerator("GET_CLIENTS_BY_TAG");
        sqlQuery.setValue("Tag", tag, Types.VARCHAR);
        return getRowsWithStringValues(sqlQuery);
	}

	@Override
	public String[] getTagsForClient(String clientID) throws DataAccessException {
        logger.logp(Level.INFO, "DBClientTagsDAO", "getTagsForClient", "Get all tags for a client");
        
        SQLQuery sqlQuery = queryDictionary.createQueryGenerator("GET_TAGS_FOR_CLIENT");
        sqlQuery.setValue("ClientID", clientID, Types.VARCHAR);
        return getRowsWithStringValues(sqlQuery);
	}

	@Override
	public void addTagForClient(String clientID, String tag) throws DataAccessException {
        logger.logp(Level.INFO, "DBClientTagsDAO", "addTagForClient", "add tag for a client");
        
        SQLQuery sqlQuery = queryDictionary.createQueryGenerator("ADD_TAG_FOR_CLIENT");
        sqlQuery.setValue("ClientID", clientID, Types.VARCHAR);
        sqlQuery.setValue("Tag", tag, Types.VARCHAR);
        
        updateDatabase(sqlQuery);		
	}

}