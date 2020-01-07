package com.neusoft.entity.customer.vo;

public class CustomerSelect {
     int   customer_id;
    String customer_number;
    String  customer_name;
    int customer_is_used;
    int  version;

    public int getCustomer_id() {
        return customer_id;
    }

    public String getCustomer_number() {
        return customer_number;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public int getCustomer_is_used() {
        return customer_is_used;
    }

    public int getVersion() {
        return version;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public void setCustomer_number(String customer_number) {
        this.customer_number = customer_number;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public void setCustomer_is_used(int customer_is_used) {
        this.customer_is_used = customer_is_used;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
