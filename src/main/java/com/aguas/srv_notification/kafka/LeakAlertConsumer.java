package com.aguas.srv_notification.kafka;

import com.aguas.srv_notification.model.PressureReading;
import com.aguas.srv_notification.service.NotificationService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class LeakAlertConsumer {

    private static final Logger log = LoggerFactory.getLogger(LeakAlertConsumer.class);

    @Autowired
    private NotificationService notificationService;

    @KafkaListener(topics = "leakage-alerts", groupId = "notification-group")
    public void consume(PressureReading alert) {
        log.info("Received leak alert: {}", alert);

        // Processa o alerta recebido
        notificationService.sendNotification(alert);
    }
}
