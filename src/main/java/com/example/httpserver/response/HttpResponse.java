package com.example.httpserver.response;

import java.util.HashMap;
import java.util.Map;

public class HttpResponse {
    private final String version;
    private int statusCode;
    private String statusMessage;
    private final Map<String, String> headers;
    private String body;

    public HttpResponse(String version, int statusCode, String statusMessage) {
        this.version = version;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.headers = new HashMap<>();
    }

    public HttpResponse(int statusCode, String statusMessage) {
        this("HTTP/1.1", statusCode, statusMessage);
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public String getVersion() {
        return version;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public String getHeader(String key) {
        return headers.get(key);
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }
}