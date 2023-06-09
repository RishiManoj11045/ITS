package com.example.page12;

public class Supervisor {
    private int id ;
    private String iithId ;
    private String name ;
    private String appointedBy;
    private String iithMailId ;
    private String Password ;
    private String contactNumber ;
    private String objectClassUnderSupervision ;
    private String  fieldofoperations ;

    public String getFieldofoperations() {
        return fieldofoperations;
    }

    public void setFieldofoperations(String fieldofoperations) {
        this.fieldofoperations = fieldofoperations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIithId() {
        return iithId;
    }

    public void setIithId(String iithId) {
        this.iithId = iithId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAppointedBy() {
        return appointedBy;
    }

    public void setAppointedBy(String appointedBy) {
        this.appointedBy = appointedBy;
    }

    public String getIithMailId() {
        return iithMailId;
    }

    public void setIithMailId(String iithMailId) {
        this.iithMailId = iithMailId;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getObjectClassUnderSupervision() {
        return objectClassUnderSupervision;
    }

    public void setObjectClassUnderSupervision(String objectClassUnderSupervision) {
        this.objectClassUnderSupervision = objectClassUnderSupervision;
    }
}
