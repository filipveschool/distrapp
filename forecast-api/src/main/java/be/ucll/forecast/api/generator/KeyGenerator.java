package be.ucll.forecast.api.generator;

import java.security.Key;

/**
 * Created by filip on 18/12/2016.
 */
public interface KeyGenerator {

    Key generateJWTSigningKey();

}
