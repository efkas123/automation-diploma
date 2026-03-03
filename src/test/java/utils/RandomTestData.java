package utils;

import com.github.javafaker.Faker;

public class RandomTestData {

    public static Faker faker = new Faker();

    public static String getUserEmail() { return faker.internet().emailAddress(); }

    public String userEmail = getUserEmail();

}
