package be.ucll.forecast.api.generator.impl;

import be.ucll.forecast.api.generator.KeyGenerator;

import javax.crypto.spec.SecretKeySpec;
import javax.ejb.Stateless;
import java.security.Key;

/**
 * Created by filip on 18/12/2016.
 */
@Stateless
public class JWTSigningKeyGeneratorImplementation implements KeyGenerator {

    @Override
    public Key generateKey() {
        String JWTSigningKeyStringOrigineel = "simplekey";
        return new SecretKeySpec(JWTSigningKeyStringOrigineel.getBytes(), 0, JWTSigningKeyStringOrigineel.length(), "DES");
    }
}
