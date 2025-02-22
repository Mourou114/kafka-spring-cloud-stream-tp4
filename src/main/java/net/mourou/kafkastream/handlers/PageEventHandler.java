package net.mourou.kafkastream.handlers;


import net.mourou.kafkastream.events.PageEvent;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

@Component
public class PageEventHandler {
    @Bean
    public Consumer<PageEvent> pageEventConsumer(){
        return (input)->{
            System.out.println("************");
            System.out.println(input.toString());
            System.out.println("************");
        };
    }
    @Bean
    public Supplier<PageEvent> pageEventSupplier(){
        return ()->{
            return new PageEvent(
                    Math.random()>0.5?"Page1":"Page3",
                    Math.random()>0.5?"User2":"User4",
                    new Date(),
                    15+new Random().nextInt(1111)
            );
        };
    }
    @Bean
    public Function<KStream<String, PageEvent>, KStream<String, Long>> kStreamFunction(){
        return (input)->
                input.filter((k,v)->v.duration()>100)
                        .map((k,v)->new KeyValue<>(v.name(), v.duration()));


    }
}
