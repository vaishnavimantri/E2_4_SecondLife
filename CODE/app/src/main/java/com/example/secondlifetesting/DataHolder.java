package com.example.secondlifetesting;
public class DataHolder {
    String cate,desc,seore,phne;

    public DataHolder(String desc, String seore, String phne, String cate) {
        this.cate = cate;
        this.desc = desc;
        this.seore = seore;
        this.phne = phne;

    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSeore() {
        return seore;
    }

    public void setSeore(String seore) {
        this.seore = seore;
    }

    public String getPhne() {
        return phne;
    }

    public void setPhne(String phne) {
        this.phne = phne;
    }
}
