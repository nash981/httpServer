/**
 * Interface for handling HTTP requests.
 * Author: Nishant Athawale
 */
package com.example.httpserver.handler;

import com.example.httpserver.request.HttpRequest;
import com.example.httpserver.response.HttpResponse;

public interface RequestHandler {
    HttpResponse handleGet(HttpRequest request);
    HttpResponse handlePut(HttpRequest request);
    HttpResponse handleDelete(HttpRequest request);
}