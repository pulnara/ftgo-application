package net.chrisrichardson.ftgo.deliveryservice.web;

import net.chrisrichardson.ftgo.common.PersonName;

public class GetCourierResponse {
    private long id;
    private boolean available;

    public boolean getAvailable() {
        return available;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public GetCourierResponse(long id, boolean available) {
        this.id = id;
        this.available = available;
    }
}
