package de.workshops.bookdemo.config;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("prod2")
public class PortTest {
    
    @Value("${server.port}")
    private Integer port;

    @Test
    void testPort() {
        assertEquals(8090, port);
    }
}
