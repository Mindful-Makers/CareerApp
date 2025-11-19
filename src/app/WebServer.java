package com.careerapp.server;

import com.careerapp.model.Company;
import com.careerapp.service.CompanyService;
import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * HTTP server that handles web requests for the Career App.
 */
public class WebServer {
    private final CompanyService companyService;
    private final HttpServer server;

    /**
     * Creates a new web server on the specified port.
     * @param port the port to listen on
     * @param companyService the service to manage companies
     */
    public WebServer(int port, CompanyService companyService) throws IOException {
        this.companyService = companyService;
        this.server = HttpServer.create(new java.net.InetSocketAddress("0.0.0.0", port), 0);
        setupHandlers();
    }

    /**
     * Sets up HTTP request handlers for different routes.
     */
    private void setupHandlers() {
        server.createContext("/", new HomeHandler());
        server.createContext("/add", new AddCompanyHandler());
        server.setExecutor(null);
    }

    /**
     * Starts the web server.
     */
    public void start() {
        server.start();
        System.out.println("Server started on port " + server.getAddress().getPort());
    }

    /**
     * Stops the web server.
     */
    public void stop() {
        server.stop(0);
    }

    /**
     * Handles requests to the home page.
     */
    private class HomeHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String html = generateHomePage();
            sendResponse(exchange, 200, html);
        }
    }

    /**
     * Handles POST requests to add a new company.
     */
    private class AddCompanyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            if ("POST".equals(exchange.getRequestMethod())) {
                String body = new String(exchange.getRequestBody().readAllBytes(), StandardCharsets.UTF_8);
                Map<String, String> params = parseFormData(body);
                
                String name = params.get("name");
                String description = params.get("description");
                
                if (name != null && !name.trim().isEmpty()) {
                    companyService.addCompany(new Company(name, description));
                }
                
                exchange.getResponseHeaders().set("Location", "/");
                sendResponse(exchange, 302, "");
            } else {
                sendResponse(exchange, 405, "Method Not Allowed");
            }
        }
    }

    /**
     * Generates the HTML for the home page with form and company list.
     */
    private String generateHomePage() {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>\n");
        html.append("<html>\n");
        html.append("<head>\n");
        html.append("    <title>Career App - Company List</title>\n");
        html.append("</head>\n");
        html.append("<body>\n");
        html.append("    <h1>Welcome to Career App</h1>\n");
        html.append("    <p>Enter companies to view available career opportunities</p>\n");
        html.append("    <hr>\n");
        html.append("    <h2>Add a Company</h2>\n");
        html.append("    <form action=\"/add\" method=\"POST\">\n");
        html.append("        <label for=\"name\">Company Name:</label><br>\n");
        html.append("        <input type=\"text\" id=\"name\" name=\"name\" required><br><br>\n");
        html.append("        <label for=\"description\">Description:</label><br>\n");
        html.append("        <textarea id=\"description\" name=\"description\" rows=\"3\" cols=\"40\"></textarea><br><br>\n");
        html.append("        <input type=\"submit\" value=\"Add Company\">\n");
        html.append("    </form>\n");
        html.append("    <hr>\n");
        html.append("    <h2>Companies List</h2>\n");
        
        if (companyService.getAllCompanies().isEmpty()) {
            html.append("    <p>No companies added yet.</p>\n");
        } else {
            html.append("    <ul>\n");
            for (Company company : companyService.getAllCompanies()) {
                html.append("        <li>\n");
                html.append("            <strong>").append(escapeHtml(company.getName())).append("</strong><br>\n");
                if (company.getDescription() != null && !company.getDescription().isEmpty()) {
                    html.append("            ").append(escapeHtml(company.getDescription())).append("\n");
                }
                html.append("        </li>\n");
            }
            html.append("    </ul>\n");
        }
        
        html.append("</body>\n");
        html.append("</html>");
        return html.toString();
    }

    /**
     * Parses form data from POST request body.
     * @param formData URL-encoded form data
     * @return map of parameter names to values
     */
    private Map<String, String> parseFormData(String formData) {
        Map<String, String> params = new HashMap<>();
        if (formData == null || formData.isEmpty()) {
            return params;
        }
        
        String[] pairs = formData.split("&");
        for (String pair : pairs) {
            String[] keyValue = pair.split("=", 2);
            if (keyValue.length == 2) {
                try {
                    String key = URLDecoder.decode(keyValue[0], StandardCharsets.UTF_8);
                    String value = URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8);
                    params.put(key, value);
                } catch (Exception e) {
                    // Skip invalid parameters
                }
            }
        }
        return params;
    }

    /**
     * Escapes HTML special characters to prevent XSS attacks.
     * @param text the text to escape
     * @return escaped text safe for HTML output
     */
    private String escapeHtml(String text) {
        if (text == null) return "";
        return text.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\"", "&quot;")
                   .replace("'", "&#39;");
    }

    /**
     * Sends an HTTP response with the given status code and content.
     * @param exchange the HTTP exchange
     * @param statusCode the HTTP status code
     * @param response the response body
     */
    private void sendResponse(HttpExchange exchange, int statusCode, String response) throws IOException {
        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
        exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
        exchange.sendResponseHeaders(statusCode, bytes.length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(bytes);
        }
    }
}
