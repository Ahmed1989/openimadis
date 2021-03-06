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

package com.strandgenomics.imaging.iserver.services.def.ispace;

/**
 * The distribution of intensity values across the pixels within a PixelArray or Image
 * @author arunabha
 *
 */
public class Statistics {
	
	private int pixelDepth;
	/**
	 * Maximum intensity value
	 */
    private int maxValue;
    /**
     * Minimum intensity value
     */
    private int minValue;
    /**
     * list of all intensity values having non zero frequencies
     */
    private Integer[] intensities;
    /**
     * frequency distribution for the corresponding intensities
     */
    private Integer[] frequencies;
    /**
     * maximum frequency
     */
    private int maxFreq;
    
    public Statistics()
    {}

	/**
	 * @return the maxValue
	 */
	public int getMaxValue() {
		return maxValue;
	}

	/**
	 * @param maxValue the maxValue to set
	 */
	public void setMaxValue(int maxValue) {
		this.maxValue = maxValue;
	}

	/**
	 * @return the minValue
	 */
	public int getMinValue() {
		return minValue;
	}

	/**
	 * @param minValue the minValue to set
	 */
	public void setMinValue(int minValue) {
		this.minValue = minValue;
	}

	/**
	 * @return the intensities
	 */
	public Integer[] getIntensities() {
		return intensities;
	}

	/**
	 * @param intensities the intensities to set
	 */
	public void setIntensities(Integer[] intensities) {
		this.intensities = intensities;
	}

	/**
	 * @return the frequencies
	 */
	public Integer[] getFrequencies() {
		return frequencies;
	}

	/**
	 * @param frequencies the frequencies to set
	 */
	public void setFrequencies(Integer[] frequencies) {
		this.frequencies = frequencies;
	}

	/**
	 * @return the maxFreq
	 */
	public int getMaxFreq() {
		return maxFreq;
	}

	/**
	 * @param maxFreq the maxFreq to set
	 */
	public void setMaxFreq(int maxFreq) {
		this.maxFreq = maxFreq;
	}

	/**
	 * @return the pixelDepth
	 */
	public int getPixelDepth() {
		return pixelDepth;
	}

	/**
	 * @param pixelDepth the pixelDepth to set
	 */
	public void setPixelDepth(int pixelDepth) {
		this.pixelDepth = pixelDepth;
	}
}
