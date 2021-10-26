package com.example.secondlifetesting;

public class UploadItemDetails {
public String price,aname,cname;

    public UploadItemDetails(String price, String aname, String cname) {

        this.price = price;
        this.aname = aname;
        this.cname = cname;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }
}
