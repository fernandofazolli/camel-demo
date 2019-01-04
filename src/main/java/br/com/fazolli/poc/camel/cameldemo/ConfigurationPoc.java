package br.com.fazolli.poc.camel.cameldemo;

import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigurationPoc {

    /**
     * O Spring desconsidera este bean e nem sobe ele ...
     * caso precise customizar a base da url precisaremos usar a propriedade
     * camel.component.servlet.mapping.contextPath=/api/*
     *
     * @return
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        final ServletRegistrationBean servlet = new ServletRegistrationBean(
                new CamelHttpTransportServlet(), "/api/*");
        servlet.setName("CamelServlet");
        return servlet;
    }

    /**
     * Criei este bean para confirmar se o spring carregava corretamente no load
     * da aplicação.
     *
     * @return
     */
    @Bean
    public NovoBean getNovoBean() {
        return new NovoBean();
    }

}
