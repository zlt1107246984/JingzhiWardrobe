package com.qf.model;

/**
 * Created by Administrator on 15-11-24.
 */
public class mainEntity {
    private String mainlogo;
    private String title;
    private int type;

    public String getMainlogo() {
        return mainlogo;
    }

    public void setMainlogo(String mainlogo) {
        this.mainlogo = mainlogo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "mainEntity{" +
                "mainlogo='" + mainlogo + '\'' +
                ", title='" + title + '\'' +
                ", type=" + type +
                '}';
    }
}
