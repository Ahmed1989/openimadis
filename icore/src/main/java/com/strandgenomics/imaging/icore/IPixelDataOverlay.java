/*
 * IPixelDataOverlay.java
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

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;


/**
 * An overlay of two or more channels
 * @author arunabha
 */
public interface IPixelDataOverlay extends Disposable {

	/**
	 * The list of channel numbers (dimensions) that are combined together to create this PixelData overlay  
	 * @return list of image-coordinates (dimensions) that are combined together
	 */
	public int[] getOverlaidChannels();
	
	/**
	 * The slice number (Z) of the image to overlay with channels
	 * @return  slice number (Z) of the image to overlay with channels
	 */
	public int getSliceNumber();
	
	/**
	 * The frame number of the image to overlay with channels
	 * @return  frame number of the image to overlay with channels
	 */
	public int getFrameNumber();
	
	/**
	 * The site number of the image to overlay with channels
	 * @return  site number of the image to overlay with channels
	 */
	public int getSiteNumber();
	
    /**
     * Returns the width (in pixels) of the images represented by this source
     * Note that width is same for all images within the record
     * @return image width
     */
    public int getImageWidth();
    
    /**
     * Returns the height (in pixels) of the images represented by this source
     * Note that height is same for all images within the record
     * @return image height
     */
    public int getImageHeight();
    
	/**
	 * Returns the RGB BufferedImage instance associated with this image source for specified ROI
	 * @param zStacked true for Maximum Intensity Projection across slices, false otherwise
	 * @param mosaic 
	 * @param useChannelColor true if a color image (based on the channel color) is returned, gray-scale image otherwise
	 * @param roi specified region of interest
	 * @return BufferedImage instance associated with this image source
	 * @throws IOException
	 */
	public BufferedImage getImage(boolean zStacked, boolean mosaic, boolean useChannelColor, Rectangle roi) throws IOException;
}