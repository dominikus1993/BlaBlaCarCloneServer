package controllers;

import entities.Person;
import entities.RegisterUser;
import entities.Result;
import repositories.IPersonRepository;
import spark.Request;
import spark.Response;
import com.google.gson.Gson;

import javax.jws.soap.SOAPBinding;

/**
 * Created by domin_000 on 02.01.2016.
 */
public class UserController {
    private final IPersonRepository repository;
    private final Gson gson;
    public UserController(IPersonRepository repository) {
        this.repository = repository;
        this.gson = new Gson();
    }

    public String Login(Request request, Response response){
        Result<Person> person = repository.Login(request.params(":login"), request.params(":password"));
        if (person.isSuccess()){
            response.cookie("Login",person.getValue().getEmail());
        }
        return gson.toJson(person);
    }

    public String Register(Request request, Response response){
        String usea = request.body();
        RegisterUser user = gson.fromJson(request.body(), RegisterUser.class);
        Result<Person> personResult = repository.Register(user.getUsername(), user.getPassword(), user.getConfirmPassword());
        return gson.toJson(personResult);
    }

}
