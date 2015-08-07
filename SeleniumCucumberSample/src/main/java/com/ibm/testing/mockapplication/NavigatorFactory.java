package com.ibm.testing.mockapplication;
/**
 * Created by patrick on 8/6/2015.
 */
public class NavigatorFactory {
    public static volatile Navigation navigator =null;

    public static synchronized Navigation getNavigator(){
        if(navigator==null){
            navigator = new Navigation();
        }
        return navigator;
    }
}
