package com.zhs.haoyangde.entity;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT. Enable "keep" sections if you want to edit. 
/**
 * Entity mapped to table PRODUCT.
 */
public class Product {

    private Long pid;
    private Integer number;

    public Product() {
    }

    public Product(Long pid) {
        this.pid = pid;
    }

    public Product(Long pid, Integer number) {
        this.pid = pid;
        this.number = number;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

}
