package com.example.evan.rpncalculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;


public class Stack {
    private int stackPtr;
    private ArrayList<BigDecimal> stack;

    public Stack(int size) {
        stack = new ArrayList<>(4);
        stackPtr = 0;
    }

    public int getStackPtr() {
        return stackPtr;
    }

    public void push(String val) {
        if (!val.equals("")) {
            stack.add(new BigDecimal(val));
            stackPtr++;
        }
    }

    public void drop() {
        if (0 != stackPtr) {
            stackPtr--;
            stack.remove(stackPtr);
        }
    }

    public void updateStackX(String str) {
        if (0 != stackPtr) {
            stack.set(stackPtr-1, new BigDecimal(str));
        }
        else {
            stack.add(new BigDecimal(str));
            stackPtr++;
        }
    }

    public void delStackX() {
        if (0 != stackPtr) {
            String substr = stack.get(stackPtr-1).toPlainString();
            if (substr.length() > 1) {
                substr = substr.substring(0, substr.length() - 1);
                stack.set(stackPtr - 1, new BigDecimal(substr));
            }
            else {
                drop();
            }
        }
    }

    public void changeSignX() {
        if (0 != stackPtr) {
            stack.set(stackPtr-1, stack.get(stackPtr-1).multiply(new BigDecimal(-1)));
        }
    }

    public String getString(int index) {
        if (index >= stackPtr) return "";
        return stack.get(stackPtr-1-index).stripTrailingZeros().toPlainString();
    }

    //OPERATORS

    public void operation(String oper) {
        if (oper.equals("add")) add();
        else if (oper.equals("sub")) sub();
        else if (oper.equals("mul")) mul();
        else if (oper.equals("div")) div();
    }

    private void add() {
        if (stackPtr > 1) {
            stack.set(stackPtr - 2, stack.get(stackPtr - 2).add(stack.get(stackPtr - 1)));
            stack.remove(stackPtr-1);
            stackPtr--;
        }
    }

    private void sub() {
        if (stackPtr > 1) {
            stack.set(stackPtr - 2, stack.get(stackPtr - 2).subtract(stack.get(stackPtr - 1)));
            stack.remove(stackPtr-1);
            stackPtr--;
        }
    }

    private void mul() {
        if (stackPtr > 1) {
            stack.set(stackPtr - 2, stack.get(stackPtr - 2).multiply(stack.get(stackPtr - 1)));
            stack.remove(stackPtr-1);
            stackPtr--;
        }
    }

    private void div() {
        if (stackPtr > 1) {
            stack.set(stackPtr - 2, stack.get(stackPtr - 2).divide(stack.get(stackPtr - 1),
                    15, RoundingMode.HALF_UP));
            stack.remove(stackPtr-1);
            stackPtr--;
        }
    }
}
