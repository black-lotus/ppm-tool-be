package id.dondon.ppmt.libraries.security;

import static id.dondon.ppmt.libraries.security.SecurityConstants.EXPIRATION_TIME;
import static id.dondon.ppmt.libraries.security.SecurityConstants.SECRET;

import id.dondon.ppmt.constant.UserField;
import id.dondon.ppmt.domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

  //Generate the token

  public String generateToken(Authentication authentication) {
    User user = (User) authentication.getPrincipal();
    Date now = new Date(System.currentTimeMillis());

    Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);

    String userId = Long.toString(user.getId());

    Map<String,Object> claims = new HashMap<>();
    claims.put(UserField.ID, (Long.toString(user.getId())));
    claims.put(UserField.USERNAME, user.getUsername());
    claims.put(UserField.FULL_NAME, user.getFullName());

    return Jwts.builder()
        .setSubject(userId)
        .setClaims(claims)
        .setIssuedAt(now)
        .setExpiration(expiryDate)
        .signWith(SignatureAlgorithm.HS512, SECRET)
        .compact();
  }

  //Validate the token

  //Get user Id from token
}
