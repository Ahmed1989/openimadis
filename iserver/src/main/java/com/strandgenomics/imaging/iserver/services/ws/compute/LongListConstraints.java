/**
 * LongListConstraints.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.strandgenomics.imaging.iserver.services.ws.compute;

public class LongListConstraints  extends com.strandgenomics.imaging.iserver.services.ws.compute.Constraints  implements java.io.Serializable {
    private java.lang.Long[] validValues;

    public LongListConstraints() {
    }

    public LongListConstraints(
           java.lang.Long[] validValues) {
        this.validValues = validValues;
    }


    /**
     * Gets the validValues value for this LongListConstraints.
     * 
     * @return validValues
     */
    public java.lang.Long[] getValidValues() {
        return validValues;
    }


    /**
     * Sets the validValues value for this LongListConstraints.
     * 
     * @param validValues
     */
    public void setValidValues(java.lang.Long[] validValues) {
        this.validValues = validValues;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof LongListConstraints)) return false;
        LongListConstraints other = (LongListConstraints) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = super.equals(obj) && 
            ((this.validValues==null && other.getValidValues()==null) || 
             (this.validValues!=null &&
              java.util.Arrays.equals(this.validValues, other.getValidValues())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = super.hashCode();
        if (getValidValues() != null) {
            for (int i=0;
                 i<java.lang.reflect.Array.getLength(getValidValues());
                 i++) {
                java.lang.Object obj = java.lang.reflect.Array.get(getValidValues(), i);
                if (obj != null &&
                    !obj.getClass().isArray()) {
                    _hashCode += obj.hashCode();
                }
            }
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(LongListConstraints.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("urn:icompute", "LongListConstraints"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("validValues");
        elemField.setXmlName(new javax.xml.namespace.QName("", "validValues"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://schemas.xmlsoap.org/soap/encoding/", "long"));
        elemField.setNillable(true);
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
