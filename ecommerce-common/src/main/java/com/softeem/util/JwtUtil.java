package com.softeem.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.util.Date;
import java.util.Map;

public class JwtUtil {
    private static final String SECRET_KEY="MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuyCI6VAUlq7Ri6OnwY0AD4vtzssYEaJQYUZP9B1m9awrJpv0aY/6O4ddOXI5K01pTabTTUnjsu7JZy9nTc/Hm0Ky/4r+YvVomjAR3vSqgaGvNfm0EJ1KObMkR0eJ6F7l1mynUe/rKDEbK5XVoCeOGlyQLU6YBKFPkMcp0XiFelTJ2kJD+GE6f8pfQ3R2hIiTBvCbDUh+GTgM8GxtIqtNVg8oD7R8rAlZWWgRw+hIxTvcrBaF6dXCA+HhwtqShSOkehhlBInd1j8V8k/kBapJLVGts6xf1h3JIvKFQ1aozbU7uxCLLEGX9Pw8KDg/XMKHP7RsFCj4PMIVow1R360pswIDAQAB";
    private static final Duration exp = Duration.ofHours(2);

    public static String generate(String username){
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis()+exp.toMillis()))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
                .compact();
    }

    public static String generate(Map<String,Object> params){
        return Jwts.builder()
                .setIssuedAt(new Date())
                .addClaims(params)
                .setExpiration(new Date(System.currentTimeMillis()+exp.toMillis()))
                .signWith(SignatureAlgorithm.HS256,SECRET_KEY)
                .compact();
    }


    public static Claims parse(String token){
        if(token == null){
            return null;
        }
        Claims claims =null;
        try {
            claims = Jwts.parser().
                    setSigningKey(SECRET_KEY).
                    parseClaimsJws(token).
                    getBody();
        }catch (Exception ex){
        }
        return claims;


    }

}
