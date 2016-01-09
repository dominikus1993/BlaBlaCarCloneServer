package utils;

import entities.Person;
import spark.Request;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.UUID;

/**
 * Created by dominik.kotecki on 04-01-2016.
 */
public class UserUtils {
    private static final String USER_SESSION_ID = "user";

    public static void addAuthenticatedUser(Request request, Person person) {
        request.session().attribute(USER_SESSION_ID, person);

    }

    public static void removeAuthenticatedUser(Request request) {
        request.session().removeAttribute(USER_SESSION_ID);

    }

    public static Person getAuthenticatedUser(Request request) {
        return request.session().attribute(USER_SESSION_ID);
    }

    public static String GenerateAuthenticationToken(String userName) {
        return UUID.randomUUID().toString() + "|" + userName + "|" + UUID.randomUUID().toString();
    }
}
