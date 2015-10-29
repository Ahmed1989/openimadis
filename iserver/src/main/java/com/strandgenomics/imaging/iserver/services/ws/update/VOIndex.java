/**
 * VOIndex.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Sep 25, 2006 (02:39:47 GMT+05:30) WSDL2Java emitter.
 */

package com.strandgenomics.imaging.iserver.services.ws.update;

public class VOIndex  implements java.io.Serializable {
    private int frame;

    private int site;

    private int slice;

    public VOIndex() {
    }

    public VOIndex(
           int frame,
           int site,
           int slice) {
           this.frame = frame;
           this.site = site;
           this.slice = slice;
    }


    /**
     * Gets the frame value for this VOIndex.
     * 
     * @return frame
     */
    public int getFrame() {
        return frame;
    }


    /**
     * Sets the frame value for this VOIndex.
     * 
     * @param frame
     */
    public void setFrame(int frame) {
        this.frame = frame;
    }


    /**
     * Gets the site value for this VOIndex.
     * 
     * @return site
     */
    public int getSite() {
        return site;
    }


    /**
     * Sets the site value for this VOIndex.
     * 
     * @param site
     */
    public void setSite(int site) {
        this.site = site;
    }


    /**
     * Gets the slice value for this VOIndex.
     * 
     * @return slice
     */
    public int getSlice() {
        return slice;
    }


    /**
     * Sets the slice value for this VOIndex.
     * 
     * @param slice
     */
    public void setSlice(int slice) {
        this.slice = slice;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof VOIndex)) return false;
        VOIndex other = (VOIndex) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            this.frame == other.getFrame() &&
            this.site == other.getSite() &&
            this.slice == other.getSlice();
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        _hashCode += getFrame();
        _hashCode += getSite();
        _hashCode += getSlice();
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(VOIndex.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:iupdate", "VOIndex"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("frame");
        elemField.setXmlName(new javax.xml.namespace.QName("", "frame"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("site");
        elemField.setXmlName(new javax.xml.namespace.QName("", "site"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("slice");
        elemField.setXmlName(new javax.xml.namespace.QName("", "slice"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "int"));
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}