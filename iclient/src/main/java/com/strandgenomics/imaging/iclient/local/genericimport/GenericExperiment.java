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

package com.strandgenomics.imaging.iclient.local.genericimport;


import java.io.File;
import java.io.IOException;

import loci.formats.gui.BufferedImageReader;

import org.apache.log4j.Logger;

import com.strandgenomics.imaging.iclient.local.RawExperiment;
import com.strandgenomics.imaging.iclient.local.RawSourceReference;
import com.strandgenomics.imaging.icore.Dimension;
import com.strandgenomics.imaging.icore.bioformats.BioRecord;
import com.strandgenomics.imaging.icore.bioformats.ImageManager;
import com.strandgenomics.imaging.icore.bioformats.ImageReaderFactory;
import com.strandgenomics.imaging.icore.bioformats.custom.IMGReaderFactory;
import com.strandgenomics.imaging.icore.bioformats.custom.RecordMetaData;

/**
 * A Record data source backed up by RecordMetaData 
 * @author arunabha
 *
 */
public class GenericExperiment extends RawExperiment {
	
	private static final long serialVersionUID = 6580898524859269591L;
	/**
	 * The meta data that powers this experiment
	 */
	protected RecordMetaData metaData = null;

	public GenericExperiment(RecordMetaData metaData) throws IOException 
	{
		super(metaData.getSeed());
		this.metaData = metaData;
		//add all the source references
		for(File sourceFile : metaData.getFiles())
		{
			sourceReferences.add( new RawSourceReference(sourceFile) );
		}
		
		sourceReferences.add( new RawSourceReference(metaData.getSeed()) );
	}
	
	public BioRecord extractRecord()
	{
		BufferedImageReader formatHandler = null;
		BioRecord record = null;
		try
		{
			formatHandler = ImageManager.getInstance().getImageReader(this);
			//create the record for the ith series
			Logger.getRootLogger().info("[GenericExperiment]:\tcreating record ...");
			record = createRecord();
			
			Logger.getRootLogger().info("[GenericExperiment]:\tinspecting pixel-data for record "+metaData);
			record.populatePixelData(); //now populate with member pixel data
			
			Logger.getRootLogger().info(" Record Signature : " + record.getSignature());
			members.put(record.getSignature(), record);
		
			Logger.getRootLogger().info("[GenericExperiment]:\tGenerating thumbnail for record "+metaData);
			record.getThumbnail();
			Logger.getRootLogger().info("[GenericExperiment]:\tSuccessfully generated thumbnail for record  "+metaData);
		}
		catch(Exception ex)
		{
			Logger.getRootLogger().error("[GenericExperiment]:\tCould not create record "+metaData, ex);
		}
    	catch(OutOfMemoryError error)
    	{
    		Logger.getRootLogger().error("[GenericExperiment]:\tOutOfMemoryError, could not create record for "+metaData, error);
    	}
		finally
		{
			try {
				formatHandler.close();
			} 
			catch (IOException e)
			{}
		}
		
		return record;
	}
	
	private BioRecord createRecord() throws IOException
	{
		return createRecordObject(metaData.getSourceTime(), null, 
				metaData.getFrameCount(), metaData.getSliceCount(), 
				metaData.getChannels(), metaData.getSites(),
				metaData.getImageWidth(), metaData.getImageHeight(), 
				metaData.getPixelDepth(), 
				metaData.getPixelSizeX(), metaData.getPixelSizeY(), metaData.getPixelSizeZ(),
				metaData.getImageType(), metaData.getSourceFormat());
	}
	
	@Override
	public String getSourceFilename()
	{
		return metaData.getImageFile(new Dimension(0,0,0,0)).getName();
	}
	
	@Override
	public String getRootDirectory(){
		
		return metaData.getRootDirectory().getAbsolutePath();
	}
	
	/**
	 * Returns the Image reader factory 
	 * @return
	 */
	public ImageReaderFactory getImageReaderFactory()
	{
		return new IMGReaderFactory(metaData);
	}
}
