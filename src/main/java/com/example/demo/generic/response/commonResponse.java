package com.example.demo.generic.response;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class commonResponse {
    boolean error;
    String message;
    Object data;
}


