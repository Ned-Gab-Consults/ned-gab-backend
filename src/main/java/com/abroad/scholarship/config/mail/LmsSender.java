package com.abroad.scholarship.config.mail;


import com.abroad.scholarship.dto.EmailSenderDto;

public interface LmsSender {
    void send(EmailSenderDto dto);
}
