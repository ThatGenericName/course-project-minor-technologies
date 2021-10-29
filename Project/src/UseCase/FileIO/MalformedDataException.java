package UseCase.FileIO;

import java.util.ArrayList;

public class MalformedDataException extends Exception{

    public MalformedDataException(String message){
        super (message);
    }

    public MalformedDataException(){
        super();
    }
}
