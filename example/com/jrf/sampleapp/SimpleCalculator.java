package com.jrf.sampleapp;

import java.io.InputStreamReader;
import java.lang.String;
import java.util.Scanner;

public class SimpleCalculator {
	private static String add(long addend, long augend) {
		return "Sum is " + (addend+augend);
	}

	private static String subtract(long minuend, long subtrahend) {
		return "Difference is " + (minuend-subtrahend);
	}

	private static String multiply(long multiplier, long multiplicand) {
		return "Product is " + (multiplier*multiplicand);
	}

	private static String divide(long dividend, long divisor) {
		return "Ratio is " + (dividend/divisor);
	}

	public static void main(String[] args) {
		long input1, input2;
		String command;

		Scanner scanner = new Scanner(new InputStreamReader(System.in));
		command = scanner.next();

		while(!command.equalsIgnoreCase("exit")) {
			input1 = scanner.nextLong();
			input2 = scanner.nextLong();

			switch(command.toLowerCase()) {
				case "add":
					System.out.println(add(input1, input2));
					break;

				case "subtract":
					System.out.println(subtract(input1, input2));
					break;

				case "multiply":
					System.out.println(multiply(input1, input2));
					break;

				case "divide":
					System.out.println(divide(input1, input2));
					break;

				default:
					System.out.println("Use \"add x y | subtract x y | multiply x y | divide x y | exit\"");
					break;
			}

			command = scanner.next();
		}
	}
}
