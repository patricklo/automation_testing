package com.ibm.testing.constants;

/**
 * Created by patrick on 8/7/2015.
 */
public enum CredentialsType {
    VALID(new String[]{"valid","correct"}),
    INVALID(new String[]{"invalid"});

    private String[] aliases;

    private CredentialsType(String[] aliases){this.aliases = aliases;}

    public static CredentialsType credentialsTypeForName(String credentialsType){
        for(CredentialsType ct:values()){
            for(String aliases:ct.aliases){
                if(aliases.equalsIgnoreCase(credentialsType))
                    return ct;
            }
        }
        throw credentialsTypeNotFound(credentialsType);
    }


    private static IllegalArgumentException credentialsTypeNotFound(String ct){
        return new IllegalArgumentException("Invalid credentials type [" + ct +"]");
    }

}
