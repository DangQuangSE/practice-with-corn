package dev.practice.integrations.http;

import java.net.http.HttpClient;
import java.time.Duration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.web.client.RestClient;

@Configuration
public class ExternalRestClientConfiguration {
    @Bean
    RestClient externalRestClient(RestClient.Builder builder,
                                 @Value("${practice.external-base-url:http://localhost:8099}") String baseUrl) {
        HttpClient client = HttpClient.newBuilder().connectTimeout(Duration.ofSeconds(2)).build();
        JdkClientHttpRequestFactory requestFactory = new JdkClientHttpRequestFactory(client);
        requestFactory.setReadTimeout(Duration.ofSeconds(3));
        return builder.baseUrl(baseUrl)
                .requestFactory(requestFactory)
                .build();
    }
}
