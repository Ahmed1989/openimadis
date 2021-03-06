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

package com.strandgenomics.imaging.iserver.services.def.worker;

/**
 * Definition of an application parameter
 * @author arunabha
 *
 */
public class Parameter {
	/**
	 * name of the parameter
	 */
	private String name;
	/**
	 * type of the parameter, one of integer, float, boolean and string only
	 */
	private String type;
	/**
	 * constraints on this parameter, can be null, meaning unbounded
	 */
	private Constraints constraints;
	/**
	 * default value associated with this parameter, null is not a valid value
	 */
	private Object defaultValue;
	/**
	 * description of the parameter
	 */
	private String description;
	
	
	public Parameter()
	{}
	
	

	/**
	 * @return the name
	 */
	public String getName() 
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the type
	 */
	public String getType() 
	{
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) 
	{
		this.type = type;
	}

	/**
	 * @return the constraints
	 */
	public Constraints getConstraints() 
	{
		return constraints;
	}

	/**
	 * @param constraints the constraints to set
	 */
	public void setConstraints(Constraints constraints)
	{
		this.constraints = constraints;
	}

	/**
	 * @return the defaultValue
	 */
	public Object getDefaultValue() 
	{
		return defaultValue;
	}

	/**
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(Object defaultValue)
	{
		this.defaultValue = defaultValue;
	}

	/**
	 * returns the description of the parameter
	 * @return
	 */
	public String getDescription()
	{
		return description;
	}

	/**
	 * sets the description of parameter
	 * @param description parameter description
	 */
	public void setDescription(String description)
	{
		this.description = description;
	}
}

