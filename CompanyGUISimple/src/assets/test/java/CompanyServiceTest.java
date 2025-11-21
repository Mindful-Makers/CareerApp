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

    @BeforeEach
    public void setUp() {
        service = new CompanyService();
    }

    @Test
    public void testServiceCreation() {
        assertNotNull(service, "Service should not be null");
    }

    @Test
    public void testGetAllCompaniesEmpty() {
        List<Company> companies = service.getAllCompanies();
        assertNotNull(companies, "List should not be null");
        assertEquals(0, companies.size(), "List should be empty");
    }

    @Test
    public void testAddCompany() {
        Company company = new Company("TechCorp", "Tech", "Tech company",
                                     "SF, CA", 1000, 80000, 150000);
        service.addCompany(company);
        
        List<Company> companies = service.getAllCompanies();
        assertEquals(1, companies.size(), "Should have 1 company");
        assertEquals("TechCorp", companies.get(0).getName(), "Company name should match");
    }

    @Test
    public void testAddMultipleCompanies() {
        service.addCompany(new Company("Company1", "Tech", "Desc1", "CA", 100, 50000, 100000));
        service.addCompany(new Company("Company2", "Finance", "Desc2", "NY", 200, 60000, 120000));
        service.addCompany(new Company("Company3", "Retail", "Desc3", "TX", 300, 40000, 80000));
        
        assertEquals(3, service.getAllCompanies().size(), "Should have 3 companies");
    }

    @Test
    public void testAddNullCompany() {
        assertThrows(IllegalArgumentException.class, () -> {
            service.addCompany(null);
        });
    }

    @Test
    public void testGetAllCompanies() {
        service.addCompany(new Company("TechCorp", "Tech", "Tech company", "CA", 100, 80000, 150000));
        service.addCompany(new Company("DataInc", "Data", "Data company", "NY", 200, 90000, 160000));
        
        List<Company> companies = service.getAllCompanies();
        assertEquals(2, companies.size(), "Should have 2 companies");
    }

    @Test
    public void testGetAllCompaniesReturnsCopy() {
        service.addCompany(new Company("Test", "Tech", "Desc", "CA", 100, 50000, 100000));
        
        List<Company> list1 = service.getAllCompanies();
        list1.clear();
        
        List<Company> list2 = service.getAllCompanies();
        assertEquals(1, list2.size(), "Service data should not be affected");
    }

    @Test
    public void testClearAllCompanies() {
        service.addCompany(new Company("TechCorp", "Tech", "Tech", "CA", 100, 50000, 100000));
        assertEquals(1, service.getAllCompanies().size());
        
        service.clearAll();
        assertEquals(0, service.getAllCompanies().size(), "Should have 0 companies after clear");
    }

    @Test
    public void testGetCompanyCount() {
        assertEquals(0, service.getCompanyCount());
        
        service.addCompany(new Company("Company1", "Tech", "D", "CA", 100, 50000, 100000));
        assertEquals(1, service.getCompanyCount());
        
        service.addCompany(new Company("Company2", "Finance", "D", "NY", 200, 60000, 120000));
        assertEquals(2, service.getCompanyCount());
        
        service.clearAll();
        assertEquals(0, service.getCompanyCount());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(service.isEmpty(), "Should be empty initially");
        
        service.addCompany(new Company("Test", "Tech", "D", "CA", 100, 50000, 100000));
        assertFalse(service.isEmpty(), "Should not be empty after adding");
        
        service.clearAll();
        assertTrue(service.isEmpty(), "Should be empty after clearing");
    }

    @Test
    public void testCompleteWorkflow() {
        assertTrue(service.isEmpty());
        
        service.addCompany(new Company("Google", "Tech", "Search", "CA", 150000, 120000, 200000));
        service.addCompany(new Company("Amazon", "Ecommerce", "Retail", "WA", 1500000, 110000, 190000));
        service.addCompany(new Company("Microsoft", "Tech", "Software", "WA", 220000, 115000, 195000));
        
        assertEquals(3, service.getCompanyCount());
        assertFalse(service.isEmpty());
        
        List<Company> all = service.getAllCompanies();
        assertEquals(3, all.size());
        assertEquals("Google", all.get(0).getName());
        assertEquals("Amazon", all.get(1).getName());
        assertEquals("Microsoft", all.get(2).getName());
        
        service.clearAll();
        assertTrue(service.isEmpty());
        assertEquals(0, service.getCompanyCount());
    }
}
