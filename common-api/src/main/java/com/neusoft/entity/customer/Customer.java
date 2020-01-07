package com.neusoft.entity.customer;

public class Customer {
    private int customerId;
    private String customerNumber;
    private String customerPassword;
    private String customerName;
    private int customerIsUsed;
    private String createdBy;
    private String gmtCreate;
    private String lastModifiedBy;
    private String gmtModified;
    private int isDeleted;
    private int sortNo;
    private int version;
    private String customerNewPassword;

    public int getCustomerId() {
        return customerId;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getCustomerIsUsed() {
        return customerIsUsed;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getGmtCreate() {
        return gmtCreate;
    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public String getGmtModified() {
        return gmtModified;
    }

    public int getIsDeleted() {
        return isDeleted;
    }

    public int getSortNo() {
        return sortNo;
    }

    public int getVersion() {
        return version;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public void setCustomerIsUsed(int customerIsUsed) {
        this.customerIsUsed = customerIsUsed;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setGmtCreate(String gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public void setGmtModified(String gmtModified) {
        this.gmtModified = gmtModified;
    }

    public void setIsDeleted(int isDeleted) {
        this.isDeleted = isDeleted;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getCustomerNewPassword() {
        return customerNewPassword;
    }

    public void setCustomerNewPassword(String customerNewPassword) {
        this.customerNewPassword = customerNewPassword;
    }
}