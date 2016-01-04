package entities;

import java.io.Serializable;

/**
 * Created by domin_000 on 03.01.2016.
 */
public class RegisterUser implements Serializable {
    private final String username;
    private final String password;
    private final String confirmPassword;

    public RegisterUser() {
        username = "";
        password = "";
        confirmPassword = "";
    }

    public RegisterUser(String username, String password, String confirmPassword) {
        this.username = username;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }
}
