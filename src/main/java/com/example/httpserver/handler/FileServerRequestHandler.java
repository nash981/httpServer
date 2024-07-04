/**
 * FileServerRequestHandler.java
 * Author: Nishant Athawale
 * Handles GET, PUT, and DELETE requests for files in a directory.
 * GET requests return the contents of the file.
 * PUT requests update the contents of the file.
 * DELETE requests delete the file.
 */
package com.example.httpserver.handler;

import com.example.httpserver.request.HttpRequest;
import com.example.httpserver.response.HttpResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileServerRequestHandler implements RequestHandler {
    private static final Logger logger = LogManager.getLogger(FileServerRequestHandler.class);
    private final String rootDirectory;

    public FileServerRequestHandler(String rootDirectory) {
        this.rootDirectory = rootDirectory;
    }

    @Override
    public HttpResponse handleGet(HttpRequest request) {
        // Get the URI from the request
        String uri = request.getUri();
        // If the URI is the root ("/"), default to index.html
        if ("/".equals(uri)) {
            uri = "/index.html";
        }
        // Create a file object for the requested file
        File file = new File(rootDirectory, uri);
        HttpResponse response = new HttpResponse("HTTP/1.1", 200, "OK");
        // If the file exists and is not a directory
        if (file.exists() && !file.isDirectory()) {
            try {
                // Read the contents of the file
                byte[] fileContent = Files.readAllBytes(file.toPath());
                // Set the response body to the contents of the file
                response.setBody(new String(fileContent));
                response.addHeader("Content-Type", Files.probeContentType(file.toPath()));
            } catch (IOException e) {
                // Log an error if there is an exception reading the file
                logger.error("Error reading file: " + file.getPath(), e);
                response.setStatusCode(500);
                response.setStatusMessage("Internal Server Error");
                response.setBody("500 Internal Server Error");
            }
        } else {
            // If the file does not exist, set the response status code to 404
            response.setStatusCode(404);
            response.setStatusMessage("Not Found");
            response.setBody("404 Not Found");
        }

        return response;
    }

    @Override
    public HttpResponse handlePut(HttpRequest request) {
        // Create a file object for the requested file
        File file = new File(rootDirectory, request.getUri());
        HttpResponse response = new HttpResponse("HTTP/1.1", 200, "OK");

        try {
            // Write the body of the request to the file
            Files.write(file.toPath(), request.getBody().getBytes());
            response.setBody("File updated successfully");
        } catch (IOException e) {
            // Log an error if there is an exception writing the file
            logger.error("Error writing file: " + file.getPath(), e);
            response.setStatusCode(500);
            response.setStatusMessage("Internal Server Error");
            response.setBody("Error updating file");
        }

        return response;
    }

    @Override
    public HttpResponse handleDelete(HttpRequest request) {
        // Create a file object for the requested file
        File file = new File(rootDirectory, request.getUri());
        HttpResponse response = new HttpResponse("HTTP/1.1", 200, "OK");

        if (file.exists()) {
            // Delete the file
            if (file.delete()) {
                response.setBody("File deleted successfully");
            } else {
                // Log an error if there is an exception deleting the file
                logger.error("Error deleting file: " + file.getPath());
                response.setStatusCode(500);
                response.setStatusMessage("Internal Server Error");
                response.setBody("Error deleting file");
            }
        } else {
            // If the file does not exist, set the response status code to 404
            response.setStatusCode(404);
            response.setStatusMessage("Not Found");
            response.setBody("File not found");
        }

        return response;
    }
}