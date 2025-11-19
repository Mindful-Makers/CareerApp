package com.careerapp.model;

/**
 * Represents a company with a name and description.
 */
public class Company {
    private String name;
    private String description;

    /**
     * Creates a new company with the given name and description.
     * @param name company name (required, cannot be null or empty)
     * @param description company description (optional)
     */
    public Company(String name, String description) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Company name cannot be null or empty");
        }
        this.name = name;
        this.description = description != null ? description : "";
    }

    /**
     * Returns the company name.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the company name.
     * @param name new company name (required)
     */
    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Company name cannot be null or empty");
        }
        this.name = name;
    }

    /**
     * Returns the company description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the company description.
     * @param description new company description
     */
    public void setDescription(String description) {
        this.description = description != null ? description : "";
    }

    @Override
    public String toString() {
        return "Company{name='" + name + "', description='" + description + "'}";
    }
}
