package wendydeluca.capstone.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String msg){
        super(msg);
    }
    public NotFoundException(UUID tId){
        super("The user with id " + tId + "has not been found!");
    }
}
