/*
 * Archive.java
 *
 * AVADIS Image Management System
 * Engine Implementation
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
package com.strandgenomics.imaging.iengine.models;

import java.io.File;
import java.math.BigInteger;
import java.util.List;

import com.strandgenomics.imaging.icore.ISourceReference;
import com.strandgenomics.imaging.icore.Storable;

public class Archive implements Storable {
	
	private static final long serialVersionUID = 303097238924475161L;
	/**
	 * list of source files (as found in the acquisition machine
	 */
	protected List<ISourceReference> sourceFiles;
	/**
	 * MD5 hash of all the constituent files 
	 */
	protected BigInteger signature;
	/**
	 * name of record specific folder - typically record file-name as the folder name
	 * this folder will contain the record source files 
	 * this folder will also contain an attachment folder containing the attachments 
	 * in case of an existing folder of the same name, will create folder by suffixing with number, 
	 * till a unique folder is created
	 */
	protected String archiveFolder;
	/**
	 * name of the folder containing the record-archive, typically project-name/user-name
	 */
	protected String rootFolder;
	
	public Archive(BigInteger signature, String rootFolder, String archiveFolder, List<ISourceReference> sourceFiles)
	{
		this.sourceFiles = sourceFiles;
		this.signature = signature;
		this.archiveFolder = archiveFolder;
		this.rootFolder = rootFolder;
	}

	@Override
	public void dispose() 
	{
		signature = null;
		sourceFiles = null;
	}
	
	public List<ISourceReference> getSourceFiles() {
		return sourceFiles;
	}

	public BigInteger getSignature() {
		return signature;
	}

	/**
	 * name of record specific folder - typically record file-name as the folder name
	 * this folder will contain the record source files 
	 * this folder will also contain an attachment folder containing the attachments 
	 * in case of an existing folder of the same name, will create folder by suffixing with number, 
	 * till a unique folder is created
	 */
	public String getArchiveFolder() 
	{
		return archiveFolder;
	}

	/**
	 * name of the folder containing the record-archive, typically project-name/user-name
	 * @return
	 */
	public String getRootFolder() 
	{
		return rootFolder;
	}
	
	public String getStorageLocation()
	{
		//by default the path will wave unix separator
		return rootFolder.replace('/', File.separatorChar) +File.separator + archiveFolder;
	}
	
	public String toString()
	{
		return getStorageLocation();
	}
}