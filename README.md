# camel-demo
Projeto que sobe um serviço rest com o camel.

Apache camel rest-dsl + spring boot customização do servlet e troubleshoots

Ao criar um projeto spring boot com um serviço rest usando o camel ,descobri algumas particularidades ao criar o servlet que o camel usa para disponibilizar o endpoint que eu gostaria compartilhar abaixo:

Primeiro ponto:
    Verificar a versão do camel a ser usada, conforme https://www.baeldung.com/apache-camel-spring-boot

     “AS OF CAMEL’S VERSION 2.19, THIS CONFIGURATION HAS BEEN DROPPED AS THE CAMELSERVLET IS BY DEFAULT SET TO “/CAMEL”.”
Para as versões do camel antes da 2.19 era necessário criar e registrar um servlet para o camel usar no seu endpoint.                  
@Bean
public ServletRegistrationBean servletRegistrationBean() {
    final ServletRegistrationBean servlet = new ServletRegistrationBean(
            new CamelHttpTransportServlet(), "/api/*");
    servlet.setName("CamelServlet");
    return servlet;
}
Onde definimos a base da url “/api/*”

Importante este servlet necessariamente deve ser chamado de “CamelServlet“, caso o servlet for nomeado diferente o camel não reconhece.

Para as versões do camel igual ou maior que 2.19 não é mais necessário criar e registrar um servlet pois ele já é criado por default.
       A base da url default é a “/camel/*”.

       Para customizar a base da url devemos sobrescrever a propriedade camel.component.servlet.mapping.contextPath no application.properties.       

camel.component.servlet.mapping.contextPath=/api/*
       Curiosidade
Caso tentarmos registrar o bean ServletRegistrationBean com a versão 2.19+ O Spring não cria o Bean.

Links

HTTPS://STACKOVERFLOW.COM/QUESTIONS/48531637/APACHE-CAMEL-REST-NOT-CONNECTING
HTTPS://WWW.BAELDUNG.COM/APACHE-CAMEL-SPRING-BOOT
HTTPS://GITHUB.COM/FERNANDOFAZOLLI/CAMEL-DEMO 
HTTPS://fernandofazolli.wordpress.com/2019/01/04/apache-camel-rest-dsl-spring-boot-customizacao-do-servlet-e-troubleshoots/
