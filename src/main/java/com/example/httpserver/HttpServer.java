/**
    * HttpServer.java
    * Author: Nishant Athawale
    * This class is the main class of the server.
    * It is responsible for starting and stopping the server, and handling client connections.
    * The server is configured using a ServerConfig object, which specifies the port number, the maximum number of threads, and the root directory of the server.
 */


package com.example.httpserver;

// Organising imports
import com.example.httpserver.config.ServerConfig;
import com.example.httpserver.handler.FileServerRequestHandler;
import com.example.httpserver.handler.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {
    private static final Logger logger = LogManager.getLogger(HttpServer.class);
    private final ServerConfig config;
    private final ServerSocket serverSocket;
    private final ExecutorService threadPool;
    private final RequestHandler requestHandler;

    public HttpServer(ServerConfig config) throws IOException {
        // Initializing package objects
        this.config = config;
        this.serverSocket = new ServerSocket(config.getPort());
        this.threadPool = Executors.newFixedThreadPool(config.getMaxThreads());
        this.requestHandler = new FileServerRequestHandler(config.getRootDirectory());
    }

    public void start() {
        // Starting the server
        // Logging the server start message
        logger.info("Server started on port " + config.getPort());
        while (!serverSocket.isClosed()) {
            try {
                Socket clientSocket = serverSocket.accept();
                // Handling client connection in a separate thread using a ConnectionHandler object and the thread pool
                // This enables the server to handle multiple client connections concurrently
                threadPool.execute(new ConnectionHandler(clientSocket, requestHandler));
            } catch (IOException e) {
                logger.error("Error accepting client connection", e);
            }
        }
    }

    public void stop() throws IOException {
        // Stopping the server
        serverSocket.close();
        threadPool.shutdown();
    }

    public static void main(String[] args) {
        // Main method
        try {
            ServerConfig config = new ServerConfig();
            HttpServer server = new HttpServer(config);
            server.start();
        } catch (IOException e) {
            logger.error("Error starting server", e);
        }
    }
}