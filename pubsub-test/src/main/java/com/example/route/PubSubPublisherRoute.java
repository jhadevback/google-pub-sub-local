package com.example.route;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;

@ApplicationScoped
public class PubSubPublisherRoute extends RouteBuilder {

    @Override
    public void configure() {

        from("direct:publish")
                .routeId("pubsub-publisher")
                .log("📤 Enviando mensaje a PubSub: ${body}")
                .to("google-pubsub:test-project:test-topic");
    }
}
