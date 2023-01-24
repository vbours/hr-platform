package com.boursinos.hrplatform;

import com.boursinos.hrplatform.clients.MinioClients;
import com.boursinos.hrplatform.clients.MinioClientsImpl;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MockConfig {

    @Bean
    @Primary
    public MinioClients minioClients() {
        return Mockito.mock(MinioClientsImpl.class);
    }

}