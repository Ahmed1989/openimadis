package com.strandgenomics.imaging.iengine.models;

/**
 * This class represents the final resource generated by MosaicManager.
 * This class will define methods which can be called on mosaic resource 
 * for example fetching a tile from stitched mosaic image.
 * 
 * @author navneet
 *
 */
public class MosaicResource {
	
	/**
	 * records which are being used to create stitched image
	 */
	private long[] recordids;
	
	/**
	 * the coordinate which represents the top most point of stitched image
	 */
	private int anchor_left;
	
	/**
	 * the coordinate which represents the left most point of stitched image
	 */
	private int anchor_top;
	
	/**
	 * width of resultant mosaic image
	 */
	private int mosaicImageWidth;
	
	/**
	 * height of resultant mosaic image
	 */
	private int mosiacImageHeight;
	
	public MosaicResource(long[] recordids, int anchor_left, int anchor_top,
			int mosaicImageWidth, int mosiacImageHeight) {
		super();
		this.recordids = recordids;
		this.anchor_left = anchor_left;
		this.anchor_top = anchor_top;
		this.mosaicImageWidth = mosaicImageWidth;
		this.mosiacImageHeight = mosiacImageHeight;
	}

	public MosaicResource() {
		super();
	}

	public MosaicResource(long[] recordids, int anchor_left, int anchor_top) {
		super();
		this.recordids = recordids;
		this.anchor_left = anchor_left;
		this.anchor_top = anchor_top;
	}
	
	public int getMosaicImageWidth() {
		return mosaicImageWidth;
	}

	public void setMosaicImageWidth(int mosaicImageWidth) {
		this.mosaicImageWidth = mosaicImageWidth;
	}

	public int getMosiacImageHeight() {
		return mosiacImageHeight;
	}

	public void setMosiacImageHeight(int mosiacImageHeight) {
		this.mosiacImageHeight = mosiacImageHeight;
	}
	
	public long[] getRecordids() {
		return recordids;
	}

	public void setRecordids(long[] recordids) {
		this.recordids = recordids;
	}
	
	public int getAnchor_left() {
		return anchor_left;
	}

	public void setAnchor_left(int anchor_left) {
		this.anchor_left = anchor_left;
	}

	public int getAnchor_top() {
		return anchor_top;
	}

	public void setAnchor_top(int anchor_top) {
		this.anchor_top = anchor_top;
	}
}