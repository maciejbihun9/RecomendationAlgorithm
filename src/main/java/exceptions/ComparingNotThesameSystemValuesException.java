package exceptions;

/**
 * Created by Maciek on 2016-12-28.
 */
public class ComparingNotThesameSystemValuesException extends Exception  {
    public ComparingNotThesameSystemValuesException(){
        super("Trying to compare not the same system. Bad value array length.");
    }
}
