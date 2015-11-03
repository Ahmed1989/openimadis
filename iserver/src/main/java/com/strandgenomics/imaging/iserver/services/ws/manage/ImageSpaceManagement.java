/**
 * ImageSpaceManagement.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.strandgenomics.imaging.iserver.services.ws.manage;

public interface ImageSpaceManagement extends java.rmi.Remote {
    public void transfer(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException;
    public boolean createInternalUser(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3, java.lang.String in4, java.lang.String in5) throws java.rmi.RemoteException;
    public boolean allowExternalUser(java.lang.String in0, java.lang.String in1, java.lang.String in2, java.lang.String in3) throws java.rmi.RemoteException;
    public com.strandgenomics.imaging.iserver.services.ws.manage.Task restoreProject(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException;
    public boolean createNewProject(java.lang.String in0, java.lang.String in1, java.lang.String in2, double in3) throws java.rmi.RemoteException;
    public com.strandgenomics.imaging.iserver.services.ws.manage.User[] getProjectMembers(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException;
    public com.strandgenomics.imaging.iserver.services.ws.manage.User[] getProjectManager(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException;
    public com.strandgenomics.imaging.iserver.services.ws.manage.Task archiveProject(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException;
    public void addProjectMembers(java.lang.String in0, java.lang.String in1, java.lang.String[] in2, java.lang.String in3) throws java.rmi.RemoteException;
    public void deleteArchive(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException;
    public void deleteRecord(java.lang.String in0, long in1) throws java.rmi.RemoteException;
    public com.strandgenomics.imaging.iserver.services.ws.manage.Task deleteProject(java.lang.String in0, java.lang.String in1) throws java.rmi.RemoteException;
    public boolean renameProject(java.lang.String in0, java.lang.String in1, java.lang.String in2) throws java.rmi.RemoteException;
    public java.lang.String getJobStatus(java.lang.String in0, com.strandgenomics.imaging.iserver.services.ws.manage.Task in1) throws java.rmi.RemoteException;
}
