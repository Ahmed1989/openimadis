/*
 * UserPreferencesDAO.java
 *
 * AVADIS Image Management System
 * Data Access Components
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
package com.strandgenomics.imaging.iengine.dao;

import java.sql.Timestamp;
import java.util.List;

import com.strandgenomics.imaging.icore.Channel;
import com.strandgenomics.imaging.icore.db.DataAccessException;
import com.strandgenomics.imaging.iengine.models.LegendField;
import com.strandgenomics.imaging.iengine.models.LegendLocation;
import com.strandgenomics.imaging.iengine.models.SearchColumn;

/**
 * Gives access to user preferences about navigator fields and projects
 */
public interface UserPreferencesDAO {
	
	public Timestamp getLastAccessTime(String userLogin, int projectID) 
			throws DataAccessException;

	public int getNumberOfNavigatorBins(String userLogin, int projectID) 
			throws DataAccessException;
	
	public boolean isBinsAreInAscendingOrder(String userLogin, int projectID) 
			throws DataAccessException;
	
	public List<SearchColumn> getSpreadSheetColumns(String userLogin, int projectID) 
			throws DataAccessException;
	
	public List<SearchColumn> getNavigationColumns(String userLogin, int projectID) 
			throws DataAccessException;

	public List<SearchColumn> getOverlayColumns(String userLogin, int projectID) 
			throws DataAccessException;
	
	public int[] getRecentProjects(String userLogin, int maxProjects) 
			throws DataAccessException;
	
	public void setSpreadSheetColumns(String userLogin, int projectID, List<SearchColumn> columns) 
			throws DataAccessException;
	
	public void setNavigationColumns(String userLogin, int projectID, List<SearchColumn> columns) 
			throws DataAccessException;
	
	public void setOverlayColumns(String userLogin, int projectID, List<SearchColumn> columns) 
			throws DataAccessException;

	public void updateProjectUsage(String userLogin, int projectID)
			throws DataAccessException;
	
	public void createDefaultPreference(String userLogin, int projectID);

	/**
	 * Returns the rgb colors for the channels for the specified record
	 * @param userLogin login id of the user
	 * @param guid record GUID
	 * @return list of colors for the channels
	 * @throws DataAccessException
	 */
	public List<Channel> getChannels(String userLogin, long guid) throws DataAccessException;

	/**
	 * Adds the rgb colors for the channels for the specified record
	 * @param userLogin login id of the user
	 * @param guid record GUID
	 * @return list of colors for the channels
	 * @throws DataAccessException
	 */
	public void addChannels(String userLogin, long guid, List<Channel> channels) throws DataAccessException;
	
	/**
	 * Update the rgb colors for the channels for the specified record
	 * @param userLogin login id of the user
	 * @param guid record GUID
	 * @return list of colors for the channels
	 * @throws DataAccessException
	 */
	public void updateChannels(String userLogin, long guid, List<Channel> channels) throws DataAccessException;

	/**
	 * updates the name of channel to new name given for all users
	 * @param guid of specified record
	 * @param channelNo of specified channel
	 * @param newChannelName new name to be given for the channel
	 */
	public void updateChannels(long guid, List<Channel> channels) throws DataAccessException;
	
	/**
	 * Create a query to delete preference relationships from user_recent_projects table
	 * @param projectID int value used as id for the project just deleted
	 * @param userLogin string value used as the login id of the user who has been removed from the project
	 * @throws DataAccessException5:13:11 PM
	 */
	public void deleteUserPreferences(int projectID , String userLogin ) throws DataAccessException ;

	/**
	 * sets set of legends for specified user
	 * @param actorLogin user login
	 * @param chosenFields specified user
	 * @throws DataAccessException 
	 */
	public void setLegendFields(String actorLogin, List<LegendField> chosenFields) throws DataAccessException;

	/**
	 * gets the chosen legend fields for specified user
	 * @param actorLogin user login
	 * @return list of specified legends for specified user
	 */
	public List<LegendField> getLegends(String actorLogin) throws DataAccessException;

	/**
	 * updates the set of legends for specified user
	 * @param actorLogin user login
	 * @param chosenFields specified legend fields
	 * @throws DataAccessException
	 */
	public void updateLegendFields(String actorLogin, List<LegendField> chosenFields) throws DataAccessException;

	/**
	 * get the legend location for specified user
	 * @param actorLogin specified user
	 * @return legend location
	 * @throws DataAccessException
	 */
	public LegendLocation getLegendLocation(String actorLogin) throws DataAccessException;
	
	/**
	 * set the legend location for specified user
	 * @param actorLogin specified user
	 * @param location legend location
	 * @throws DataAccessException
	 */
	public void setLegendLocation(String actorLogin, LegendLocation location) throws DataAccessException;
}