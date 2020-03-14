package com.example.retrofitdemo2;

public class JsonResult {

    /**
     * user_name : 李四
     * password : 100180915
     * user_image : http://47.93.186.137/img/img1.jpg
     */

    private String user_name;
    private String password;
    private String user_image;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    @Override
    public String toString() {
        return "JsonResult{" +
                "user_name='" + user_name + '\'' +
                ", password='" + password + '\'' +
                ", user_image='" + user_image + '\'' +
                '}';
    }
}
