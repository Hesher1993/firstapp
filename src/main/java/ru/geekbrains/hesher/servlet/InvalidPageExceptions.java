package ru.geekbrains.hesher.servlet;

public class InvalidPageException extends RuntimeException{
    public InvalidPageException(String msg) {
        super("Invalid page:" + msg);
    }
}
