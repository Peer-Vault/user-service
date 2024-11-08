package com.peer.vault.user_service.messaging;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.peer.vault.user_service.domain.UserCredential;
import com.peer.vault.user_service.utils.KafkaConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void producerForRegistration(UserCredential credential){
        try {
            String userJson = objectMapper.writeValueAsString(credential);
            kafkaTemplate.send(KafkaConstants.USER_REGISTRATION, userJson);
            System.out.println("Sent user registration data to Kafka topic: " + credential.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
