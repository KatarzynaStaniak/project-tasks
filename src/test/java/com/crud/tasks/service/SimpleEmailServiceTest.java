package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;


import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void shouldSendEmail() {
        //Given
        Mail mail = new Mail("test@test.com", "Test", "Test Message", null);

//        MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
//            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
//            messageHelper.setTo(mail.getMailTo());
//            messageHelper.setSubject(mail.getSubject());
//            messageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()), true);
//            messageHelper.setCc(mail.getToCc());
//        };

        //When
        simpleEmailService.send(mail);

        //Then
        verify(javaMailSender, times(1)).send(any(MimeMessagePreparator.class));
    }

}