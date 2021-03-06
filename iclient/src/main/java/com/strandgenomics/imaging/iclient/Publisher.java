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

package com.strandgenomics.imaging.iclient;

import java.util.List;



/**
 * Publisher of applications
 * @author arunabha
 *
 */
public class Publisher extends ImageSpaceObject {
	
	private static final long serialVersionUID = -7804187387651016078L;
	/**
	 * name of the publisher (unique)
	 */
	protected String name;
	
	Publisher(String name)
	{
		this.name = name;
	}
	
	/**
	 * Returns the name of the publisher publishing a application
	 * @return the name of the publisher
	 */
	public String getName()
	{
		return name;
	}

    /**
     * List applications published by this publisher
     * @param categoryName name of the category or null
     * @return a list of applications published by this publisher
     */
    public List<Application> listApplications(String categoryName)
    {
    	return getImageSpace().listApplications(this, categoryName);
    }

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}
}
