package org.example;

import java.math.BigInteger;
import java.util.Scanner;

public class Task1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter N: ");
        int n = scanner.nextInt(); // Entering of N
        if (n < 0) {
            System.out.println("The number has to be greater than 0");
            return;
        }
        System.out.println("Count of correct bracket expressions for N = " + n
                + " : " + calculateCatalan(n));
    }

    public static BigInteger calculateCatalan(int n) {
        if (n == 0) return BigInteger.ONE; // Basic case: for n = 0 there is 1 way - empty line

        // Calculating Catalan numbers using the formula:
        // C_n = (2n)! / [(n + 1)! * n!]
        BigInteger numerator = factorial(2 * n);         // (2n)!
        BigInteger denominator = factorial(n + 1).multiply(factorial(n)); // (n+1)! * n!
        return numerator.divide(denominator);           // C_n
    }

    // Method for computing factorial of a number
    public static BigInteger factorial(int num) {
        if (num == 1) {
            return BigInteger.valueOf(1);
        } else {
            return BigInteger.valueOf(num).multiply(factorial(num - 1));
        }
    }
}
