package com.mybasket.app.security;


import com.mybasket.app.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class JwtService {

    private final SecretKey key;
    private final long acccessTokenExpireTime;
    private final  String issuer;


    public JwtService (
          @Value("${security.jwt.secret}")  String secret,
            @Value("${security.jwt.access-token-expire-time}")  long acccessTokenExpireTime,
          @Value("${security.jwt.issuer}") String issuer
    ){

        this.acccessTokenExpireTime=acccessTokenExpireTime;
        // convert string to SecretKey
        this.key= Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.issuer=issuer;
    }

    public String generateAccessToken(User user) {


        Instant now = Instant.now();

        List<String> roles=  user.getRole()==null?List.of():List.of(user.getRole().toString());


        return    Jwts
                .builder()
                .id(UUID.randomUUID().toString())
                .subject(user.getUserid()+"")
                .issuer(issuer)
                .issuedAt(Date.from(now))
                .expiration(Date.from(now.plusMillis(acccessTokenExpireTime)))
                .claims(
                        Map.of(
                                "email",user.getEmail(),
                                "roles",roles,
                                "ty","access"
                        )
                )
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

    }

    public boolean isAccessToken(String token) {
        Claims payload = this.parse(token).getPayload();
        return payload.get("ty").equals("access");

    }

    public Jws<Claims> parse(String token) {

       return Jwts.parser().verifyWith(key).build().parseSignedClaims(token);

    }
}
