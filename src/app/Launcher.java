package com.careerapp;

import com.careerapp.server.WebServer;
import com.careerapp.service.CompanyService;

/**
 * Main entry point for the Career App web application.
 */
public class Launcher {
    private static final int DEFAULT_PORT = 5000;

    /**
     * Starts the Career App web server.
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            int port = getPort();
            CompanyService companyService = new CompanyService();
            WebServer server = new WebServer(port, companyService);
            
            System.out.println("Starting Career App on port " + port + "...");
            server.start();
            System.out.println("Career App is running!");
            System.out.println("Open your browser and navigate to http://localhost:" + port);
            
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                System.out.println("\nShutting down Career App...");
                server.stop();
            }));
            
            Thread.currentThread().join();
        } catch (Exception e) {
            System.err.println("Failed to start Career App: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * Gets the port number from the PORT environment variable, or uses default.
     * @return the port number to use
     */
    public static int getPort() {
        String portEnv = System.getenv("PORT");
        if (portEnv != null && !portEnv.isEmpty()) {
            try {
                return Integer.parseInt(portEnv);
            } catch (NumberFormatException e) {
                System.err.println("Invalid PORT environment variable, using default: " + DEFAULT_PORT);
            }
        }
        return DEFAULT_PORT;
    }
}
