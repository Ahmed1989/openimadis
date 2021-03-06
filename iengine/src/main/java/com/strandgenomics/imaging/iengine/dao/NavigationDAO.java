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

package com.strandgenomics.imaging.iengine.dao;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.strandgenomics.imaging.icore.ImageType;
import com.strandgenomics.imaging.icore.MetaData;
import com.strandgenomics.imaging.icore.SearchCondition;
import com.strandgenomics.imaging.icore.SearchField;
import com.strandgenomics.imaging.icore.SourceFormat;
import com.strandgenomics.imaging.icore.db.DataAccessException;
import com.strandgenomics.imaging.icore.image.PixelDepth;
import com.strandgenomics.imaging.iengine.models.AcquisitionProfile;
import com.strandgenomics.imaging.iengine.models.SearchColumn;
import com.strandgenomics.imaging.iengine.models.ValueCount;

/**
 * Methods to do navigation for a specific project
 * @author arunabha
 *
 */
public interface NavigationDAO {
	
    /**
     * Returns a list of meta-data names that are navigable
     * @return a list of meta-data names that are navigable
     */
    public Collection<SearchColumn> getNavigableFields() throws DataAccessException;
    
	/**
	 * Returns the list of fixed columns - Maps search-able columns with proper names 
	 * @return list of fixed columns
	 */
	public Collection<SearchColumn> getFixedFields();
    
    /**
     * Returns a list of meta-data names that are navigable
     * @return a list of meta-data names that are navigable
     */
    public Collection<SearchColumn> getUserAnnotationFields() throws DataAccessException;
	
	/**
	 * Inserts the specified named meta data to the specified record
	 * @param guid the record
	 * @param data the name & value of the meta data
	 * @throws DataAccessException
	 */
	public void insertUserAnnotation(long guid, MetaData data) 
			throws DataAccessException;

	public SearchColumn findFieldForAnnotationName(String annotationName);
	
	public SearchColumn findFieldForColumnName(String columnName);
	
	public int getRecordCountForNullValue(Set<SearchCondition> preConditions, SearchField field) 
			throws DataAccessException;
	
	public int getRecordCount(Set<SearchCondition> preConditions, SearchCondition field) 
			throws DataAccessException;

	public Object findMinimum(Set<SearchCondition> preConditions, SearchCondition field) 
			throws DataAccessException;

	public Object findMaximum(Set<SearchCondition> preConditions, SearchCondition field) 
			throws DataAccessException;
	
	public int getRecordCountForClosedInterval(Set<SearchCondition> preConditions, SearchField field, 
			Timestamp lowerLimit, Timestamp upperLimit) throws DataAccessException;

	public int getRecordCountForClosedInterval(Set<SearchCondition> preConditions, SearchField field,
			Long lowerLimit, Long upperLimit) throws DataAccessException;
	
	public int getRecordCountForClosedInterval(Set<SearchCondition> preConditions, SearchField field, 
			Double lowerLimit, Double upperLimit) throws DataAccessException;

	public int getRecordCountForOpenInterval(Set<SearchCondition> preConditions, SearchField field, 
			Double lowerLimit, Double upperLimit) throws DataAccessException;
	
	public List<ValueCount> findFixedTextBins(Set<SearchCondition> preConditions, SearchCondition field, 
			boolean ascending, int maxBins) throws DataAccessException;
	
	public long[] getRecordGuids(Set<SearchCondition> searchConditions, Integer integer)
			throws DataAccessException;
	
	/**
	 * Returns the column values for the specified guids on the selected columns
	 * @param guids the relevant set of guids
	 * @param columns
	 * @return
	 * @throws DataAccessException
	 */
	public Map<Long, List<MetaData>> getRecordMetaData(Set<Long> guids, SearchColumn ... columns) 
			throws DataAccessException;

	/**
	 * Inserts a new record instance having the specified fixed fields in the navigator table
	 * @param guid the GUID of the record
	 * @param uploadedBy id of the user who have uploaded this record
	 * @param noOfSlices  number of slices (Z-positions) 
	 * @param noOfFrames number of frames
	 * @param noOfChannels number of channels
	 * @param noOfSites number of sites
	 * @param imageWidth image width in pixels
	 * @param imageHeight image height in pixels
	 * @param uploadTime time when the record was uploaded
	 * @param sourceTime last modification time of the source files
	 * @param creationTime when the record was created with the acquisition software
	 * @param acquiredTime when the source files are generated from the microscopes
	 * @param imageDepth pixel depth (bytes per pixel)
	 * @param xPixelSize pixel size in microns in x dimension
	 * @param yPixelSize pixel size in microns in y dimension
	 * @param zPixelSize pixel size in microns in z dimension
	 * @param sourceType name of the format
	 * @param imageType image type
	 * @param machineIP IP address of the acquisition machine
	 * @param macAddress MAC address of the acquisition machine
	 * @param sourceFolder source directory
	 * @param sourceFilename seed file name
	 */
	public void registerRecord(long guid, String uploadedBy, 
			int noOfSlices, int noOfFrames, int noOfChannels, int noOfSites, 
			int imageWidth, int imageHeight,
			Long uploadTime, Long sourceTime, Long creationTime, Long acquiredTime,
			PixelDepth imageDepth, double xPixelSize, double yPixelSize, double zPixelSize,
			SourceFormat sourceType, ImageType imageType, 
			String machineIP, String macAddress,
			String sourceFolder, String sourceFilename) throws DataAccessException;
	
	/**
	 * remove specified annotation column from navigation table
	 * @param projectId specified project
	 * @param columnName specified annotation name
	 * @throws DataAccessException
	 */
	public void removeAnnotationColumn(int projectId, String columnName) throws DataAccessException;
	
	/**
	 * remove specified annotation column from navigation info table
	 * @param projectId specified project
	 * @param columnName specified annotation name
	 * @throws DataAccessException
	 */
	public void removeAnnotationColumnFromInfo(int projectId, String columnName) throws DataAccessException;

	/**
	 * delete record from navigation table
	 * @param guid of specified record
	 * @throws DataAccessException 
	 */
	public void deleteRecord(long guid) throws DataAccessException;

	/**
	 * inserts storage location for record where it is stored physically on server
	 * @param guid specified record
	 * @param storageLocation storage location
	 * @throws DataAccessException
	 */
	void insertStorageLocation(long guid, String storageLocation) throws DataAccessException;
	
	/**
	 * inserts microscope name for the record
	 * @param guid
	 * @param microscopeName
	 * @throws DataAccessException
	 */
	void insertMicroscopeName(long guid, String microscopeName) throws DataAccessException;

	/**
	 * apply acquisition profile for record
	 * @param guid specified record
	 * @param acqProfile specified acquisition profile
	 * @throws DataAccessException 
	 */
	public void applyAcquisitionProfile(long guid, AcquisitionProfile acqProfile) throws DataAccessException;
}
