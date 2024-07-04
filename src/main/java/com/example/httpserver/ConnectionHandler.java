/**
 * ConnectionHandler.java
 * Handles a single client connection.
 * Reads the request from the client, parses it, and passes it to the RequestHandler.
 * Writes the response from the RequestHandler back to the client.
 */
package com.example.httpserver;

// Import the necessary packages.
import com.example.httpserver.handler.RequestHandler;
import com.example.httpserver.request.HttpRequest;
import com.example.httpserver.request.HttpRequestParser;
import com.example.httpserver.response.HttpResponse;
import com.example.httpserver.response.HttpResponseBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ConnectionHandler implements Runnable {
    private static final Logger logger = LogManager.getLogger(ConnectionHandler.class);
    private final Socket clientSocket;
    private final RequestHandler requestHandler;

    public ConnectionHandler(Socket clientSocket, RequestHandler requestHandler) {
        // The clientSocket is the socket that the server uses to communicate with the client.
        this.clientSocket = clientSocket;
        this.requestHandler = requestHandler;
    }

    @Override
    public void run() {
        try (InputStream inputStream = clientSocket.getInputStream();
             OutputStream outputStream = clientSocket.getOutputStream()) {
            // Parse the request from the client.
            HttpRequest request = HttpRequestParser.parse(inputStream);
            logger.info("Received request: " + request.getMethod() + " " + request.getUri());
            // Handle the request and get the response.
            HttpResponse response = handleRequest(request);
            // Write the response back to the client.
            byte[] responseBytes = HttpResponseBuilder.build(response);
            outputStream.write(responseBytes);
            outputStream.flush();
            // Log the response.
            logger.info("Sent response: " + response.getStatusCode() + " " + response.getStatusMessage());

        } catch (IOException e) {
            logger.error("Error handling client connection", e);
        } finally {
            // Close the client socket.
            try {
                clientSocket.close();
            } catch (IOException e) {
                logger.error("Error closing client socket", e);
            }
        }
    }

    private HttpResponse handleRequest(HttpRequest request) {
        // Handle the request based on the HTTP method.
        return switch (request.getMethod().toUpperCase()) {
            case "GET" -> requestHandler.handleGet(request);
            case "PUT" -> requestHandler.handlePut(request);
            case "DELETE" -> requestHandler.handleDelete(request);
            default -> new HttpResponse(405, "Method Not Allowed");
        };
    }
}