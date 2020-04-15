package com.example.demo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

/**
 * {@link TokenProvider} defines the JWT Provider.
 *
 * @author wahid.ousman.rughony
 * @version 1.0
 * @since 1.0
 */
@Component
@AllArgsConstructor
@Slf4j
public class TokenProvider {

    /**
     * Number of milliseconds in a second.
     */
    private static final int MILLISECONDS = 1000;

    /**
     * Delimiter used to separate assigned user authorities in a JWT.
     */
    private static final String AUTH_SEPARATOR = ",";

    /**
     * Key representing authorities.
     */
    private static final String AUTHORITIES_KEY = "auth";

    /**
     * Generate the JSON Web Token.
     *
     * @param authentication {@link Authentication } instance
     * @param rememberMe     Flag to indicate if the user has chosen to persist his token over a long period of time
     * @return The generated JSON Web Token
     */
    public String createToken(final Authentication authentication, final boolean rememberMe) {

        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(AUTH_SEPARATOR));

        long now = (new Date()).getTime();

        Date validity;

        if (rememberMe) {
            validity = new Date(now + MILLISECONDS * 864000);
        } else {
            validity = new Date(now + MILLISECONDS * 14400);
        }

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .signWith(SignatureAlgorithm.HS512, "49203de0c2f4e0dde036ae6ec144ba7ad9e33eb24cdc987015b63dbd3c6d8ece")
                .setExpiration(validity)
                .compact();
    }

    /**
     * Retrieve the {@link Authentication} instance from the given JWT.
     *
     * @param token The JWT token
     * @return {@link Authentication} instance
     */
    public final Authentication getAuthentication(final String token) {

        Claims claims = Jwts.parser()
                .setSigningKey("49203de0c2f4e0dde036ae6ec144ba7ad9e33eb24cdc987015b63dbd3c6d8ece")
                .parseClaimsJws(token)
                .getBody();

        String principal = claims.getSubject();

        Collection<? extends GrantedAuthority> authorities;

        if (!("").equals(claims.get(AUTHORITIES_KEY))) {
            authorities = Arrays.stream(claims.get(AUTHORITIES_KEY).toString().split(AUTH_SEPARATOR))
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        } else {
            authorities = AuthorityUtils.NO_AUTHORITIES;
        }

        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }

    /**
     * Check the validity of a JWT Token.
     *
     * @param authToken The JWT token
     * @return Whether the JWT token is valid
     */
    public final boolean validateToken(final String authToken) {

        try {
            Jwts.parser().setSigningKey("49203de0c2f4e0dde036ae6ec144ba7ad9e33eb24cdc987015b63dbd3c6d8ece").parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            return false;
        }
    }

}
