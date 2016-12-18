package be.ucll.forecast.api.generator;

/**
 * Created by filip on 18/12/2016.
 */
public interface PasswordHashGenerator {
    String generateHash(String passwordNotEncrypted);
}
