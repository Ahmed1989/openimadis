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

package com.strandgenomics.imaging.iengine.models;

import com.strandgenomics.imaging.icore.Storable;

/**
 * Description of record attachments
 * 
 * @author Anup Kulkarni
 */
public class Attachment implements Storable {
	
	private static final long serialVersionUID = -2898659659007150984L;
	/**
	 * The user who has added this attachment
	 */
	protected String addedBy;
	/**
	 * name of this attachment
	 */
	protected String name;
	/**
	 * notes associated with this attachment
	 */
	protected String notes;
	
	public Attachment(String name, String notes, String addedBy)
	{
		this.name = name;
		this.notes = notes;
		this.addedBy = addedBy;
	}
	
	public String getName() 
	{
		return name;
	}

	public String getNotes()
	{
		return notes;
	}
	
	public String getUser()
	{
		return addedBy;
	}
	
	@Override
	public void dispose() 
	{
		name = null;
		notes = null;
		addedBy = null;
	}
}
