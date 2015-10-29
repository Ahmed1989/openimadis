/*
 * SearchCondition.java
 *
 * AVADIS Image Management System
 * Web Service Definitions
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
package com.strandgenomics.imaging.iserver.services.def.search;

public class SearchCondition extends SearchField {
	/**
	 * lower limit of the range, inclusive
	 */
	private Object lowerLimit = null;
	/**
	 * upper limit of the range, exclusive
	 */
	private Object upperLimit = null;
	
	public SearchCondition()
	{}
	
	public void setLowerLimit(Object value)
	{
		lowerLimit = value;
	}
	
	public void setUpperLimit(Object value)
	{
		upperLimit = value;
	}
	
	public Object getLowerLimit()
	{
		return lowerLimit;
	}
	
	public Object getUpperLimit()
	{
		return upperLimit;
	}
}
