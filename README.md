
# How to run this Project on your local machine

1. Pull the git repository into your local machine
2. Install latest gradle version
3. go to the project directory where the `gradle.build` file is
4. run `./gradlew build ; ./gradlew run` command. This should start your server on port 8080.
5. go to localhost:8080 to see the static page.

## Some specifics about the server:
1. The server is binded to port 8080
2. The concurrent request handling capacity depends on the type of machine that the server is run on. In this version it is limited to 4000 concurrent threads. 
3. The default directory is www which can be seen in the file directory
4. All the specifics mentioned above can be modified in the `ServerConfig.java` file


HTTP Server from Scratch

Features that have been implemented
1. Request Handling:
- Ability to receive and process HTTP requests (GET, POST, etc.)
- Parse incoming request headers and body

2. Response Generation:
- Construct and send HTTP responses with proper status codes and headers
- Serve static files (HTML, CSS, JavaScript, images, etc.)

3. Error Handling:
- Generate and send appropriate error responses (404 Not Found, 500 Internal Server Error, etc.)

4. Logging:
- Record access logs and error logs
- Configurable log formats and locations

Proposed Features:

1. Request Handling:
- Route requests to appropriate handlers based on URL paths

2. Response Generation:
- Generate dynamic content

3. Protocol Support:
- Support for HTTP/1.1 at minimum
- Handling of persistent connections (Keep-Alive)

4. Content Types:
- Ability to serve different MIME types
- Set appropriate Content-Type headers

5. Security:
- Basic authentication support
- HTTPS/TLS encryption (optional for basic, but increasingly important)
- Input validation and sanitization

6. Configuration:
- Ability to set port number
- Define document root directory
- Configure virtual hosts (optional for very basic servers)

7. Performance:
- Multithreading or asynchronous I/O to handle concurrent connections
- Basic caching mechanisms

8. Extensibility:
- Modular design to allow adding new features or handlers

9. Standards Compliance:
- Adhere to HTTP specifications (RFC 2616 for HTTP/1.1)

10. Directory Listing:
- Option to enable/disable directory listing when accessing a directory without an index file

11. Default Documents:
- Ability to specify and serve default documents (e.g., index.html) when accessing a directory

12. Basic URL Rewriting:
- Simple rewrite rules for clean URLs or redirects
