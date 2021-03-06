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

package com.strandgenomics.imaging.icore.app;

import java.util.Set;

/**
 * Detail specification of an application
 * @author arunabha
 *
 */
public class ApplicationSpecification extends Application {
	
	private static final long serialVersionUID = -17932380155310894L;

	/**
	 * broad area (or domain) where this application is relevant - for example Quality Control, Statistics 
	 */
	public final String categoryName;
	
	/**
	 * general description of the application
	 */
	private String description;

	/**
	 * list of parameters
	 */
	public Set<Parameter> parameters;
	
	/**
	 * server side unique identifier for application
	 */
	public final String clientId;
	
	/**
	 * Defines a application with its name, version, category name, description, parameters and constraints
	 * @param name
	 * @param version
	 * @param categoryName
	 * @param description
	 * @param parameters
	 * @param publisherKey
	 */
    public ApplicationSpecification(String clientId, String name, String version, String categoryName, String description, Set<Parameter> parameters)
    {
    	super(name, version);
    	
    	this.description  = description;
    	this.categoryName = categoryName;
    	this.parameters   = parameters;
    	this.clientId = clientId;
    }
    
    /**
     * updates the description to specified one
     * @param description
     */
    public void setDescription(String description)
    {
    	this.description = description;
    }
    
	/**
	 * Returns a short description of this application
	 * @return a short description of this application
	 */
	public String getDescription()
	{
		return description;
	}
	
	/**
	 * Returns server side unique identifier for application
	 * @return client id
	 */
	public String getID()
	{
		return clientId;
	}
	
	/**
	 * Returns the category name 
	 * @return
	 */
	public String getCategory()
	{
		return categoryName;
	}
	
	/**
	 * Returns a general description of each parameter needed for application execution
	 * @return a general description of each parameter needed for application execution
	 */
	public Set<Parameter> getParameters()
	{
		return parameters;
	}
}
