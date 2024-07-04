package com.example.httpserver.response;

import java.nio.charset.StandardCharsets;

public class HttpResponseBuilder {
    public static byte[] build(HttpResponse response) {
        StringBuilder responseBuilder = new StringBuilder();
        responseBuilder.append(response.getVersion()).append(" ")
                .append(response.getStatusCode()).append(" ")
                .append(response.getStatusMessage()).append("\r\n");

        for (String header : response.getHeaders().keySet()) {
            responseBuilder.append(header).append(": ")
                    .append(response.getHeader(header)).append("\r\n");
        }

        responseBuilder.append("\r\n");

        if (response.getBody() != null) {
            responseBuilder.append(response.getBody());
        }

        return responseBuilder.toString().getBytes(StandardCharsets.UTF_8);
    }
}