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
