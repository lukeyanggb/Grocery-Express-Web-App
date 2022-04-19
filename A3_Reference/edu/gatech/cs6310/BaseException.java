package edu.gatech.cs6310;

public class BaseException extends Exception{
    private String message;

    public BaseException(String message) {
        super(message);
        this.message = message;
    }


    public void printMessage(){
        System.out.println(message);
    }
}
