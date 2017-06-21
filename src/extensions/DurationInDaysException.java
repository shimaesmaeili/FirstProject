package extensions;

public class DurationInDaysException extends Exception{

    DurationInDaysException(){
        super();
    }

    public DurationInDaysException(String s){
        super(s);
    }
}
