package com.monapp;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class CalculatorTest {
    
    private Calculator calculator;
    
    @Before
    public void setUp() {
        calculator = new Calculator();
    }
    
    @Test
    public void testAdditionner() {
        assertEquals(5, calculator.additionner(2, 3));
        assertEquals(0, calculator.additionner(-1, 1));
        assertEquals(-5, calculator.additionner(-2, -3));
    }
    
    @Test
    public void testMultiplier() {
        assertEquals(6, calculator.multiplier(2, 3));
        assertEquals(0, calculator.multiplier(0, 5));
        assertEquals(-6, calculator.multiplier(-2, 3));
    }
    
    @Test
    public void testSoustraire() {
        assertEquals(1, calculator.soustraire(3, 2));
        assertEquals(-5, calculator.soustraire(3, 8));
    }
}