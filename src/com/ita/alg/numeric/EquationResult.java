package com.ita.alg.numeric;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import static javax.swing.UIManager.put;

public class EquationResult {
    Map<Character, Integer> map = new HashMap<Character, Integer>() {{
        put('-', 1);
        put('+', 1);
        put('*', 2);
        put('/', 2);
        put('%', 2);
        put('^', 3);
    }};

    public static void main(String[] args) {
        System.out.println(new EquationResult().solve("(3+4)*(5+(2-3))"));
    }

    public int solve(String s) {
        s = s.replace(" ", "");
        if (s.contains("(")) {
            String newEquation = "";
            int lastLeftParenthesisIndex = s.lastIndexOf("(");
            String tmp = s.substring(lastLeftParenthesisIndex);
            int firstRightParenthesisIndex = lastLeftParenthesisIndex + tmp.indexOf(")");
            int middle = calculateWithoutParenthesis(s.substring(lastLeftParenthesisIndex + 1, firstRightParenthesisIndex));
            String middleString = String.valueOf(middle);
            newEquation = s.substring(0, lastLeftParenthesisIndex) + middleString + s.substring(firstRightParenthesisIndex + 1);
            return solve(newEquation);
        } else {
            return calculateWithoutParenthesis(s);
        }
    }

    private int calculateWithoutParenthesis(String equation) {
        if (equation.startsWith("-")) {
            equation = 0 + equation;
        }
        Stack<Character> ops = new Stack<>();
        Stack<Integer> nums = new Stack<>();

        for (int i = 0; i < equation.length(); ) {
            if (Character.isDigit(equation.charAt(i))) {
                int beginIndex = i;
                i = getNextPosition(equation, i);
                nums.push(Integer.parseInt(equation.substring(beginIndex, i)));
            } else {
                if (equation.charAt(i) == '-') {
                    if (i == 0) {
                        i = getNextPosition(equation, i);
                        nums.push(Integer.parseInt(equation.substring(0, i)));
                        continue;
                    } else if (!Character.isDigit(equation.charAt(i - 1))) {
                        int beginIndex = i;
                        i = getNextPosition(equation, i);
                        nums.push(Integer.parseInt(equation.substring(beginIndex, i)));
                        continue;
                    }
                }
                if (ops.empty()) {
                    ops.push(equation.charAt(i));
                } else {
                    if (map.get(equation.charAt(i)) > map.get(ops.peek())) {
                        ops.push(equation.charAt(i));
                    } else {
                        int num2 = nums.pop();
                        int num1 = nums.pop();
                        int middle = ops(num1, num2, ops.pop());
                        nums.push(middle);
                        ops.push(equation.charAt(i));
                    }
                }
                i++;
            }

        }
        while (!ops.empty()) {
            int num2 = nums.pop();
            int num1 = nums.pop();
            int middle = ops(num1, num2, ops.pop());
            nums.push(middle);
        }
        return nums.pop();
    }

    private int ops(int num1, int num2, char ops) {
        switch (ops) {
            case '+' -> {
                return num1 + num2;
            }
            case '-' -> {
                return num1 - num2;
            }
            case '*' -> {
                return num1 * num2;
            }
            case '/' -> {
                return num1 / num2;
            }
            case '%' -> {
                return num1 % num2;
            }
            case '^' -> {
                return (int) Math.pow(num1, num2);
            }
        }
        return -1;
    }

    private int getNextPosition(String s, int start) {
        start++;
        while (start <= s.length() - 1 && Character.isDigit(s.charAt(start))) {
            start++;
        }
        return start;
    }
}
