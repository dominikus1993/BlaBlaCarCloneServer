package entities;

import com.sun.corba.se.spi.orbutil.fsm.Guard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by domin_000 on 03.01.2016.
 */
public class Result<T> {

    private final boolean isSuccess;
    private final boolean isError;
    private final List<String> messages;
    private final T value;

    public Result() {
        this(false, true, CreateMessagesList("No data"), null);
    }


    public Result(boolean isSuccess, boolean isError, List<String> messages) {
        this.isSuccess = isSuccess;
        this.isError = isError;
        this.messages = messages;
        value = null;
    }

    public Result(boolean isSuccess, boolean isError, List<String> messages, T value) {
        this.isSuccess = isSuccess;
        this.isError = isError;
        this.messages = messages;
        this.value = value;
    }

    public Result(T value) {
        this.value = value;
        this.isError = (value == null);
        this.isSuccess = (value != null);
        this.messages = CreateMessagesList("");
    }

    public static List<String> CreateMessagesList(String ...messages)
    {
        return Arrays.asList(messages);
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public boolean isError() {
        return isError;
    }


    public List<String> getMessages() {
        return messages;
    }

    public T getValue() {
        return value;
    }

    public static Result Error(String ...messages){
        return new Result(false, true, CreateMessagesList(messages));
    }
}




