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

import java.util.List;
import java.util.Map;

import com.strandgenomics.imaging.icore.app.Priority;
import com.strandgenomics.imaging.icore.app.State;
import com.strandgenomics.imaging.icore.db.DataAccessException;
import com.strandgenomics.imaging.iengine.compute.Task;

/**
 * Base class for ActiveTaskDAO, ArchievedTaskDAO 
 * Common functions for ActiveTask, ArchivedTask should go in this interface
 * @author Devendra
 */
public interface TaskDAO 
{	
	/**
	 * inserts the task in DB
	 * @param jobID specified jobID
	 * @param appName name of application 
	 * @param appVersion version of application 
	 * @param isMonitored is the task under owner's task monitor
	 * @param authcode authcode required for the task
	 * @param owner of the task
	 * @param priority of the task
	 * @param scheduleTime of the task
	 * @param state of the task
	 * @param parameters required for the task
	 * @param inputs guids for the task
	 * @throws DataAccessException 
	 */
	public void insertTask(long jobID, String appName, String appVersion, int projectID, boolean isMonitored, String authcode, long authID, String owner, Priority priority, long scheduleTime, State state, Map<String, String> parameters, long[] inputs) throws DataAccessException;
	
	/**
	 * sets the task as monitored for specified user
	 * @param userName specified user
	 * @param id task id
	 * @throws DataAccessException 
	 */
	public void setMonitored(String userName, long id) throws DataAccessException;
	
	/**
	 * returns the task for given id
	 * @param jobID specified job id
	 * @return the task for given id
	 * @throws DataAccessException 
	 */
	public Task getTask(long jobID) throws DataAccessException;
	
	/**
	 * returns tasks for given status
	 * @param state
	 * @return tasks for given status
	 * @throws DataAccessException 
	 */
	public List<Task> getTasks(State state) throws DataAccessException;
	
	/**
	 * returns tasks for given status
	 * @param state
	 * @return tasks for given status
	 * @throws DataAccessException 
	 */
	public List<Task> getAllTasks() throws DataAccessException;
	
	/**
	 * returns all the monitored tasks for user
	 * @param user specified user
	 * @return list of all monitored tasks
	 * @throws DataAccessException
	 */
	public List<Task> getMonitoredTasks(String user) throws DataAccessException;
	
	
	/**
	 * Clears is_monitored for given task
	 * @param taskId
	 * @throws DataAccessException
	 */
	public void clearIsMonitored(long taskId) throws DataAccessException;
}
