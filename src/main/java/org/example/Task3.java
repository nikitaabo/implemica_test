package org.example;

import java.math.BigInteger;
import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter N: ");
        int n = scanner.nextInt(); // Entering of N
        if (n < 0) {
            System.out.println("The number has to be greater than 0");
            return;
        }
        BigInteger f = factorial(n);

        System.out.println("Sum of digits in the number " + n + "! : " + sumOfDigits(f));
    }

    // Method for computing factorial of a number
    public static BigInteger factorial(int num) {
        if (num == 0 || num == 1) {
            return BigInteger.valueOf(1);
        } else {
            return BigInteger.valueOf(num).multiply(factorial(num - 1));
        }
    }

    // Method for computing sum of digits of a number
    public static int sumOfDigits(BigInteger number) {
        String numberStr = number.toString();
        int sum = 0;

        // Summing up the numbers
        for (char digit : numberStr.toCharArray()) {
            sum += Character.getNumericValue(digit);
        }

        return sum;
    }
}
