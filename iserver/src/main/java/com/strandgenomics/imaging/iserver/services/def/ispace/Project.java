/*
 * Project.java
 *
 * AVADIS Image Management System
 * Webservice Definitions
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
package com.strandgenomics.imaging.iserver.services.def.ispace;

/**
 * A place holder of many records - may be an administrative unit or biological
 * @author arunabha
 */
public class Project {
	
	/**
	 * name of the project
	 */
	private String name;
	/**
	 * notes/comments/description associated with this project
	 */
	private String notes;
	/**
	 * creation date, the difference, measured in milliseconds, between the current time and midnight, January 1, 1970 UTC.
	 */
	private long creationDate;
	/** 
	 * disk quota in gb 
	 */
	private double storageQuota;
	/** 
	 * disk usage in gb 
	 */
	private double spaceUsage;
	/**
	 * Number of records within a project
	 */
	private int recordCount = 0;
	
	//default constructor, as a requirement of java beans
	public Project()
	{}
	
	/**
	 * @return the storageQuota
	 */
	public double getStorageQuota() {
		return storageQuota;
	}

	/**
	 * @param storageQuota the storageQuota to set
	 */
	public void setStorageQuota(double storageQuota) {
		this.storageQuota = storageQuota;
	}

	/**
	 * @return the spaceUsage
	 */
	public double getSpaceUsage() {
		return spaceUsage;
	}

	/**
	 * @param spaceUsage the spaceUsage to set
	 */
	public void setSpaceUsage(double spaceUsage) {
		this.spaceUsage = spaceUsage;
	}
	
	/**
	 * Returns the name of the project
	 * Project names are unique across the Enterprise IMG Server
	 * @return the name of the project
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * Notes or comments associated with the project 
	 * @return Notes or comments associated with the project 
	 */
	public String getNotes()
	{
		return notes;
	}
	
	/**
	 * Returns the creation time
	 * @return the creation time
	 */
	public long getCreationDate()
	{
		return creationDate;
	}
    
    
	/**
	 * Sets the name of the project
	 * Project names are unique across the Enterprise IMG Server
	 */
	public void setName(String value)
	{
		name = value;
	}
	
	/**
	 * Sets Notes or comments associated with the project 
	 */
	public void setNotes(String value)
	{
		notes = value;
	}
	
	/**
	 * Sets the creation time
	 */
	public void setCreationDate(long value)
	{
		creationDate = value;
	}

	/**
	 * @return the recordCount
	 */
	public int getRecordCount() 
	{
		return recordCount;
	}

	/**
	 * @param recordCount the recordCount to set
	 */
	public void setRecordCount(int recordCount) 
	{
		this.recordCount = recordCount;
	}
}