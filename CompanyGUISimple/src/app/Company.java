package com.careerapp.model;

/**
 * Represents a company with comprehensive information including
 * name, type, description, location, employee count, and salary range.
 */
public class Company {
    
    private String name;
    private String typeOfCompany;
    private String description;
    private String location;
    private int numberOfEmployees;
    private double salaryMin;
    private double salaryMax;

    /**
     * Creates a new company with the given information.
     * 
     * @param name company name (required, cannot be null or empty)
     * @param typeOfCompany type/industry of the company
     * @param description what the company does
     * @param location where the company is located
     * @param numberOfEmployees total number of employees
     * @param salaryMin minimum salary offered
     * @param salaryMax maximum salary offered
     * @throws IllegalArgumentException if name is null or empty
     */
    public Company(String name, String typeOfCompany, String description,
                   String location, int numberOfEmployees,
                   double salaryMin, double salaryMax) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Company name cannot be null or empty");
        }
        
        this.name = name;
        this.typeOfCompany = typeOfCompany != null ? typeOfCompany : "";
        this.description = description != null ? description : "";
        this.location = location != null ? location : "";
        this.numberOfEmployees = numberOfEmployees;
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
    }

    public String getName() {
        return name;
    }

    public String getTypeOfCompany() {
        return typeOfCompany;
    }

    public String getDescription() {
        return description;
    }

    public String getLocation() {
        return location;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public double getSalaryMin() {
        return salaryMin;
    }

    public double getSalaryMax() {
        return salaryMax;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Company name cannot be null or empty");
        }
        this.name = name;
    }

    public void setTypeOfCompany(String typeOfCompany) {
        this.typeOfCompany = typeOfCompany != null ? typeOfCompany : "";
    }

    public void setDescription(String description) {
        this.description = description != null ? description : "";
    }

    public void setLocation(String location) {
        this.location = location != null ? location : "";
    }

    public void setNumberOfEmployees(int numberOfEmployees) {
        this.numberOfEmployees = numberOfEmployees;
    }

    public void setSalaryMin(double salaryMin) {
        this.salaryMin = salaryMin;
    }

    public void setSalaryMax(double salaryMax) {
        this.salaryMax = salaryMax;
    }

    /**
     * Calculates and returns the average salary.
     * 
     * @return the average of min and max salary
     */
    public double getAverageSalary() {
        return (salaryMin + salaryMax) / 2.0;
    }

    @Override
    public String toString() {
        return "Company{" +
                "name='" + name + '\'' +
                ", type='" + typeOfCompany + '\'' +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", employees=" + numberOfEmployees +
                ", salary=$" + salaryMin + "-$" + salaryMax +
                '}';
    }
}
