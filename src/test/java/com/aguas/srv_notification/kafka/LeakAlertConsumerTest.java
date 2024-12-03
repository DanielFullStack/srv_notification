package com.aguas.srv_notification.kafka;

import com.aguas.srv_notification.model.PressureReading;
import com.aguas.srv_notification.service.NotificationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

public class LeakAlertConsumerTest {

    @Mock
    private NotificationService notificationService;

    @InjectMocks
    private LeakAlertConsumer leakAlertConsumer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldProcessLeakAlertAndSendNotification() {
        // Given
        PressureReading alert = new PressureReading();

        // When
        leakAlertConsumer.consume(alert);

        // Then
        verify(notificationService).sendNotification(alert);
    }
}
