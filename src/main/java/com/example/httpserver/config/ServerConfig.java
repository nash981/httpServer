/**
 *  ServerConfig.java
 *  Author: Nishant Athawale
 *
 *  This class is used to store the configuration of the server.
 *  The configuration includes the port number, the maximum number of threads, and the root directory of the server.
 *  By default, the server is configured to run on port 8080, with a maximum of 4000 threads, and the root directory is set to "www".
 */
package com.example.httpserver.config;

public class ServerConfig {

    private int port = 8080;
    private int maxThreads = 4000;
    private String rootDirectory = "www";

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public int getMaxThreads() {
        return maxThreads;
    }

    public void setMaxThreads(int maxThreads) {
        this.maxThreads = maxThreads;
    }

    public String getRootDirectory() {
        return rootDirectory;
    }

    public void setRootDirectory(String rootDirectory) {
        this.rootDirectory = rootDirectory;
    }
}