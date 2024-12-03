package com.aguas.srv_notification.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.javamail.JavaMailSender;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class EmailServiceTest {
    
    @Mock
    private JavaMailSender mailSender;
    
    @Mock
    private MimeMessage mimeMessage;
    
    private EmailService emailService;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(mailSender.createMimeMessage()).thenReturn(mimeMessage);
        emailService = new EmailService(mailSender);
    }
    
    @Test
    void sendEmail_Success() throws MessagingException {
        String to = "test@example.com";
        String subject = "Test Subject";
        String text = "Test Message";
        
        doNothing().when(mailSender).send(any(MimeMessage.class));
        
        emailService.sendEmail(to, subject, text);
        
        verify(mailSender, times(1)).createMimeMessage();
        verify(mailSender, times(1)).send(any(MimeMessage.class));
    }
    
    @Test
    void sendEmail_Failure() throws MessagingException {
        String to = "test@example.com";
        String subject = "Test Subject";
        String text = "Test Message";
        
        doThrow(new RuntimeException("Failed to send email"))
            .when(mailSender).send(any(MimeMessage.class));
        
        try {
            emailService.sendEmail(to, subject, text);
        } catch (Exception ignored) {}
        
        verify(mailSender, times(1)).createMimeMessage();
        verify(mailSender, times(1)).send(any(MimeMessage.class));
    }
}
