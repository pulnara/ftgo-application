package net.chrisrichardson.ftgo.kitchenservice.web;

import java.util.List;

public class GetTicketsResponse {
    private List<GetTicketResponse> tickets;

    public List<GetTicketResponse> getTickets() {
        return tickets;
    }

    public void setTickets(List<GetTicketResponse> tickets) {
        this.tickets = tickets;
    }

    public GetTicketsResponse(List<GetTicketResponse> tickets) {
        this.tickets = tickets;
    }
}
