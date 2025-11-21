package com.careerapp.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 tests for the Company model class.
 */
public class CompanyTest {
    
    private Company company;

    @BeforeEach
    public void setUp() {
        company = new Company(
            "TechCorp",
            "Technology",
            "Leading tech company",
            "San Francisco, CA",
            5000,
            100000.0,
            200000.0
        );
    }

    @Test
    public void testCompanyCreation() {
        assertNotNull(company, "Company should not be null");
        assertEquals("TechCorp", company.getName(), "Company name should match");
        assertEquals("Leading tech company", company.getDescription(), "Company description should match");
    }

    @Test
    public void testGetName() {
        assertEquals("TechCorp", company.getName());
    }

    @Test
    public void testGetTypeOfCompany() {
        assertEquals("Technology", company.getTypeOfCompany());
    }

    @Test
    public void testGetDescription() {
        assertEquals("Leading tech company", company.getDescription());
    }

    @Test
    public void testGetLocation() {
        assertEquals("San Francisco, CA", company.getLocation());
    }

    @Test
    public void testGetNumberOfEmployees() {
        assertEquals(5000, company.getNumberOfEmployees());
    }

    @Test
    public void testGetSalaryMin() {
        assertEquals(100000.0, company.getSalaryMin(), 0.01);
    }

    @Test
    public void testGetSalaryMax() {
        assertEquals(200000.0, company.getSalaryMax(), 0.01);
    }

    @Test
    public void testSetName() {
        company.setName("NewCorp");
        assertEquals("NewCorp", company.getName());
    }

    @Test
    public void testSetTypeOfCompany() {
        company.setTypeOfCompany("Finance");
        assertEquals("Finance", company.getTypeOfCompany());
    }

    @Test
    public void testSetDescription() {
        company.setDescription("New description");
        assertEquals("New description", company.getDescription());
    }

    @Test
    public void testSetLocation() {
        company.setLocation("Austin, TX");
        assertEquals("Austin, TX", company.getLocation());
    }

    @Test
    public void testSetNumberOfEmployees() {
        company.setNumberOfEmployees(10000);
        assertEquals(10000, company.getNumberOfEmployees());
    }

    @Test
    public void testSetSalaryMin() {
        company.setSalaryMin(80000.0);
        assertEquals(80000.0, company.getSalaryMin(), 0.01);
    }

    @Test
    public void testSetSalaryMax() {
        company.setSalaryMax(250000.0);
        assertEquals(250000.0, company.getSalaryMax(), 0.01);
    }

    @Test
    public void testGetAverageSalary() {
        double expected = (100000.0 + 200000.0) / 2.0;
        double actual = company.getAverageSalary();
        assertEquals(expected, actual, 0.01);
    }

    @Test
    public void testToString() {
        String result = company.toString();
        assertTrue(result.contains("TechCorp"), "toString should contain company name");
    }

    @Test
    public void testNullNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Company(null, "Tech", "Desc", "CA", 100, 50000, 100000);
        });
    }

    @Test
    public void testEmptyNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Company("", "Tech", "Desc", "CA", 100, 50000, 100000);
        });
    }

    @Test
    public void testSetNullNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            company.setName(null);
        });
    }

    @Test
    public void testSetEmptyNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            company.setName("   ");
        });
    }
}
