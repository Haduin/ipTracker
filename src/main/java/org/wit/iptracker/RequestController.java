package org.wit.iptracker;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class RequestController {
    @Bean
    public RouterFunction<ServerResponse> route(RequestHandler requestHandler) {
        return RouterFunctions.route(
                GET("/").and(accept(MediaType.APPLICATION_JSON)), requestHandler::handleGetRequest
        );
    }
}
