package de.ba.tempstation.configuration;

import de.ba.tempstation.client.WebSocketClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfiguration {
    @Bean
    public WebSocketClient createWebSocketClient() {
        return new WebSocketClient();
    }
}
