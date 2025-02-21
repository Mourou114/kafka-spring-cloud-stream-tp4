package net.mourou.kafkastream.controllers;

import net.mourou.kafkastream.events.PageEvent;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController
public class PageEventController {
    private StreamBridge streamBridge;

    public PageEventController(StreamBridge streamBridge) {
        this.streamBridge = streamBridge;
    }
    @GetMapping("/publish")
    public PageEvent publish(String name, String topic){
        PageEvent event = new PageEvent(
                name,
                Math.random()>0.5?"User1":"User2",
                new Date(),
                10+new Random().nextInt(1000));
        streamBridge.send(topic, event);
        return event;
    }





}
