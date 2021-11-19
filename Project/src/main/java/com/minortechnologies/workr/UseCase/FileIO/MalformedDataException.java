package com.minortechnologies.workr.UseCase.FileIO;

public class MalformedDataException extends Exception{

    public MalformedDataException(String message){
        super (message);
    }

    public MalformedDataException(){
        super();
    }
}
