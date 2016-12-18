package be.ucll.forecast.parser.filter;

/**
 * Created by filip on 18/12/2016.
 */

import javax.ws.rs.NameBinding;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * We maken gebruik van het keyword @interface
 * zodat we een signature kunnen voorzien voor onze custom annotatie.
 * <p>
 * Je kan deze interface klasse nu als @JWTSecuredFilter annotatie gebruiken.
 *
 * @NameBinding gebriiken we zodat men custom annotatie
 * geregistreerd wordt in JAVA EE aan de hand van JNDI (= JAVA NAMING DIRECTORY INTERFACE)
 * @Retention(RetentionPolicy.RUNTIME) gebruiken we om te zeggen wanneer
 * ons geannoteerd object beschikbaar is.
 * <p>
 * Door RUNTIME te gebruiken zeggen we dat de geannoteerde klasse beheerd
 * wordt door je JVM (JAVA EE Container)
 */
@NameBinding
@Retention(RetentionPolicy.RUNTIME)
//@Target({ElementType.TYPE, ElementType.METHOD})
public @interface BasicAuthenticationFilter {
}
