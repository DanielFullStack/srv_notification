package com.aguas.srv_notification.service;

import com.aguas.srv_notification.model.PressureReading;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import jakarta.mail.MessagingException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class NotificationServiceTest {

    @Mock
    private EmailService emailService;

    @InjectMocks
    private NotificationService notificationService;

    private static final String TEST_EMAIL = "test@example.com";

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(notificationService, "mailReceiver", TEST_EMAIL);
    }

    @Test
    void sendNotification_ShouldSendEmailSuccessfully() throws MessagingException {
        // Arrange
        PressureReading alert = new PressureReading();
        alert.setSensorId("SENSOR-001");
        alert.setVariation(1.5);

        // Act
        notificationService.sendNotification(alert);

        // Assert
        verify(emailService, times(1)).sendEmail(
            eq(TEST_EMAIL),
            eq("Pressure Alert - Sensor SENSOR-001"),
            eq("Alert: Pressure variation detected for sensor SENSOR-001 with variation of 1.5")
        );
    }

    @Test
    void sendNotification_ShouldHandleEmailException() throws MessagingException {
        // Arrange
        PressureReading alert = new PressureReading();
        alert.setSensorId("SENSOR-001");
        alert.setVariation(1.5);

        doThrow(new MessagingException("Failed to send email"))
            .when(emailService)
            .sendEmail(anyString(), anyString(), anyString());

        // Act
        notificationService.sendNotification(alert);

        // Assert
        verify(emailService, times(1)).sendEmail(anyString(), anyString(), anyString());
    }
}