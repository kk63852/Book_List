package com.example.lenovo.test_application;

public class books {
    private String bookTitle;
    private String authorName;
    private String imageSource;
    public books(String mbookTitle,String mauthorName,String mimageSource){
bookTitle=mbookTitle;
authorName=mauthorName;
imageSource=mimageSource;
    }
    public books(){

    }
    public String getBookTitle(){return bookTitle;}
    public String getAuthorName(){return authorName;}
    public String getImageSource(){return imageSource;}
}
