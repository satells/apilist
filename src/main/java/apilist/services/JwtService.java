package apilist.services;

import java.time.Instant;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class JwtService {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private long expiration;

	public String generateToken(UserDetails userDetails) {
		Algorithm algorithm = Algorithm.HMAC256(secret);
		return JWT.create().withSubject(userDetails.getUsername()).withIssuedAt(new Date())
				.withExpiresAt(Date.from(Instant.now().plusSeconds(expiration))).sign(algorithm);
	}

	public String getUsernameFromToken(String token) {
		Algorithm algorithm = Algorithm.HMAC256(secret);
		return JWT.require(algorithm).build().verify(token).getSubject();
	}

	public boolean isTokenValid(String token, UserDetails userDetails) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			String username = JWT.require(algorithm).build().verify(token).getSubject();
			return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
		} catch (JWTVerificationException exception) {
			return false;
		}
	}

	private boolean isTokenExpired(String token) {
		Algorithm algorithm = Algorithm.HMAC256(secret);
		Date expiresAt = JWT.require(algorithm).build().verify(token).getExpiresAt();
		return expiresAt.before(new Date());
	}
}