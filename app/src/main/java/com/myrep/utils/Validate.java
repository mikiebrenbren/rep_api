package com.myrep.utils;

/**
 * Created by Michael Brennan on 8/26/15.
 */
public class Validate {

    public static boolean validateZip(String zip){
        //I chose to only validate five digit patterns only for simplicity sake, would want to validate full US zip code.
        String zipCodePattern = "\\d{5}(-\\d{4})?";
        if(zip.matches(zipCodePattern)){
            return true;
        }
        return false;
    }
}
