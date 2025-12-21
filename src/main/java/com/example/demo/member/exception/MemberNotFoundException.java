package com.example.demo.member.exception;

public class MemberNotFoundException extends RuntimeException{
    public MemberNotFoundException (String mes){
        super(mes);
    }

}
