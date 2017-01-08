package com.shifter.validation;

import android.util.Log;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Harshad on 8/8/2016.
 */
public class PasswordValidator{

    private Pattern pattern;
    private Matcher matcher;

    private static final String PASSWORD_PATTERN ="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
    private static final String UCASE_PATTERN=".*[A-Z].*";
    private static final String LCASE_PATTERN=".*[a-z].*";
    private static final String NUMBER_PATTERN=".*\\d.*";

    private static final String wrong="<font color=\"red\">&#10008; </font>";
    private static final String right="<font color=\"green\">&#10004; </font>";
    private String message="";

    private String digiteMsg="6 Characters Long";
    private String uppercaseMsg="One Uppercase Letter";
    private String lowercaseMsg="One Lowercase Letter";
    private String numberMsg="One Number";

    public String getMessage() {
        return message;
    }

    /*public PasswordValidator(String validationExp){
        pattern = Pattern.compile(validationExp);
    }*/

    /**
     * Validate password with regular expression
     * @param password password for validation
     * @return true valid password, false invalid password
     */
    public boolean validate(final String password){

        boolean is_match_no=false,is_match_u=false,is_match_l=false,is_match_6=false;

        message = "";

        if(password.toString().trim().length() < 6)
        {
            is_match_6 =false;
            message = message+wrong+digiteMsg;
        }
        else
        {
            is_match_6=true;
            message = message+right+digiteMsg;
        }

        pattern = Pattern.compile(NUMBER_PATTERN);
        matcher = pattern.matcher(password);
        if(matcher.matches())
        {
            is_match_no =true;
            message = message+"<br>"+right+numberMsg+"</br>";
        }
        else
        {
            is_match_no =false;
            message = message+"<br>"+wrong+numberMsg+"</br>";
        }

        pattern = Pattern.compile(UCASE_PATTERN);
        matcher = pattern.matcher(password);
        if(matcher.matches())
        {
            is_match_u =true;
            message = message+"<br>"+right+uppercaseMsg+"</br>";
        }
        else
        {
            is_match_u = false;
            message = message+"<br>"+wrong+uppercaseMsg+"</br>";
        }

        pattern = Pattern.compile(LCASE_PATTERN);
        matcher = pattern.matcher(password);
        if(matcher.matches())
        {
            is_match_l =true;
            message = message+"<br>"+right+lowercaseMsg+"</br>";
        }
        else
        {
            is_match_l =false;
            message = message+"<br>"+wrong+lowercaseMsg+"</br>";
        }

        Log.e("TAG","is_match_no : "+is_match_no);
        Log.e("TAG","is_match_u  : "+is_match_u);
        Log.e("TAG","is_match_l  : "+is_match_l);
        Log.e("TAG","is_match_6  : "+is_match_6);

        if(is_match_no && is_match_u && is_match_l && is_match_6)
        {
            message="";
            return  false;
        }
        else
            return true;

    }
}
