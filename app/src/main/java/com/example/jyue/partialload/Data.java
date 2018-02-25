package com.example.jyue.partialload;

/**
 * Created by Jyue on 2018/2/25.
 */

public class Data {
    private String County;
    private String PublishAgency;
    private String SiteName;
    private String Uvi;

    public void setCounty(String county) {
        County = county;
    }

    public String getCounty() {
        return County;
    }

    public void setPublishAgency(String publishAgency) {
        PublishAgency = publishAgency;
    }

    public String getPublishAgency() {
        return PublishAgency;
    }

    public void setSiteName(String siteName) {
        SiteName = siteName;
    }

    public String getSiteName() {
        return SiteName;
    }

    public void setUvi(String uvi) {
        Uvi = uvi;
    }

    public String getUvi() {
        return Uvi;
    }
}
