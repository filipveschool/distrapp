package be.ucll.forecast.api.generator.impl;

import be.ucll.forecast.api.generator.PasswordHashGenerator;
import javafx.scene.control.PasswordField;
import sun.security.util.Password;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.enterprise.context.ApplicationScoped;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by filip on 18/12/2016.
 */
@ApplicationScoped
public class PasswordHashGeneratorImplementation implements PasswordHashGenerator {

    @Override
    public String generateHash(String passwordNotEncrypted) {
        String hash = null;
        try {
            byte[] salt = new byte[16];
            Random random = new Random(System.currentTimeMillis());
            random.nextBytes(salt);
            KeySpec spec = new PBEKeySpec(passwordNotEncrypted.toCharArray(), salt, 65536, 128);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBEKSPwithHmacSHA1");
            hash = Arrays.toString(skf.generateSecret(spec).getEncoded());
        } catch (InvalidKeySpecException | NoSuchAlgorithmException exc) {
            Logger.getLogger(PasswordHashGeneratorImplementation.class.getName()).log(Level.SEVERE, null, exc);
        }
        return hash;
    }
}
