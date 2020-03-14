package com.automation.tests.day8;

import org.testng.Assert;
import org.testng.annotations.Test;

public class UnitTestPractice {
    public static void main(String[] args) {
       // System.out.println(reverseString("apple"));

        String toReverse = "abc";
        String expected = "cba";
        String actual = reverseString(toReverse);

        verifyEquals(expected, actual);
    }

    @Test(description = "Verify if method can reverse a string")
    public void test(){
        String expected = "elpp";
        String actual = reverseString("apple");
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void test2(){
        String expected = "rac";
        String actual = reverseString("car");
        Assert.assertEquals(actual,expected);
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
