package com.test;

import java.util.function.Supplier;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.ClientProperties;
import org.glassfish.jersey.jackson.JacksonFeature;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.jaxrs.json.JacksonJaxbJsonProvider;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.security.SecuredAPIFilter;

public class RestClient {

    private Client client;

    public RestClient() {
        configureClient();
    }

    private void configureClient() {
        JacksonJsonProvider jacksonJsonProvider = new JacksonJaxbJsonProvider();
        jacksonJsonProvider.setMapper(getJsonObjectMapper());

        ClientConfig clientConfig = new ClientConfig().register(JacksonFeature.class).register(jacksonJsonProvider);

        clientConfig.property(ClientProperties.SUPPRESS_HTTP_COMPLIANCE_VALIDATION, "true");
        clientConfig.property(ClientProperties.CONNECT_TIMEOUT, 60000);
        clientConfig.property(ClientProperties.READ_TIMEOUT, 60000);

        client = ClientBuilder.newClient(clientConfig);
    }

    private ObjectMapper getJsonObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        mapper.setSerializationInclusion(Include.NON_NULL);
        mapper.enable(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS);
        mapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        return mapper;
    }

    public WebTarget getTarget() {
        return client.target("http://localhost:8080/link-test/link");
    }

    public Response post(String path, Object resource) {
        return doRetry$(() -> {
            final WebTarget webTarget = getTarget().path(path);

            final String tenantID = "tenant";

            return webTarget
            /**/.request(MediaType.APPLICATION_JSON_TYPE)
            /**/.header(SecuredAPIFilter.HTTP_HEADER_TENANT_NAME, tenantID)
            /**/.post(Entity.json(resource));
        });
    }

    // https://github.com/gondor/openstack4j/issues/78
    public Response doRetry$(Supplier<Response> p) {
        int trials = 0;
        int maxTrials = 10;
        Throwable ex = null;
        do {
            try {
                return p.get();
            } catch (Throwable e) {
                ex = e;
                String message = e.getMessage();
                if (message.contains("Already connected") || message.contains("Connection reset") || message.contains("Read timed out")) {
                    trials++;
                } else {
                    throw new IllegalStateException("After " + trials , e);
                }
            }
        } while (trials < maxTrials);
        throw new IllegalStateException("After " + trials, ex);
    }

}
