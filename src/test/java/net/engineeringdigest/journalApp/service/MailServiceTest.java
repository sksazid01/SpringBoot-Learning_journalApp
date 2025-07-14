package net.engineeringdigest.journalApp.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MailServiceTest {
    @Autowired
    private MailService mailService;

    @Test
    void testSendEmail() {
        mailService.sendMail("ahasanulhaque20@gmail.com", "Test Subject", "This is a test email");
        // If no exception is thrown, the configuration is working
    }
}