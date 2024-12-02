package com.aguas.srv_notification.service;

import com.aguas.srv_notification.model.PressureReading;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import jakarta.mail.MessagingException;

@Service
public class NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);

    @Value("${mail.receiver}")
    private String mailReceiver;

    private final EmailService emailService;

    public NotificationService(EmailService emailService) {
        this.emailService = emailService;
    }

    private void handleEmailLeakAlert(PressureReading alert) {
        try {
            String to = mailReceiver;
            String subject = "Pressure Alert - Sensor " + alert.getSensorId();
            String text = String.format("Alert: Pressure variation detected for sensor %s with variation of %s",
                    alert.getSensorId(), alert.getVariation());

            emailService.sendEmail(to, subject, text);
        } catch (MessagingException e) {
            log.error("Failed to send leak alert notification", e);
        }
    }

    public void sendNotification(PressureReading alert) {
        log.info("Sending notification for sensor {}: pressure variation = {}",
                alert.getSensorId(), alert.getVariation());
        handleEmailLeakAlert(alert);
    }
}