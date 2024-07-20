//package com.peer.vault.user_service.messaging;
//
//
//import com.peer.vault.user_service.constant.KafkaConstant;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class KafkaProducerService {
//
//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate;
//
//    public void sendNotificationRegistration(String email) {
//        kafkaTemplate.send(KafkaConstant.USER_REGISTRATION, email);
//    }
//
//}
