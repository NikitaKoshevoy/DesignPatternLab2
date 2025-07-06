package com.esdc.lab2.interpreter;

import java.util.ArrayDeque;
import java.util.Deque;

public class SimpleEvaluator {

    public static final char PLUS = '+';
    public static final char MINUS = '-';
    public static final char MULTIPLY = '*';
    public static final char DELIMITER = '/';
    public static final char ZERO_CHAR = '0';
    public static final char OPEN_BRACKET = '(';
    public static final char CLOSING_BRACKET = ')';

    public static int evaluate(String expression) {
        return evaluateExpression(expression.replaceAll("\\s+", ""));
    }

    private static int evaluateExpression(String expr) {
        Deque<Integer> nums = new ArrayDeque<>();
        Deque<Character> ops = new ArrayDeque<>();
        int sign = 1;
        int i = 0;

        while (i < expr.length()) {
            char ch = expr.charAt(i);

            if (Character.isDigit(ch)) {
                int num = 0;
                while (i < expr.length() && Character.isDigit(expr.charAt(i))) {
                    num = num * 10 + (expr.charAt(i) - ZERO_CHAR);
                    i++;
                }
                nums.push(sign * num);
                continue;
            } else if (ch == PLUS) {
                sign = 1;
            } else if (ch == MINUS) {
                sign = -1;
            } else if (ch == OPEN_BRACKET) {
                ops.push(OPEN_BRACKET);
                nums.push(sign);
                sign = 1;
            } else if (ch == CLOSING_BRACKET) {
                int result;
                while (!ops.isEmpty() && ops.peek() != OPEN_BRACKET) {
                    result = applyOp(ops.pop(), nums.pop(), nums.pop());
                    nums.push(result);
                }
                if (!ops.isEmpty()) ops.pop();
                if (!nums.isEmpty()) {
                    int val = nums.pop();
                    int multiplier = (!nums.isEmpty() && (nums.peek() == 1 || nums.peek() == -1)) ? nums.pop() : 1;
                    nums.push(val * multiplier);
                }
            } else if (ch == MULTIPLY || ch == DELIMITER) {
                while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(ch)) {
                    int b = nums.pop();
                    int a = nums.pop();
                    nums.push(applyOp(ops.pop(), b, a));
                }
                ops.push(ch);
            }
            i++;
        }

        while (!ops.isEmpty()) {
            int b = nums.pop();
            int a = nums.pop();
            nums.push(applyOp(ops.pop(), b, a));
        }

        return nums.isEmpty() ? 0 : nums.pop();
    }

    private static int applyOp(char op, int b, int a) {
        return switch (op) {
            case PLUS -> a + b;
            case MINUS -> a - b;
            case MULTIPLY -> a * b;
            case DELIMITER -> b == 0 ? 0 : a / b;
            default -> 0;
        };
    }

    private static int precedence(char op) {
        return switch (op) {
            case PLUS, MINUS -> 1;
            case MULTIPLY, DELIMITER -> 2;
            default -> 0;
        };
    }
}
