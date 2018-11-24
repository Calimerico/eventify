package com;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by spasoje on 23-Nov-18.
 */
@RestController
public class Producer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @GetMapping(value = "postMsg")
    public void send() {
        kafkaTemplate.send("cqrs", "Some message");
    }

    @GetMapping(value = "pera")
    public void send2() {
        System.out.println("proba proba u konzolu");
    }
}
