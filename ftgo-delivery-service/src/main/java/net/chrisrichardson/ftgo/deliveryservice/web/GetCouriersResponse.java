package net.chrisrichardson.ftgo.deliveryservice.web;

import java.util.List;

public class GetCouriersResponse {
    private List<GetCourierResponse> couriers;

    public List<GetCourierResponse> getCouriers() {
        return couriers;
    }

    public void setCouriers(List<GetCourierResponse> couriers) {
        this.couriers = couriers;
    }

    public GetCouriersResponse(List<GetCourierResponse> couriers) {
        this.couriers = couriers;
    }
}
