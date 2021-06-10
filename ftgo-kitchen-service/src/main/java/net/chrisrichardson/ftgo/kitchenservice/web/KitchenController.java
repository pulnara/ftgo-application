package net.chrisrichardson.ftgo.kitchenservice.web;

import net.chrisrichardson.ftgo.kitchenservice.api.web.TicketAcceptance;
import net.chrisrichardson.ftgo.kitchenservice.domain.KitchenService;
import net.chrisrichardson.ftgo.kitchenservice.domain.Ticket;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.stream.Collectors.toList;

@RestController
public class KitchenController {

  private KitchenService kitchenService;

  public KitchenController(KitchenService kitchenService) {
    this.kitchenService = kitchenService;
  }

  @RequestMapping(path="/tickets/{ticketId}/accept", method=RequestMethod.POST)
  public void acceptTicket(@PathVariable long ticketId, @RequestBody TicketAcceptance ticketAcceptance) {
    kitchenService.accept(ticketId, ticketAcceptance.getReadyBy());
  }

  @RequestMapping(path="/tickets/{ticketId}/startPreparing", method=RequestMethod.POST)
  public void startPreparingTicket(@PathVariable long ticketId) {
    kitchenService.preparing(ticketId);
  }

  @RequestMapping(path="/tickets/{ticketId}/readyForPickup", method=RequestMethod.POST)
  public void finishPreparingTicket(@PathVariable long ticketId) {
    kitchenService.readyForPickup(ticketId);
  }

  @RequestMapping(path="/tickets", method=RequestMethod.GET)
  public ResponseEntity<GetTicketsResponse> getAllTickets() {
    return new ResponseEntity<>(
            new GetTicketsResponse(kitchenService.getAllTickets()
                    .stream()
                    .map(this::makeGetTicketResponse)
                    .collect(toList())),
            HttpStatus.OK
    );
  }

  private GetTicketResponse makeGetTicketResponse(Ticket ticket) {
    return new GetTicketResponse(ticket.getId(), ticket.getRestaurantId(), ticket.getState());
  }

}
