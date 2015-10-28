/*
 * Parameter.java
 *
 * AVADIS Image Management System
 *
 * Copyright 2011-2012 by Strand Life Sciences
 * 5th Floor, Kirloskar Business Park, 
 * Bellary Road, Hebbal
 * Bangalore 560024
 * Karnataka, India
 * 
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Strand Life Sciences., ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Strand Life Sciences.
 */
package com.strandgenomics.imaging.icore.app;

import java.io.Serializable;

/**
 * Description of a parameter of an application
 * @author arunabha
 *
 */
public class Parameter implements Serializable {

	private static final long serialVersionUID = -4442491119645663893L;
	
	/**
	 * name of the parameter
	 */
	public final String name;
	/**
	 * type of the parameter, one of integer, float, boolean and string only
	 */
	public final ParameterType type;
	/**
	 * constraints on this parameter, can be null, meaning unbounded
	 */
	public final ParameterConstraints constraints;
	/**
	 * default value associated with this parameter, null is not a valid value
	 */
	public final Object defaultValue;
	/**
	 * description of the parameter
	 */
	public final String description;
	
	/**
	 * Creates a Parameter having the specified name, type and constraints
	 * @param name name of the parameter
	 * @param type type of the parameter, one of integer, float boolean and string only
	 * @param constraints constraints on this parameter
	 * @param defaultValue default value of the parameter
	 * @param description parameter description
	 */
	public Parameter(String name, ParameterType type, ParameterConstraints constraints, Object defaultValue, String description)
	{
		if(name == null || type == null)
			throw new IllegalArgumentException("null parameter name or type");
		
		if(constraints != null && constraints.getType() != type)
			throw new IllegalArgumentException("parameter and its constraint type do not match");
		
		this.name = name;
		this.type = type;
		this.constraints = constraints;
		
		if(!validate(defaultValue))
			throw new IllegalArgumentException("illegal default value="+defaultValue+" for parameter "+name);
		
		this.defaultValue = defaultValue;
		
		this.description = description;
	}
	
	@Override
	public String toString()
	{
		return name;
	}
	
	@Override
	public int hashCode()
	{
		return name.hashCode();
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj != null && obj instanceof Parameter)
		{
			Parameter that = (Parameter) obj;
			if(this == that) 
				return true;
			else 
				return this.name.equals(that.name);
		}
		return false;
	}
	
	/**
	 * check if the specified value is valid for this parameter
	 * @param value the value to verify
	 * @return true if valid, false otherwise
	 */
	public boolean validate(Object value)
	{
		if(isValidType(value))
		{
			return constraints == null ? true : constraints.validate(value); 
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * Returns true if the specified value is consistent with this parameter's type
	 * @param value the value to verify
	 * @return  true if valid, false otherwise
	 */
	public boolean isValidType(Object value)
	{
		boolean validType = false;
		switch(type)
		{
			case INTEGER:
				validType = (value instanceof Long || value instanceof Integer || value instanceof Short);
				break;
			case DECIMAL:
				validType = (value instanceof Float || value instanceof Double);
				break;
			case STRING:
				validType = (value instanceof String);
				break;
			case BOOLEAN:
				validType = (value instanceof Boolean);
				break;
		}
		return validType;
	}
}
