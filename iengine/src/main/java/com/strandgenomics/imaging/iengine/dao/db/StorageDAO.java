/**
 * openImaDis - Open Image Discovery: Image Life Cycle Management Software
 * Copyright (C) 2011-2016  Strand Life Sciences
 *   
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.strandgenomics.imaging.iengine.dao.db;

import java.sql.Connection;

import com.strandgenomics.imaging.icore.Storable;
import com.strandgenomics.imaging.icore.db.ConnectionProvider;
import com.strandgenomics.imaging.icore.db.DataAccessException;
import com.strandgenomics.imaging.iengine.dao.ImageSpaceDAOFactory;

/**
 * Maps the data access objects to the storage database. DAO classes needing access to the storage
 * database needs to extend this class
 * @author arunabha
 *
 */
public abstract class StorageDAO<T extends Storable> extends ImageSpaceDAO<T> {
	
    /**
     * Creates a DAO instance with the factory and a connection provider 
     * @param factory factory of all relevant DAOs
     * @param connectionProvider provider if database connection
     * @throws DataAccessException 
     */
	StorageDAO(ImageSpaceDAOFactory factory, ConnectionProvider connectionProvider, String queryFile) 
	{
		super(factory, connectionProvider, queryFile);
	}
	
    /**
     * fetches database connection for the storage
     */
    public Connection getConnection() throws DataAccessException{

    	return connectionProvider.getStorageConnection();
    }
}
