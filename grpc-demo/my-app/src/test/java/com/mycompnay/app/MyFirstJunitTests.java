package com.mycompnay.app;

import static org.junit.Assert.assertEquals;

import com.mycompnay.app.Caclculator;
import org.junit.Test;

import java.util.Calendar;

public class MyFirstJunitTests {
    @Test
    public void evalExp(){
        Caclculator cal = new Caclculator();
        int sum = cal.evaluate("1+2+3");
        assertEquals(7, sum);
    }
}