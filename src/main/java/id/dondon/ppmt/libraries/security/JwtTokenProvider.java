package id.dondon.ppmt.libraries.security;

import static id.dondon.ppmt.libraries.security.SecurityConstants.EXPIRATION_TIME;
import static id.dondon.ppmt.libraries.security.SecurityConstants.SECRET;

import id.dondon.ppmt.constant.UserField;
import id.dondon.ppmt.domain.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

  protected final Log logger = LogFactory.getLog(getClass());

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

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
      return true;
    } catch (SignatureException ex) {
      logger.error("Invalid JWT Signature");
    } catch (MalformedJwtException ex) {
      logger.error("Invalid JWT Token");
    } catch (ExpiredJwtException ex) {
      logger.error("Expired JWT token");
    } catch (UnsupportedJwtException ex) {
      logger.error("Unsupported JWT token");
    } catch (IllegalArgumentException ex) {
      logger.error("JWT claims string is empty");
    }

    return false;
  }

  public Long getUserIdFromJWT(String token) {
    Claims claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
    String id = (String)claims.get(UserField.ID);

    return Long.parseLong(id);
  }

}
