package com.abroad.scholarship.config.mail;


import com.abroad.scholarship.dto.EmailSenderDto;

public interface NedGabSender {
    void send(EmailSenderDto dto);
}
