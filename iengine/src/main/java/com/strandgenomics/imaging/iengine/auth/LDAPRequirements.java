/*
 * LDAPRequirements.java
 *
 * AVADIS Image Management System
 * Server Implementation
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
package com.strandgenomics.imaging.iengine.auth;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mantri This class is basically to hold all the requirements that
 *         might be required for authentication using the LDAP server and also
 *         to perform search on it .
 */
public class LDAPRequirements {

	private String m_name = null;
	private String m_hostip = null;
	private String m_port = null;
	private String m_initial_context_factory = null;
	private String m_adminDN = null;
	private String m_password = null;
	private String m_root = null;
	private List<String> m_list = null;

	public LDAPRequirements() {
		m_list = new ArrayList<String>();
	}

	public LDAPRequirements(String name, String hostip, String port,
			String initial_context_factory, String adminDN, String password,
			String root, List<String> list) {
		m_name = name;
		m_hostip = hostip;
		m_port = port;
		m_initial_context_factory = initial_context_factory;
		m_adminDN = adminDN;
		m_password = password;
		m_root = root;
		m_list = new ArrayList<String>();

		for (String searchPath : list) {
			addLDAPSearchablePath(searchPath);
		}
	}

	public void setName(String name) {
		m_name = name;

	}

	public String getName() {
		return m_name;
	}

	public void setHostip(String hostip) {
		m_hostip = hostip;

	}

	public String getHostip() {
		return m_hostip;
	}

	public void setPort(String port) {
		m_port = port;

	}

	public String getPort() {
		return m_port;
	}

	public void setRoot(String root) {
		m_root = root;

	}

	public String getRoot() {
		return m_root;
	}

	public void setInitial_Context_Factory(String initial_context_factory) {
		m_initial_context_factory = initial_context_factory;
	}

	public String getInitial_Context_Factory() {
		return m_initial_context_factory;
	}

	public void setAdminDN(String adminDN) {
		m_adminDN = adminDN;

	}

	public String getAdminDN() {
		return m_adminDN;
	}

	public void setPassword(String password) {
		m_password = password;

	}

	public String getPassword() {
		return m_password;
	}

	public void addLDAPSearchablePath(String ldapPath) {
		if (ldapPath == null || ldapPath.trim().length() == 0) {
			return;
		}
		m_list.add(ldapPath);
	}

	public List<String> getList() {
		return (m_list);
	}
}