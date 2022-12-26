package com.adaLaundry.utility;

import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class JwtToken {

    private String SECRET_KEY = "liberate-tutume-ex-inferis-ad-astra-per-aspera";
    private String audience = "AdaLaundryWeb";

    private Claims getClaims(String token){
        JwtParser parser = Jwts.parser().setSigningKey(SECRET_KEY);
        Jws<Claims> signatureAndClaims = parser.parseClaimsJws(token);
        Claims claims = signatureAndClaims.getBody();

        return claims;
    }

    public String getUsername(String token){

        Claims claims = getClaims(token);

        return claims.get("username", String.class);
    }

    public String generateToken(String username, String role) {

        JwtBuilder builder = Jwts.builder();
        builder = builder
                .claim("username", username)
                .claim("role", role)
                .setIssuer("http://localhost:3000")
                .setAudience(this.audience)
                .signWith(SignatureAlgorithm.HS256, this.SECRET_KEY);// secret key harus minimal 32 character karna pake HS256

        return builder.compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails){

        Claims claims = getClaims(token);
        String user = claims.get("username", String.class);
        String audience = claims.getAudience();

        return (user.equals(userDetails.getUsername()) && audience.equals(audience));
    }
}
