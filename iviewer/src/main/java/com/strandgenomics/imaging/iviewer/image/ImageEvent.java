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

package com.strandgenomics.imaging.iviewer.image;

import com.strandgenomics.imaging.icore.Signature;

import edu.umd.cs.piccolo.PLayer;

public class ImageEvent {
	
	/**
	 * record identifier
	 */
	protected Signature recordID;
	/**
	 *  The slice number (Z) of the image to overlay with channels
	 */
	public final int sliceNo;
	/**
	 * The frame number of the image to overlay with channels
	 */
	public final int frameNo;
	/**
	 *  The site number of the image to overlay with channels
	 */
	public final int siteNo; 
	/**
	 *  The list of channel numbers (dimensions) that are combined together to create this PixelData overlay  
	 */
	public final int[] channelNos;
	
	/**
	 * the image data
	 */
	protected PLayer piccoloLayer = null;
	
	/**
	 * Create a ImageEvent for the specified coordinates of the specified record
	 * @param recordID the record under consideration
	 * @param frameNo the frame number
	 * @param sliceNo the slice (Z) number
	 * @param channels the overlayed(selected) channels
	 * @param piccoloLayer the image
	 */
	public ImageEvent(PLayer piccoloLayer, Signature recordID, int sliceNo, int frameNo, int siteNo, int[] channelNos)
	{
		this.recordID = recordID;
		this.piccoloLayer = piccoloLayer;
		
		this.sliceNo = sliceNo;
		this.frameNo = frameNo;
		this.siteNo  = siteNo;
		this.channelNos = channelNos;
	}
	
	public boolean isValid()
	{
		return piccoloLayer != null;
	}
	
	public void dispose()
	{
		recordID = null;
		piccoloLayer = null;
	}

	/**
	 * @return the recordID
	 */
	public Signature getRecordID() {
		return recordID;
	}

	/**
	 * @return the piccoloLayer
	 */
	public PLayer getPiccoloLayer()
	{
		return piccoloLayer;
	}
}
