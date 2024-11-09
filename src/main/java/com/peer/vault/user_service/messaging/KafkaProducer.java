package com.peer.vault.user_service.messaging;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.peer.vault.user_service.domain.UserCredential;
import com.peer.vault.user_service.utils.KafkaConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void producerForRegistration(UserCredential credential){
        try {
            Map<String, String> userRegistration = new HashMap<>();
            userRegistration.put("email", credential.getEmail());
            userRegistration.put("first", credential.getFirstName());
            userRegistration.put("last", credential.getLastName());
            String userJson = objectMapper.writeValueAsString(userRegistration);

            kafkaTemplate.send(KafkaConstants.USER_REGISTRATION, userJson);
            System.out.println("Sent user registration data to Kafka topic: " + credential.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
