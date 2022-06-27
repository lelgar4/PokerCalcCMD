package com.pokercalc.cmd;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Utility
{

/** ------------------------
 *      isNumeric()
 *  ------------------------
 *     -- tests if a string is a number by seeing if it can be parsed into an int.
 *     -- Returns TRUE if the string can be parsed into an int; returns FALSE if it cannot.
 *     -- Used for user input of card ranks in function inputCard()
 **/

    public static boolean isNumeric(String s)
    {
        int i;

//  if string 's' is null or empty an error message is printed and function returns false
        if(s == null || s.equals("")){
            System.out.println("String is null/empty; can't be parsed.");
            return false;
        }

//  parse string as int wrapped in try-catch; if string can't be parsed as int, NumberFormatException is caught
//  and a notification is printed to console
        try {

//  if 's' is parsed as int and no NFE is thrown, the string is a number; function returns true
            i = Integer.parseInt(s);
            return true;

        }catch (NumberFormatException nfe){
            System.out.println("NumberFormatException thrown.\nInput string can't be parsed to integer/is NOT numeric.");
        }

//  return false after catch block; NFE was thrown, 's' is not numeric
        return false;
    }



/** ------------------------
 *      outputErrorMessage()
 *  ------------------------
 *
 **/

    public static void outputErrorMessage(String errorHeader,String errorBody,boolean includesTryAgain)
    {
/*      EXAMPLE:        ======================
                        ERROR: <errorHeader>
                        ----------------------
                        <errorBody>
                        Try again.
                        ======================             */

        int lengthErrorBorder = Math.max(errorHeader.length(),errorBody.length());
        StringBuilder sbErrorBorder = new StringBuilder("=");

        while(sbErrorBorder.length() <= lengthErrorBorder + 2){
            sbErrorBorder.append('=');
        }

        StringBuilder sbOutput = new StringBuilder();
        sbOutput.append("\n\n").append(sbErrorBorder).append("\n")
                .append("ERROR: ").append(errorHeader).append("\n")
                .append(sbErrorBorder.toString().replace('=','-')).append("\n")
                .append(errorBody).append("\n");

        if(includesTryAgain){
            sbOutput.append("Try again.\n");
        }

        sbOutput.append(sbErrorBorder).append("\n");

        System.out.println(sbOutput);
    }



/** ------------------------
 *      outputMenu()
 *  ------------------------
 *      //todo: documentation
 **/
    public static void outputMenu(String menuHeader, String[] menuOptions)
    {
        ArrayList<String> strings = (ArrayList<String>) Arrays.asList(menuOptions);
        strings.add(menuHeader);
        int lengthMenuBorder = getMaxLength_StringsArr(strings);

        StringBuilder sbMenuBorder = new StringBuilder("=");

        while(sbErrorBorder.length() <= lengthErrorBorder + 2){
            sbErrorBorder.append('=');
        }

    }



/** -----------------------------
 *      getMaxLength_StringsArr()
 *  -----------------------------
 *      //todo: documentation
 **/
    public static int getMaxLength_StringsArr(ArrayList<String> strings)
    {
        String max = "";

        for(String s : strings)
        {
            if(s.length() > max.length()){
                max = s;
            }
        }

        return max.length();
    }
}
