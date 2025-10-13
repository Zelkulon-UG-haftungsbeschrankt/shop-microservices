/**
 * created on 13.10.25
 * by hdi10 with IntelliJ IDEA
 * for email-microservice
 * path to this file is: src/main/java/com/zelkulon/email_microservice/core/model
 **/
package com.zelkulon.email_microservice.core.model;

import lombok.Getter;

public class Email {

@Getter
private String receiver;
@Getter
private String subject;
@Getter
private String content;

    public Email(String receiver, String subject, String content) {
        this.receiver = receiver;
        this.subject = subject;
        this.content = content;
    }
}
