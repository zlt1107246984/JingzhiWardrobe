package com.qf.model;

/**
 * Created by Administrator on 15-11-23.
 */
public class DapeiEntity {
    private String strMainLogo;
    private String strTitle;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStrMainLogo() {
        return strMainLogo;
    }

    public void setStrMainLogo(String strMainLogo) {
        this.strMainLogo = strMainLogo;
    }

    public String getStrTitle() {
        return strTitle;
    }

    public void setStrTitle(String strTitle) {
        this.strTitle = strTitle;
    }

    @Override
    public String toString() {
        return "DapeiEntity{" +
                "strMainLogo='" + strMainLogo + '\'' +
                ", strTitle='" + strTitle + '\'' +
                ", type=" + type +
                '}';
    }
}
