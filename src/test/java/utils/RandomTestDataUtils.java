package utils;

import com.github.javafaker.Faker;

public class RandomTestDataUtils {

    public static Faker faker = new Faker();

    public static String getUserEmail() { return faker.internet().emailAddress(); }
    public static String getUserPassword() { return faker.internet().password(); }





}
