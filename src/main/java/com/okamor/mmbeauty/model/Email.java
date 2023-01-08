package com.okamor.mmbeauty.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Email {
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
