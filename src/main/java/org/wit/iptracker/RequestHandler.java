package org.wit.iptracker;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class RequestHandler {

    private final RequestDetailsRepository repository;
    private final WebClient webClient;

    public Mono<ServerResponse> handleGetRequest(ServerRequest serverRequest) {
        return webClient.get().uri(serverRequest.remoteAddress().get().getAddress().toString())
                .retrieve()
                .bodyToMono(IpApiResponse.class)
                .flatMap(ipApiResponse -> repository.save(
                                        RequestDetails.builder()
                                                .city(ipApiResponse.city())
                                                .country(ipApiResponse.country())
                                                .ip(ipApiResponse.query())
                                                .requestTime(LocalDateTime.now())
                                                .build()
                                )
                                .thenMany(repository.findAll())
                                .collectList()
                ).flatMap(all -> ServerResponse.ok()
                        .bodyValue(all));

    }
}
