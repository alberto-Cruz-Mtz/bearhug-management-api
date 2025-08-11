package site.bearhug.management.util.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import site.bearhug.management.util.jwt.exception.InvalidTokenException;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class JwtUtil {

    @Value("${security.private.key.path}")
    public String privateKeyPath;
    @Value("${security.public.key.path}")
    public String publicKeyPath;
    @Value("${security.secret.key}")
    public String secretKey;

    public String generateToken(Authentication authentication) throws JWTCreationException {
        Algorithm algorithm = KeyReading.getAlgorithmRSA(publicKeyPath, privateKeyPath);

        if (algorithm == null) return null;

        String username = authentication.getPrincipal().toString();
        String authorities = this.getAuthorities(authentication);

        return createToken(username, authorities, algorithm);
    }

    public String extractToken(String token) {
        return token.substring(7);
    }

    public DecodedJWT verifyToken(String token) {
        try {
            Algorithm algorithm = KeyReading.getAlgorithmRSA(publicKeyPath, privateKeyPath);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer("Bearhug-Management").build();
            return verifier.verify(token);
        } catch (JWTVerificationException exception) {
            throw new InvalidTokenException("Invalid authentication token.");
        }
    }

    public String extractUsername(DecodedJWT decoded) {
        return decoded.getSubject();
    }

    public Claim extractClaim(String claim, DecodedJWT decoded) {
        return decoded.getClaim(claim);
    }

    private String getAuthorities(Authentication authentication) {
        return authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
    }

    private String createToken(String username, String authorities, Algorithm algorithm) {
        return JWT.create()
                .withIssuer(this.secretKey)
                .withSubject(username)
                .withClaim("authorities", authorities)
                .withJWTId(UUID.randomUUID().toString())
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + 3600 * 1000))
                .withNotBefore(new Date(System.currentTimeMillis()))
                .sign(algorithm);
    }
}
