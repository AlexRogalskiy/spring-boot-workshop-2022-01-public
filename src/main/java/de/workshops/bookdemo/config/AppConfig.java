package de.workshops.bookdemo.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "bookdemo")
@Component
@Data
public class AppConfig {
    
    /**
     * Das ist ein INT param.
     */
    private int intParam;

    private String param2;

    private MailConfig mail;


    @Data
    public static class MailConfig {

        private String host;
        private int port;
    }

}
