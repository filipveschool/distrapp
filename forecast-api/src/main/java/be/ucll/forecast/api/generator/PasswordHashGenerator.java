package be.ucll.forecast.api.generator;

import javax.ejb.Local;

/**
 * Created by filip on 18/12/2016.
 */
@Local
public interface PasswordHashGenerator {
    String generateHash(String passwordNotEncrypted);
}
