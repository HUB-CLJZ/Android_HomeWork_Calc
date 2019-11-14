package com.example.contentproviderproject;

public class SmsInfo {
    private int _id;
    private String adress;
    private int type;
    private String body;
    private long date;

    public SmsInfo(int _id, String adress, int type, String body, long date) {
        this._id = _id;
        this.adress = adress;
        this.type = type;
        this.body = body;
        this.date = date;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
