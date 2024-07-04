/**
 * HttpRequest.java
 * Author: Nishant Athawale
 * This class represents an HTTP request.
 * It contains the method, URI, version, headers, and body of the request.
 * The method, URI, and version are required fields, while the headers and body are optional.
 * The headers are stored in a map, where the key is the header name and the value is the header value.
 * The body is stored as a string.
 * The class provides methods to add headers, get headers, set the body, and get the body.
 */

package com.example.httpserver.request;

// Import the necessary packages.
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private final String method;
    private final String uri;
    private final String version;
    private final Map<String, String> headers;
    private String body;

    public HttpRequest(String method, String uri, String version) {
        // Initialize the HttpRequest with the method, URI, and version.
        this.method = method;
        this.uri = uri;
        this.version = version;
        this.headers = new HashMap<>();
    }

    public void addHeader(String key, String value) {
        // Add a header to the map of headers.
        headers.put(key, value);
    }

    public String getMethod() {
        // Return the HTTP method of the request.
        return method;
    }

    public String getUri() {
        // Return the URI of the request.
        return uri;
    }

    public String getVersion() {
        // Return the HTTP version of the request.
        return version;
    }

    public String getHeader(String key) {
        // Return the value of the header with the specified key.
        return headers.get(key);
    }

    public void setBody(String body) {
        // Set the body of the request.
        this.body = body;
    }

    public String getBody() {
        // Return the body of the request.
        return body;
    }
}