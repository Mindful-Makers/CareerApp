package com.careerapp.service;

import com.careerapp.model.Company;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

/**
 * JUnit 5 tests for the CompanyService class.
 */
public class CompanyServiceTest {
    private CompanyService service;

    /**
     * Sets up a fresh service before each test.
     */
    @BeforeEach
    public void setUp() {
        service = new CompanyService();
    }

    /**
     * Tests that a service is created successfully.
     */
    @Test
    public void testServiceCreation() {
        assertNotNull(service, "Service should not be null");
    }

    /**
     * Tests adding a company to the service.
     */
    @Test
    public void testAddCompany() {
        Company company = new Company("TechCorp", "Tech company");
        service.addCompany(company);
        
        List<Company> companies = service.getAllCompanies();
        assertEquals(1, companies.size(), "Should have 1 company");
        assertEquals("TechCorp", companies.get(0).getName(), "Company name should match");
    }

    /**
     * Tests getting all companies from the service.
     */
    @Test
    public void testGetAllCompanies() {
        service.addCompany(new Company("TechCorp", "Tech company"));
        service.addCompany(new Company("DataInc", "Data company"));
        
        List<Company> companies = service.getAllCompanies();
        assertEquals(2, companies.size(), "Should have 2 companies");
    }

    /**
     * Tests that getAllCompanies() returns empty list initially.
     */
    @Test
    public void testGetAllCompaniesEmpty() {
        List<Company> companies = service.getAllCompanies();
        assertNotNull(companies, "List should not be null");
        assertEquals(0, companies.size(), "List should be empty");
    }

    /**
     * Tests adding multiple companies to the service.
     */
    @Test
    public void testAddMultipleCompanies() {
        service.addCompany(new Company("Company1", "Desc1"));
        service.addCompany(new Company("Company2", "Desc2"));
        service.addCompany(new Company("Company3", "Desc3"));
        
        assertEquals(3, service.getAllCompanies().size(), "Should have 3 companies");
    }

    /**
     * Tests that adding null company throws exception.
     */
    @Test
    public void testAddNullCompany() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.addCompany(null);
        });
    }

    /**
     * Tests clearing all companies from the service.
     */
    @Test
    public void testClearAllCompanies() {
        service.addCompany(new Company("TechCorp", "Tech"));
        service.clearAll();
        
        assertEquals(0, service.getAllCompanies().size(), "Should have 0 companies after clear");
    }
}
