/**
 * JobReport.java
 *
 * Project imaging
 * Core Compute Component
 *
 * Copyright 2009-2012 by Strand Life Sciences
 * 237, Sir C.V.Raman Avenue
 * RajMahal Vilas
 * Bangalore 560080
 * India
 * All rights reserved.
 *
 * This software is the confidential and proprietary information
 * of Strand Life Sciences., ("Confidential Information").  You
 * shall not disclose such Confidential Information and shall use
 * it only in accordance with the terms of the license agreement
 * you entered into with Strand Life Sciences.
 */
package com.strandgenomics.imaging.icore.app;

/**
 * Status of a job being executed by the compute-server
 * @author arunabha
 *
 */
public class JobReport {
	
	/**
	 * id of the task being executed
	 */
	public final long taskID; 
	/**
	 * state of the job being executed
	 */
	public final JobState state;
	
	public JobReport(long taskID, JobState state)
	{
		this.taskID = taskID;
		this.state = state;
	}
	
	@Override
	public int hashCode()
	{
		return (int)(taskID ^ (taskID >>> 32));
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj != null && obj instanceof JobReport)
		{
			JobReport that = (JobReport) obj;
			return this.taskID == that.taskID;
		}
		return false;
	}
}