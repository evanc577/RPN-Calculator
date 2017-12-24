package com.example.evan.rpncalculator;

import java.math.BigDecimal;

/**
 * Created by evan on 12/24/17.
 */

public class Number {
    private BigDecimal val;
    private boolean hasValue;

    public Number() {
        hasValue = false;
    }

    public Number(String s, boolean hasValue) {
        val = new BigDecimal(s);
        this.hasValue = hasValue;
    }

    public Number(int v, boolean hasValue) {
        val = new BigDecimal(v);
        this.hasValue = hasValue;
    }

    public boolean getHasValue() {
        return hasValue;
    }

    public String getString() {
        if (!hasValue) return "";
        return val.toString();
    }
}
