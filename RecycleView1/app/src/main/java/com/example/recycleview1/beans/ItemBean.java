package com.example.recycleview1.beans;
public class ItemBean {
    private  int icon;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    @Override
    public String toString() {
        return "ItemBean{" +
                "icon=" + icon +
                ", title='" + title + '\'' +
                '}';
    }
}
