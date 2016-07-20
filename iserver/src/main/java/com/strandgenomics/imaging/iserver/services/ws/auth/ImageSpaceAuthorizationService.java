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

/**
 * ImageSpaceAuthorizationService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.strandgenomics.imaging.iserver.services.ws.auth;

public interface ImageSpaceAuthorizationService extends javax.xml.rpc.Service {
    public java.lang.String getiAuthAddress();

    public com.strandgenomics.imaging.iserver.services.ws.auth.ImageSpaceAuthorization getiAuth() throws javax.xml.rpc.ServiceException;

    public com.strandgenomics.imaging.iserver.services.ws.auth.ImageSpaceAuthorization getiAuth(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
