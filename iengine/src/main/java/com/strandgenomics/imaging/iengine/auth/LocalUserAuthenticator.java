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

package com.strandgenomics.imaging.iengine.auth;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.strandgenomics.imaging.icore.db.DataAccessException;
import com.strandgenomics.imaging.icore.util.EncryptionUtil;
import com.strandgenomics.imaging.icore.util.Util;
import com.strandgenomics.imaging.iengine.dao.ImageSpaceDAOFactory;
import com.strandgenomics.imaging.iengine.dao.UserDAO;
import com.strandgenomics.imaging.iengine.models.User;

public class LocalUserAuthenticator implements UserAuthenticator {

	protected Logger logger = Logger.getLogger("com.strandgenomics.imaging.iengine.auth");

	@Override
	public boolean authenticateUser(String login, String password) throws DataAccessException 
	{
		if (login == null || password == null) 
		{
			logger.logp(Level.INFO, "LocalUserAuthenticator", "authenticateUser", "incomplete arguments ...");
			return false;
		}

		boolean authenticated = false;

		ImageSpaceDAOFactory factory = ImageSpaceDAOFactory.getDAOFactory();
		UserDAO userDao = factory.getUserDAO();
		User userObj = userDao.findUser(login);

		// in the database, passwords are stored as md5 hash of the original text
		password = Util.toHexString(EncryptionUtil.computeMessageDigest(password));
		
        if( password.equals(userObj.getPassword()) )
        {
            logger.logp(Level.INFO, "LocalUserAuthenticator", "authenticateUser", "validated for "+login);
            authenticated = true;
            userObj.dispose();
        }

		return authenticated;
	}

	@Override
	public boolean changePassword(String login, String oldPassword, String newPassword) throws DataAccessException {
		// TODO Auto-generated method stub
		return false;
	}
}
