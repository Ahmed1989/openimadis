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

import ij.IJ;
import ij.ImagePlus;
import ij.ImageStack;
import ij.gui.GenericDialog;
import ij.measure.Calibration;
import ij.plugin.PlugIn;
import ij.process.ByteProcessor;
import ij.process.ColorProcessor;
import ij.process.FloatProcessor;
import ij.process.ImageProcessor;
import ij.process.ShortProcessor;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.strandgenomics.imaging.iclient.ImageSpace;
import com.strandgenomics.imaging.iclient.ImageSpaceObject;
import com.strandgenomics.imaging.iclient.PixelMetaData;
import com.strandgenomics.imaging.iclient.Project;
import com.strandgenomics.imaging.iclient.Record;
import com.strandgenomics.imaging.icore.Dimension;
import com.strandgenomics.imaging.icore.IPixelData;
import com.strandgenomics.imaging.icore.image.PixelArray;

/*
 *
 * Plugin to view iManage records in ImageJ
 *
 */

public class ViewRecord_ implements PlugIn {

	private static ImageProcessor getImageProcessor(IPixelData pixelData)
			throws IOException
	{
		PixelArray rawData = pixelData.getRawData();
		if (rawData instanceof PixelArray.Byte)
		{
			// create byteprocessor if the data is of type BYTE
			byte[] img = (byte[]) rawData.getPixelArray();
			return new ByteProcessor(pixelData.getImageWidth(),
					pixelData.getImageHeight(), img, null);
		}
		else
			if (rawData instanceof PixelArray.Short)
			{
				// create shortprocessor if the data is of type SHORT
				short[] img = (short[]) rawData.getPixelArray();
				return new ShortProcessor(pixelData.getImageWidth(),
						pixelData.getImageHeight(), img, null);
			}
			else
				if (rawData instanceof PixelArray.Integer)
				{
					// create colorprocessor if the data is of type INT
					int[] img = (int[]) rawData.getPixelArray();
					return new ColorProcessor(pixelData.getImageWidth(),
							pixelData.getImageHeight(), img);
				}
				else
					if (rawData instanceof PixelArray.Float)
					{
						// create colorprocessor if the data is of type INT
						float[] img = (float[]) rawData.getPixelArray();
						return new FloatProcessor(pixelData.getImageWidth(),
								pixelData.getImageHeight(), img, null);
					}

		return null;
	}

	public void run(String arg)
	{

		GenericDialog gd = new GenericDialog("iManage Strand");

		gd.setEchoChar('*');
		gd.addStringField("Auth-Code", "");

		gd.addStringField("Host IP", "demo.avadis-img.com");
		gd.addNumericField("Host Port", 80, 0);
		gd.addMessage("This plugin will open a record in a project,\n apply a fiond edge filter to it \n and try to upload it back.");
		gd.showDialog();
		if (gd.wasCanceled())
			return;
		// get the parameter from the GUI

		String password = gd.getNextString();
		String hostIP = gd.getNextString();
		int hostPort = (int) gd.getNextNumber();
		
		ImageSpace ispace = ImageSpaceObject.getConnectionManager();
		String AppID = "epNJofuAvd2IwWVbn2lCMFeCTMW7PoeqH14R93ux";

		try
		{
			ispace.login(false, hostIP, hostPort, AppID, password);
		}

		catch (Exception e)
		{
			e.printStackTrace();
			IJ.showMessage("Check your ImageJ proxy settings: None if you're local, www-cache 3128 if outside");
			return;
		}
		List<Project> projectList = null;
		try
		{
			projectList = ispace.getActiveProjects();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			IJ.showMessage("Error in getting active project");
		}

		if (projectList != null)
		{
			String[] listofproject = new String[projectList.size()];
			int i = 0;
			for (Project p : projectList)
			{
				listofproject[i] = p.getName();
				i = i + 1;

			}

			GenericDialog gdproj = new GenericDialog(
					"Strand Life Sciences: Create Image in IManage Server");
			gdproj.addChoice("Select your project", listofproject,
					listofproject[0]);
			gdproj.addStringField("Tag to search for", "nimisha");
			gdproj.addNumericField("Max Number of results", 1, 0);
			gdproj.showDialog();
			if (gd.wasCanceled())
				return;

			int projindex = gdproj.getNextChoiceIndex();
			String tag = gdproj.getNextString();
			int maxresult = (int) gdproj.getNextNumber();
			Project p = projectList.get(projindex);
			Set<String> projectNames = new HashSet<String>();
			projectNames.add(p.getName());

			long[] projectSpecGuids = ImageSpaceObject.getImageSpace().search(
					tag, projectNames, null, maxresult);
			if (projectSpecGuids != null)
			{
				IJ.showMessage(" Number of record found:"
						+ IJ.d2s(projectSpecGuids.length)
						+ "\n All stack will be download, and filtered and created as a new record");

				for (int idx = 0; idx < projectSpecGuids.length; idx++)
				{
					Record r = ImageSpaceObject.getImageSpace()
							.findRecordForGUID(projectSpecGuids[idx]);
					// Open it, process it, save the results
					// get pixel data
					int nbframes = r.getFrameCount();
					int nbslices = r.getSliceCount();
					int width = r.getImageWidth();
					int height = r.getImageHeight();
					int channels = r.getChannelCount();
					String title = r.getSourceFilename();
					Calibration cal = new Calibration();
					cal.setUnit("um");
					cal.pixelWidth = r.getPixelSizeAlongXAxis();
					cal.pixelHeight = r.getPixelSizeAlongYAxis();
					cal.pixelDepth = r.getPixelSizeAlongZAxis();
					cal.frameInterval = 1.0;// r. NOT AVAILABLE FOR NOW
					ImageStack myrecord = new ImageStack(width, height);
					
					IPixelData pixelData = r.getPixelData(new Dimension(0, 0, 0, 0));
					double elapsedtime = pixelData.getElapsedTime();

					for (int c = 0; c < channels; c++)
					{
						for (int s = 0; s < nbslices; s++)
						{
							for (int f = 0; f < nbframes; f++)
							{

								try
								{
									pixelData = r.getPixelData(new Dimension(f, s, c, 0));
								}
								catch (Exception e)
								{
									// TODO Auto-generated catch block
									e.printStackTrace();
									IJ.showMessage(" error in getting image");
									return;
								}
								IJ.showStatus(Integer.toString(f) + "/"
										+ Integer.toString(nbframes));

								ImageProcessor myip = null;
								try
								{
									myip = getImageProcessor(pixelData);
								}
								catch (Exception e)
								{
									// TODO Auto-generated catch block
									e.printStackTrace();
									IJ.showMessage(" error in getting image");
									return;
								}


								myrecord.addSlice("Slice " + s + " and frame " + f,myip);

							}
							
						}
					}

					ImagePlus toshow = new ImagePlus(title + "_s");
					toshow.setStack(myrecord, channels, nbslices, nbframes);
					toshow.setCalibration(cal);
					toshow.show();
				}

			}

		}

		else
		{
			IJ.showMessage("projects not found...");
		}

		ispace.logout();
		IJ.showMessage("Done Showing Records");
	}

}

