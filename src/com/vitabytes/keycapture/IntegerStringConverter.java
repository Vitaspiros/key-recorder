package com.vitabytes.keycapture;

import javafx.util.StringConverter;

public class IntegerStringConverter extends StringConverter<Number> {

    public IntegerStringConverter() {};
    @Override
    public Number fromString(String str) {
        Number num=Double.parseDouble(str);
        return num.intValue();
    }

    @Override
    public String toString(Number num) {
        if (num.intValue()!=num.floatValue())
            return "";
        return String.valueOf(num.intValue());
    }
    
}
