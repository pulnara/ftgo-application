package net.chrisrichardson.ftgo.kitchenservice.web;

import net.chrisrichardson.ftgo.kitchenservice.domain.TicketState;

public class GetTicketResponse {
    private Long id;
    private Long restaurantId;
    private TicketState state;


    public GetTicketResponse(Long id, Long restaurantId, TicketState ticketState) {
        this.id = id;
        this.restaurantId = restaurantId;
        this.state = ticketState;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public TicketState getState() {
        return state;
    }

    public void setState(TicketState state) {
        this.state = state;
    }

}
