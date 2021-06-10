package net.chrisrichardson.ftgo.deliveryservice.web;

public class CreateCourierResponse {
    private long courierId;

    public long getCourierId() {
        return courierId;
    }

    public void setCourierId(long courierId) {
        this.courierId = courierId;
    }

    public CreateCourierResponse() {

    }

    public CreateCourierResponse(long courierId) {
        this.courierId = courierId;
    }
}
