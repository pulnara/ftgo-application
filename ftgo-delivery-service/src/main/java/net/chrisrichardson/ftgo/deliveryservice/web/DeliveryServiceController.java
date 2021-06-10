package net.chrisrichardson.ftgo.deliveryservice.web;

import net.chrisrichardson.ftgo.deliveryservice.api.web.CourierAvailability;
import net.chrisrichardson.ftgo.deliveryservice.domain.Courier;
import net.chrisrichardson.ftgo.deliveryservice.domain.DeliveryService;
import net.chrisrichardson.ftgo.deliveryservice.api.web.DeliveryStatus;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.stream.Collectors.toList;

@RestController
public class DeliveryServiceController {

    private DeliveryService deliveryService;

    public DeliveryServiceController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @RequestMapping(path = "/couriers/{courierId}", method = RequestMethod.POST)
    public CreateCourierResponse createCourier(@PathVariable long courierId) {
        Courier courier = deliveryService.findOrCreateCourier(courierId);
        return new CreateCourierResponse(courier.getId());
    }

    @RequestMapping(path = "/couriers/{courierId}", method = RequestMethod.GET)
    public ResponseEntity<GetCourierResponse> getCourier(@PathVariable long courierId) {
        return deliveryService.findCourier(courierId)
                .map(courier -> new ResponseEntity<>(new GetCourierResponse(courier.getId(), courier.isAvailable()), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(path = "/couriers", method = RequestMethod.GET)
    public ResponseEntity<GetCouriersResponse> getAllCouriers() {
        return new ResponseEntity<>(
                new GetCouriersResponse(deliveryService.getAllCouriers()
                        .stream()
                        .map(this::makeGetCourierResponse)
                        .collect(toList())),
                HttpStatus.OK
        );
    }

    @RequestMapping(path = "/couriers/{courierId}/availability", method = RequestMethod.POST)
    public void updateCourierLocation(@PathVariable long courierId, @RequestBody CourierAvailability availability) {
        deliveryService.updateAvailability(courierId, availability.isAvailable());
    }

    @RequestMapping(path = "/deliveries/{deliveryId}", method = RequestMethod.GET)
    public ResponseEntity<DeliveryStatus> getDeliveryStatus(@PathVariable long deliveryId) {
        return deliveryService.getDeliveryInfo(deliveryId).map(ds -> new ResponseEntity<>(ds, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    private GetCourierResponse makeGetCourierResponse(Courier courier) {
        return new GetCourierResponse(courier.getId(), courier.isAvailable());
    }

}
