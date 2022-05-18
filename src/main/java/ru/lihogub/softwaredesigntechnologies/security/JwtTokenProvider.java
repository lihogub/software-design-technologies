package ru.lihogub.softwaredesigntechnologies.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import ru.lihogub.softwaredesigntechnologies.entity.Principal;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class JwtTokenProvider {
    @Value("${app.jwt.secretKey}")
    private String secretKeyPlainText;

    @Value("${app.jwt.expiration}")
    private Long expiration;

    private SecretKey secretKey;
    private JwtParser jwtParser;

    public String createToken(Long userId, String username, String role) {
        Claims claims = Jwts.claims();
        claims.setId(userId.toString());
        claims.setSubject(username);
        claims.put("role", role);

        Date now = new Date();
        Date expiresAt = new Date(now.getTime() + expiration);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiresAt)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public Authentication getAuthentication(HttpServletRequest request) throws JwtException {
        String token = resolveToken(request);
        Jws<Claims> claims = validateTokenAndGetClaims(token);
        Principal principal = getPrincipal(claims);
        Collection<SimpleGrantedAuthority> authorities = getAuthorities(claims);
        return new UsernamePasswordAuthenticationToken(principal, null, authorities);
    }

    @PostConstruct
    protected void init() {
        secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKeyPlainText));
        jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build();
    }

    private String resolveToken(HttpServletRequest request) throws JwtException {
        String token = request.getHeader("Authorization");
        if (token == null || !token.startsWith("Bearer"))
            throw new JwtException("Token not found");
        return token.substring(7);
    }

    private Jws<Claims> validateTokenAndGetClaims(String token) throws JwtException {
        Jws<Claims> claims = jwtParser.parseClaimsJws(token);
        if (claims.getBody().getExpiration().before(new Date()))
            throw new JwtException("Token is expired");
        return claims;
    }

    private Principal getPrincipal(Jws<Claims> claims) {
        return new Principal(
                Long.parseLong(claims.getBody().getId()),
                claims.getBody().getSubject()
        );
    }

    private Collection<SimpleGrantedAuthority> getAuthorities(Jws<Claims> claims) {
        return Collections.singleton(new SimpleGrantedAuthority(claims.getBody().get("role", String.class)));
    }
}