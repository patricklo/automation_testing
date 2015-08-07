package com.ibm.testing.constants;

/**
 * Created by patrick on 8/6/2015.
 */
public enum  Browsers {
    FIREFOX,
    CHROME,
    SAFARI;

    public static Browsers browserForName(String browser) throws IllegalArgumentException{
        for(Browsers b: values()){
            if(b.toString().equalsIgnoreCase(browser)){
                return b;
            }
        }
        throw browserNotFound(browser);
    }

    private static IllegalArgumentException browserNotFound(String outcome) {
        return new IllegalArgumentException(("Invalid browser [" + outcome + "]"));
    }
}
