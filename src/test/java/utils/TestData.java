package utils;

import static utils.RandomTestDataUtils.getUserEmail;
import static utils.RandomTestDataUtils.getUserPassword;

public class TestData {

    public String userEmail = getUserEmail();
    public String userPassword = getUserPassword();

    public static final String TOO_LONG_EMAIL = "a".repeat(300) + "@gmail.com";
    public static final String TOO_SHORT_EMAIL_VALUE = "a@a.a";
    public static final String NON_EMAIL_MASK_VALUE = ".@somevalue";
    public static final String BRING_APP_NAME = "Bring!";


}
