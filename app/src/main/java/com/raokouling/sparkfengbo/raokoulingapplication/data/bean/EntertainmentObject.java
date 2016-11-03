package com.raokouling.sparkfengbo.raokoulingapplication.data.bean;

/**
 * Created by fengbo on 2016/11/2.
 */

public abstract class EntertainmentObject {

    private String id;
    private String content;
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
