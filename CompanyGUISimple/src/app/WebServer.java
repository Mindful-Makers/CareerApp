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
import java.util.List;

/**
 * HTTP server that handles web requests for the Career App.
 * Serves HTML pages with company forms and comparison functionality.
 */
public class WebServer {
    
    private final CompanyService companyService;
    private final HttpServer server;

    /**
     * Creates a new web server on the specified port.
     * 
     * @param port the port to listen on
     * @param companyService the service to manage companies
     * @throws IOException if server cannot be created
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
        server.createContext("/compare", new CompareHandler());
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
                String type = params.get("type");
                String description = params.get("description");
                String location = params.get("location");
                String employeesStr = params.get("employees");
                String salaryMinStr = params.get("salaryMin");
                String salaryMaxStr = params.get("salaryMax");
                
                try {
                    int employees = Integer.parseInt(employeesStr);
                    double salaryMin = Double.parseDouble(salaryMinStr);
                    double salaryMax = Double.parseDouble(salaryMaxStr);
                    
                    Company company = new Company(name, type, description, location,
                                                 employees, salaryMin, salaryMax);
                    companyService.addCompany(company);
                } catch (NumberFormatException e) {
                    // Skip invalid input
                }
                
                exchange.getResponseHeaders().set("Location", "/");
                sendResponse(exchange, 302, "");
            } else {
                sendResponse(exchange, 405, "Method Not Allowed");
            }
        }
    }

    /**
     * Handles requests to compare companies.
     */
    private class CompareHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String html = generateComparePage();
            sendResponse(exchange, 200, html);
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
        html.append("    <title>Career App - Company Categories</title>\n");
        html.append("    <style>\n");
        html.append("        body { font-family: Arial, sans-serif; max-width: 800px; margin: 50px auto; padding: 20px; }\n");
        html.append("        h1 { color: #333; }\n");
        html.append("        .form-group { margin: 15px 0; }\n");
        html.append("        label { display: block; font-weight: bold; margin-bottom: 5px; }\n");
        html.append("        input, textarea { width: 100%; padding: 8px; font-size: 14px; border: 1px solid #ddd; border-radius: 4px; }\n");
        html.append("        button { background: #28a745; color: white; padding: 10px 20px; border: none; border-radius: 4px; cursor: pointer; font-size: 16px; margin: 10px 5px 10px 0; }\n");
        html.append("        button.compare { background: #007bff; }\n");
        html.append("        .company-card { background: #f8f9fa; padding: 15px; margin: 10px 0; border-left: 4px solid #007bff; }\n");
        html.append("    </style>\n");
        html.append("</head>\n");
        html.append("<body>\n");
        
        html.append("    <h1>Career App - Company Information</h1>\n");
        html.append("    <p>Enter company details to view and compare career opportunities</p>\n");
        html.append("    <hr>\n");
        
        html.append("    <h2>Company Categories (Input Section)</h2>\n");
        html.append("    <form action=\"/add\" method=\"POST\">\n");
        
        html.append("        <div class=\"form-group\">\n");
        html.append("            <label for=\"name\">Company Name:</label>\n");
        html.append("            <input type=\"text\" id=\"name\" name=\"name\" required>\n");
        html.append("        </div>\n");
        
        html.append("        <div class=\"form-group\">\n");
        html.append("            <label for=\"type\">Type of Company:</label>\n");
        html.append("            <input type=\"text\" id=\"type\" name=\"type\" placeholder=\"e.g., Technology, Finance, Healthcare\">\n");
        html.append("        </div>\n");
        
        html.append("        <div class=\"form-group\">\n");
        html.append("            <label for=\"description\">Description:</label>\n");
        html.append("            <textarea id=\"description\" name=\"description\" rows=\"3\"></textarea>\n");
        html.append("        </div>\n");
        
        html.append("        <div class=\"form-group\">\n");
        html.append("            <label for=\"location\">Location:</label>\n");
        html.append("            <input type=\"text\" id=\"location\" name=\"location\" placeholder=\"e.g., San Francisco, CA\">\n");
        html.append("        </div>\n");
        
        html.append("        <div class=\"form-group\">\n");
        html.append("            <label for=\"employees\">Number of Employees:</label>\n");
        html.append("            <input type=\"number\" id=\"employees\" name=\"employees\" placeholder=\"e.g., 50000\">\n");
        html.append("        </div>\n");
        
        html.append("        <div class=\"form-group\">\n");
        html.append("            <label for=\"salaryMin\">Salary Min:</label>\n");
        html.append("            <input type=\"number\" id=\"salaryMin\" name=\"salaryMin\" placeholder=\"e.g., 80000\">\n");
        html.append("        </div>\n");
        
        html.append("        <div class=\"form-group\">\n");
        html.append("            <label for=\"salaryMax\">Salary Max:</label>\n");
        html.append("            <input type=\"number\" id=\"salaryMax\" name=\"salaryMax\" placeholder=\"e.g., 150000\">\n");
        html.append("        </div>\n");
        
        html.append("        <button type=\"submit\">Add Company</button>\n");
        html.append("    </form>\n");
        
        html.append("    <hr>\n");
        
        html.append("    <h2>Companies List</h2>\n");
        
        List<Company> companies = companyService.getAllCompanies();
        
        if (companies.isEmpty()) {
            html.append("    <p><em>No companies added yet. Use the form above to add companies!</em></p>\n");
        } else {
            for (Company company : companies) {
                html.append("    <div class=\"company-card\">\n");
                html.append("        <h3>").append(escapeHtml(company.getName())).append("</h3>\n");
                html.append("        <p><strong>Type:</strong> ").append(escapeHtml(company.getTypeOfCompany())).append("</p>\n");
                html.append("        <p><strong>Description:</strong> ").append(escapeHtml(company.getDescription())).append("</p>\n");
                html.append("        <p><strong>Location:</strong> ").append(escapeHtml(company.getLocation())).append("</p>\n");
                html.append("        <p><strong>Employees:</strong> ").append(company.getNumberOfEmployees()).append("</p>\n");
                html.append("        <p><strong>Salary Range:</strong> $").append(String.format("%,.0f", company.getSalaryMin()))
                           .append(" - $").append(String.format("%,.0f", company.getSalaryMax())).append("</p>\n");
                html.append("        <p><strong>Average Salary:</strong> $").append(String.format("%,.0f", company.getAverageSalary())).append("</p>\n");
                html.append("    </div>\n");
            }
            
            html.append("    <button class=\"compare\" onclick=\"window.location.href='/compare'\">Compare Companies</button>\n");
        }
        
        html.append("</body>\n");
        html.append("</html>");
        
        return html.toString();
    }

    /**
     * Generates the HTML for the comparison page.
     */
    private String generateComparePage() {
        StringBuilder html = new StringBuilder();
        
        html.append("<!DOCTYPE html>\n");
        html.append("<html><head><title>Compare Companies</title></head>\n");
        html.append("<body style='font-family: Arial; max-width: 800px; margin: 50px auto; padding: 20px;'>\n");
        html.append("<h1>Company Salary Comparison</h1>\n");
        
        List<Company> companies = companyService.getAllCompanies();
        
        if (companies.isEmpty()) {
            html.append("<p>No companies to compare yet!</p>\n");
        } else {
            html.append("<table border='1' style='width:100%; border-collapse: collapse;'>\n");
            html.append("<tr><th>Company</th><th>Type</th><th>Location</th><th>Employees</th><th>Salary Min</th><th>Salary Max</th><th>Average</th></tr>\n");
            
            for (Company c : companies) {
                html.append("<tr>");
                html.append("<td>").append(escapeHtml(c.getName())).append("</td>");
                html.append("<td>").append(escapeHtml(c.getTypeOfCompany())).append("</td>");
                html.append("<td>").append(escapeHtml(c.getLocation())).append("</td>");
                html.append("<td>").append(c.getNumberOfEmployees()).append("</td>");
                html.append("<td>$").append(String.format("%,.0f", c.getSalaryMin())).append("</td>");
                html.append("<td>$").append(String.format("%,.0f", c.getSalaryMax())).append("</td>");
                html.append("<td>$").append(String.format("%,.0f", c.getAverageSalary())).append("</td>");
                html.append("</tr>\n");
            }
            
            html.append("</table>\n");
        }
        
        html.append("<br><button onclick='window.location.href=\"/\"'>Back to Home</button>\n");
        html.append("</body></html>");
        
        return html.toString();
    }

    /**
     * Parses form data from POST request body.
     * 
     * @param formData URL-encoded form data
     * @return map of parameter names to values
     */
    private Map<String, String> parseFormData(String formData) {
        Map<String, String> params = new HashMap<>();
        if (formData == null || formData.isEmpty()) return params;
        
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
     * 
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
     * 
     * @param exchange the HTTP exchange
     * @param statusCode the HTTP status code
     * @param response the response body
     * @throws IOException if an I/O error occurs
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
