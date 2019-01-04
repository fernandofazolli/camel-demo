package br.com.fazolli.poc.camel.cameldemo;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class Router extends RouteBuilder {
    @Override
    public void configure() throws Exception {
        restConfiguration().contextPath("/api/*")
                .component("servlet");

        rest("/teste").produces("application/json")
                .get("/hello/{name}")
                .route().transform().simple("Hello ${header.name}, Good Bye !!!")
                .end();

    }
}
