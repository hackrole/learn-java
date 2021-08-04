package com.mycompnay.app;

public class Caclculator {
    public int evaluate(String expression) {
        int sum = 0;
        for (String summand :
                expression.split("\\+")) {
            sum += Integer.valueOf(summand);
        }
        return sum;
    }
}
