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

package com.strandgenomics.imaging.icore.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.strandgenomics.imaging.icore.Disposable;

/**
 * Root class of all query fragments
 * @author arunabha
 *
 */
public abstract class QueryFragment implements Disposable, Cloneable {
	
	/**
	 * the logger to logs
	 */
	protected Logger logger = Logger.getLogger("com.strandgenomics.imaging.icore.db");

    /**
     * Returns the string representation of the sql query fragment
     */
    public abstract String toQueryString();
    
    /**
     * Returns the cloned instance of this query-fragment
     */
    public abstract Object clone();

    /**
     * Set the parameter index and the corresponding value to the PreparedStatement and returns 
     * the next available parameter index. Note that the index starts with 1.
     */
    public abstract int setParameterIndexAndValue(PreparedStatement pstmt, int parameterIndex)
        throws SQLException;
    
    /**
     * support for altering queries, generally to be used for prepared statement non-parameter stuff
     */
    public abstract void setParameter(String name, String value);
    
    /**
     * support for nested and/or parameterized queries
     */
    public abstract void setParameter(String name, QueryFragment value);
    
    /**
     * set value to parameter of the specified name with the specified value and java.sql.Types
     */
    public abstract void setValue(String name, Object value, int sqlType);
    
    /**
     * set value to parameter of the specified name with the specified value, types and operator
     */
    public abstract void setValue(String name, Object value, int sqlType, Operator operator);

    /**
     * set value to parameter of the specified name with the specified value, types, operator and the forceNullUse flag
     */
	public abstract void setValue(String name, Object value, int sqlType, Operator operator, boolean forceNullUse);
}