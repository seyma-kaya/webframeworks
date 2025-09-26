package app.security;

import app.GlobalConfig;
import io.jsonwebtoken.*;
import jakarta.servlet.http.HttpServletRequest;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

public class JWToken {
    private static final String JWT_CALLNAME_CLAIM = "sub";
    private static final String JWT_USERID_CLAIM = "id";
    private static final String JWT_ROLE_CLAIM = "role";
    private static final String JWT_IPADDRESS_CLAIM = "ipa";
    public static final String JWT_ATTRIBUTE_NAME = "JWTokenInfo";


    private String callName = null;
    private Long userId = null;
    private String role = null;
    private String ipAddress;


    public JWToken(String callName, Long userId, String role) {
        this.callName = callName;
        this.userId = userId;
        this.role = role;
    }

    public String encode(String issuer, String passphrase, int expiration) {
        Key key = getKey(passphrase);
        return Jwts.builder()
                .claim(JWT_CALLNAME_CLAIM, this.callName)
                .claim(JWT_USERID_CLAIM, this.userId)
                .claim(JWT_ROLE_CLAIM, this.role)
                .claim(JWT_IPADDRESS_CLAIM, this.ipAddress != null ? this.ipAddress : "1.1.1.1")
                .setIssuer(issuer)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration * 1000L))
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();

    }

    private static Key getKey(String passphrase) {
        byte[] hmacKey = passphrase.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(hmacKey, SignatureAlgorithm.HS512.getJcaName());
    }
    public static JWToken decode(String token, String passphrase)
            throws ExpiredJwtException, MalformedJwtException {
        // Validate the token string and extract the claims
        Key key = getKey(passphrase);
        Jws<Claims> jws = Jwts.parserBuilder().setSigningKey(key).build()
                .parseClaimsJws(token);
        Claims claims = jws.getBody();

        JWToken jwToken = new JWToken(
                claims.get(JWT_CALLNAME_CLAIM).toString(),
                Long.valueOf(claims.get(JWT_USERID_CLAIM).toString()),
                        claims.get(JWT_ROLE_CLAIM).toString()
        );
        jwToken.setIpAddress((String) claims.get(JWT_IPADDRESS_CLAIM));
        return jwToken;

    }
        public String getCallName() {
        return callName;
    }

    public Long getUserId() {
        return userId;
    }

    public String getRole() {
        return role;
    }

    public String getIpAddress(HttpServletRequest request) {
        String ipAddress = null;
        if (GlobalConfig.IP_FORWARDED_FOR != null) {
            ipAddress = request.getHeader(GlobalConfig.IP_FORWARDED_FOR);
        }
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        return ipAddress;    }

    public void setCallName(String callName) {
        this.callName = callName;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }
}
