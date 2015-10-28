/*
 * SystemManager.java
 *
 * AVADIS Image Management System
 * Core Engine
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
package com.strandgenomics.imaging.iengine.system;

import java.util.logging.Logger;

/**
 * Base class of Manager classes
 * @author arunabha
 *
 */
public abstract class SystemManager {
	
	/**
	 * the logger
	 */
	protected Logger logger;
	
	public SystemManager()
	{
		Config.getInstance(); //initializes the system
		logger = Logger.getLogger("com.strandgenomics.imaging.iengine.system");
	}
}