package com.abroad.scholarship.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class EmailSenderDto {
    private String to;
    private String subject;
    private String content;
}
