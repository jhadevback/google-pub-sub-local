package com.example.rest;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.apache.camel.ProducerTemplate;

@Path("/publish")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class PublishResource {

    @Inject
    ProducerTemplate producerTemplate;

    @POST
    public String publish(String message) {
        producerTemplate.sendBody("direct:publish", message);
        return "Mensaje enviado: " + message;
    }
}
