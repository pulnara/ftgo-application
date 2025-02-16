package net.chrisrichardson.ftgo.consumerservice.web;

import io.eventuate.tram.events.publisher.ResultWithEvents;
import net.chrisrichardson.ftgo.consumerservice.domain.Consumer;
import net.chrisrichardson.ftgo.consumerservice.domain.ConsumerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping(path = "/consumers")
public class ConsumerController {

    private ConsumerService consumerService;

    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public CreateConsumerResponse create(@RequestBody CreateConsumerRequest request) {
        ResultWithEvents<Consumer> result = consumerService.create(request.getName());
        return new CreateConsumerResponse(result.result.getId());
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{consumerId}")
    public ResponseEntity<GetConsumerResponse> get(@PathVariable long consumerId) {
        return consumerService.findById(consumerId)
                .map(consumer -> new ResponseEntity<>(new GetConsumerResponse(consumer.getName(), consumerId), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<GetConsumersResponse> get() {
        return new ResponseEntity<>(
                new GetConsumersResponse(consumerService.getAllConsumers()
                        .stream()
                        .map(this::makeGetConsumerResponse)
                        .collect(toList())),
                HttpStatus.OK
        );
    }

    private GetConsumerResponse makeGetConsumerResponse(Consumer consumer) {
        return new GetConsumerResponse(consumer.getName(), consumer.getId());
    }
}
