package com.example.route;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class PubSubSubscriberRoute extends RouteBuilder {

    @Override
    public void configure() {

        from("google-pubsub:test-project:test-subscription")
                .routeId("pubsub-subscriber")
                .log("📥 Mensaje recibido: ${body}");
    }
}
