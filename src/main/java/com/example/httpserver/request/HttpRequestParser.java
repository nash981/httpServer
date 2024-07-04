/**
 * HttpRequestParser.java
 * Author: Nishant Athawale
 * The HttpRequestParser class is responsible for parsing the HTTP request.
 * It reads the request line, headers, and body from the input stream and constructs an HttpRequest object.
 * The parse method reads the request line, splits it into method, URI, and version, and creates an HttpRequest object.
 * It then reads the headers and adds them to the HttpRequest object.
 * If the method is PUT, it reads the body and sets it in the HttpRequest object.
 * The class uses a BufferedReader to read the input stream line by line.
 * The request line and headers are read until an empty line is encountered.
 * The body is read until the end of the input stream.
 */
package com.example.httpserver.request;

// Import the necessary packages.
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class HttpRequestParser {
    public static HttpRequest parse(InputStream inputStream) throws IOException {
        // Create a BufferedReader to read the input stream.
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String requestLine = reader.readLine();
        String[] requestParts = requestLine.split(" ");

        String method = requestParts[0];
        String uri = requestParts[1];
        String version = requestParts[2];

        // Create an HttpRequest object with the method, URI, and version.
        HttpRequest request = new HttpRequest(method, uri, version);

        // Read the headers and add them to the HttpRequest object.
        String header;
        while (!(header = reader.readLine()).isEmpty()) {
            String[] headerParts = header.split(": ");
            request.addHeader(headerParts[0], headerParts[1]);
        }
        // If the method is PUT, read the body and set it in the HttpRequest object.
        if ("PUT".equalsIgnoreCase(method)) {
            StringBuilder bodyBuilder = new StringBuilder();
            while (reader.ready()) {
                bodyBuilder.append((char) reader.read());
            }
            request.setBody(bodyBuilder.toString());
        }

        return request;
    }
}