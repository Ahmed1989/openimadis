/*
 * UploadListener.java
 *
 * AVADIS Image Management System
 * Utility Stuffs
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
package com.strandgenomics.imaging.icore.util;

import java.io.File;
import java.util.EventListener;

/**
 * UI component interested with the current state of the upload
 * @author arunabha
 *
 */
public interface UploadListener extends EventListener {

	/**
	 * This method is called when the given file is getting uploaded 
	 * @param source the file which is being uploaded
	 * @param totalBytes total bytes to transfer
	 * @param bytesUploaded number of bytes actually transferred
	 */
	void reportProgress(File source, long totalBytes, long bytesUploaded);
}
