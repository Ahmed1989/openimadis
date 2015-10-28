/*
 * ITile.java
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
package com.strandgenomics.imaging.icore;

import java.io.IOException;

import com.strandgenomics.imaging.icore.image.PixelArray;


/**
 * A tile or rectangular block within a pixel data
 * @author arunabha
 *
 */
public interface ITile extends ImageSource, Disposable {
	
	/**
	 * Returns the dimension of this pixel-data
	 * @return the frame number for this image
	 */
	public Dimension getDimension();
	
	/**
	 * Returns the X coordinate of the upper-left corner of the specified rectangular region
	 * @return the X coordinate of the upper-left corner of the specified rectangular region
	 */
	public int getX();
	
	/**
	 * Returns the Y coordinate of the upper-left corner of the specified rectangular region
	 * @return the Y coordinate of the upper-left corner of the specified rectangular region
	 */
	public int getY();
	
    /**
     * Returns the raw data associated with this image 
     * @return the raw data associated with this image 
     */
    public PixelArray getRawData() throws IOException;
}
