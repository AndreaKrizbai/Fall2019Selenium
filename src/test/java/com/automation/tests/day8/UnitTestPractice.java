package com.automation.tests.day8;

public class UnitTestPractice {
    public static void main(String[] args) {
       // System.out.println(reverseString("apple"));

        String toReverse = "abc";
        String expected = "cba";
        String actual = reverseString(toReverse);
    }





    public static boolean verifyEquals(String expected, String actual){
        if(expected.equals(actual)){
            System.out.println("TEST PASSED");
            return true;
        }else{
            System.out.println("TEST FAILED");
            System.out.println("Expected: " + expected);
            System.out.println("Actual: " + actual);
            return false;
        }
    }


    public static String reverseString (String str){
        String reversed = "";
        for (int i = str.length()-1; i >= 0 ; i--) {
            reversed += str.charAt(i);
        }
        return reversed;
    }


}
