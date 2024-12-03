package com.aguas.srv_notification.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class EmailConfigTest {

    @InjectMocks
    private EmailConfig emailConfig;

    @Test
    void mailSender_WhenValidProperties_ShouldCreateMailSender() {
        // Arrange
        ReflectionTestUtils.setField(emailConfig, "mailHost", "smtp.test.com");
        ReflectionTestUtils.setField(emailConfig, "mailPort", "587");
        ReflectionTestUtils.setField(emailConfig, "mailUsername", "test@test.com");
        ReflectionTestUtils.setField(emailConfig, "mailPassword", "password");
        ReflectionTestUtils.setField(emailConfig, "mailProtocol", "smtp");
        ReflectionTestUtils.setField(emailConfig, "smtpAuth", "true");
        ReflectionTestUtils.setField(emailConfig, "starttlsEnable", "true");
        ReflectionTestUtils.setField(emailConfig, "starttlsRequired", "true");
        ReflectionTestUtils.setField(emailConfig, "sslEnable", "false");
        ReflectionTestUtils.setField(emailConfig, "mailDebug", "true");
        ReflectionTestUtils.setField(emailConfig, "connectionTimeout", "5000");
        ReflectionTestUtils.setField(emailConfig, "timeout", "5000");
        ReflectionTestUtils.setField(emailConfig, "writeTimeout", "5000");

        // Act
        JavaMailSender mailSender = emailConfig.mailSender();

        // Assert
        assertNotNull(mailSender);
    }

    @Test
    void mailSender_WhenInvalidPort_ShouldThrowNumberFormatException() {
        // Arrange
        ReflectionTestUtils.setField(emailConfig, "mailHost", "smtp.test.com");
        ReflectionTestUtils.setField(emailConfig, "mailPort", "invalid");
        ReflectionTestUtils.setField(emailConfig, "mailUsername", "test@test.com");
        ReflectionTestUtils.setField(emailConfig, "mailPassword", "password");
        ReflectionTestUtils.setField(emailConfig, "mailProtocol", "smtp");

        // Act & Assert
        assertThrows(NumberFormatException.class, () -> emailConfig.mailSender());
    }
}