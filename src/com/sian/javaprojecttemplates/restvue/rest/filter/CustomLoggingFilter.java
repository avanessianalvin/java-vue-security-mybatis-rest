package com.sian.javaprojecttemplates.restvue.rest.filter;

import com.sun.jersey.api.container.filter.LoggingFilter;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponse;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;

public class CustomLoggingFilter extends LoggingFilter implements ContainerRequestFilter, ContainerResponseFilter {

    private static Logger logger = LogManager.getLogger(CustomLoggingFilter.class.getName());

    @Override
    public ContainerRequest filter(ContainerRequest request) {
        StringBuilder sb = new StringBuilder();
        sb.append("\nMethod : ").append(request.getMethod().toUpperCase())
                .append("  Path : ").append(request.getRequestUri().getPath())
                .append("\nHeaders : ").append(request.getRequestHeaders());


        logger.log(Level.INFO, "\nHTTP REQUEST" + sb.toString());

        return request;
    }

    @Override
    public ContainerResponse filter(ContainerRequest request, ContainerResponse response) {
        ObjectMapper objectMapper = new ObjectMapper();
        StringBuilder sb = new StringBuilder();
        sb.append("\nHeaders : ").append(response.getHttpHeaders());
        sb.append("\nStatus : ").append(response.getStatus());

        try {
            sb.append("\nEntity : ").append(objectMapper.writeValueAsString(response.getEntity()));
        } catch (Exception e) {
            logger.log(Level.INFO, "\nEntity : ERROR in converting entity to JSON for logging. " + e.getMessage());
        }

        logger.log(Level.INFO, "\nHTTP RESPONSE" + sb.toString());

        return response;
    }
}
