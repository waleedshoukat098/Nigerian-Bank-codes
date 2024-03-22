package com.techinnovation.nigerianbankcodes.Models;

public class CodeModel {
    String title;
    String code;
    int image;

    public CodeModel(String title, String code, int image) {
        this.title = title;
        this.code = code;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}







