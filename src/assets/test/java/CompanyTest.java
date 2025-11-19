package com.careerapp.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 tests for the Company model class.
 */
public class CompanyTest {
    private Company company;

    /**
     * Sets up a test company before each test.
     */
    @BeforeEach
    public void setUp() {
        company = new Company("TechCorp", "Leading technology company");
    }

    /**
     * Tests that a company is created with correct name and description.
     */
    @Test
    public void testCompanyCreation() {
        assertNotNull(company, "Company should not be null");
        assertEquals("TechCorp", company.getName(), "Company name should match");
        assertEquals("Leading technology company", company.getDescription(), "Company description should match");
    }

    /**
     * Tests getting the company name.
     */
    @Test
    public void testGetName() {
        assertEquals("TechCorp", company.getName());
    }

    /**
     * Tests getting the company description.
     */
    @Test
    public void testGetDescription() {
        assertEquals("Leading technology company", company.getDescription());
    }

    /**
     * Tests setting a new company name.
     */
    @Test
    public void testSetName() {
        company.setName("NewCorp");
        assertEquals("NewCorp", company.getName());
    }

    /**
     * Tests setting a new company description.
     */
    @Test
    public void testSetDescription() {
        company.setDescription("New description");
        assertEquals("New description", company.getDescription());
    }

    /**
     * Tests that toString() includes the company name.
     */
    @Test
    public void testToString() {
        String result = company.toString();
        assertTrue(result.contains("TechCorp"), "toString should contain company name");
    }

    /**
     * Tests that creating a company with null name throws exception.
     */
    @Test
    public void testNullNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Company(null, "Description");
        });
    }

    /**
     * Tests that creating a company with empty name throws exception.
     */
    @Test
    public void testEmptyNameThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Company("", "Description");
        });
    }
}
