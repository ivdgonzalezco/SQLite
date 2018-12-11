package com.sqlite.sqlite;

public class Company {

    private Integer id;
    private String name;
    private String url;
    private String phone;
    private String email;
    private String productsServices;
    private String Classification;

    public Company(Integer id, String name, String url, String phone, String email, String productsServices, String classification) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.phone = phone;
        this.email = email;
        this.productsServices = productsServices;
        Classification = classification;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProductsServices() {
        return productsServices;
    }

    public void setProductsServices(String productsServices) {
        this.productsServices = productsServices;
    }

    public String getClassification() {
        return Classification;
    }

    public void setClassification(String classification) {
        Classification = classification;
    }
}
