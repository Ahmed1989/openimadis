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

package com.strandgenomics.imaging.iengine.auth;

import java.util.Date;
import java.util.List;

import com.strandgenomics.imaging.iengine.Service;

/**
 * An access token is used to perform authorization on behalf of a user. It can
 * act as a proxy for the user or provide a more fine-grained access to the
 * resources of the user. It is uniquely identified by a id
 * 
 * @author santhosh
 */
public interface IAccessToken extends IPFilter {

    /**
     * Get unique id associated with this token
     * 
     * @return unique id
     */
    public String getId();

    /**
     * Can the access token access the service given.
     * 
     * @param service
     *            service which needs to be checked.
     * @return <code>true</code> if access is allowed. <code>false</code>
     *         otherwise.
     */
    public boolean canAccess(Service service);

    /**
     * Is the token still valid
     * 
     * @return <code>true</code> if the token is still valid, <code>false</code>
     *         otherwise
     */
    public boolean isValid();

    /**
     * Has the token expired or not
     * 
     * @return <code>true</code> if the token has expired. <code>false</code>
     *         otherwise
     */
    public boolean hasExpired();

    /**
     * Get user associated with token
     * 
     * @return user associated with token
     */
    public String getUser();

    /**
     * Get id of client asssociated with token
     * 
     * @return id of client asssociated with token
     */
    public String getClientID();

    /**
     * Returns the time instance when the access token expires
     * 
     * @param accessToken
     *            the access token under consideration
     * @return milliseconds left for the specified access to expire
     */
    public Date getExpiryTime();

    /**
     * Get the list of filters associated with this token.
     * 
     * @return list of filters. can be <code>null</code>.
     */
    public List<IPFilter> getFilters();

    /**
     * Returns the time instance when the access token was last accessed.
     * 
     * @return the time instance when the access token was last accessed.
     */
    public Date getLastAccessTime();

    /**
     * Returns the time instance when the access token was created
     * 
     * @return the time instance when the access token was created
     */
    public Date getCreationTime();

    /**
     * Internal id to reference the access token.
     * 
     * @return internal id of the access token.
     */
    public long getInternalID();
    
    /**
     * Get list of services the token has access to.
     * @return list of services the token has access to.
     */
    public Service[] getAllowedServices();
}
