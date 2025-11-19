package com.careerapp.service;

import com.careerapp.model.Company;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages a collection of companies in memory.
 */
public class CompanyService {
    private List<Company> companies;

    /**
     * Creates a new company service with an empty list.
     */
    public CompanyService() {
        this.companies = new ArrayList<>();
    }

    /**
     * Adds a company to the collection.
     * @param company the company to add (required)
     */
    public void addCompany(Company company) {
        if (company == null) {
            throw new IllegalArgumentException("Company cannot be null");
        }
        companies.add(company);
    }

    /**
     * Returns a copy of all companies.
     * @return list of all companies
     */
    public List<Company> getAllCompanies() {
        return new ArrayList<>(companies);
    }

    /**
     * Removes all companies from the collection.
     */
    public void clearAll() {
        companies.clear();
    }
}
