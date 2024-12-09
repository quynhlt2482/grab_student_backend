package backendgrabstudent.backend_GrabStudent.Security;


import backendgrabstudent.backend_GrabStudent.Exception.CustomException;
import backendgrabstudent.backend_GrabStudent.Exception.ErrorNumber;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}")
    private String SECRET_KEY;


    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 2; // 1 hour
    private final long REFRESH_TOKEN_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7;

    public String generateToken(String email, Integer studentId) {
        return Jwts.builder()
                .setSubject(email)
                .claim("student_id", studentId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String generateRefreshToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractEmail(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Integer extractStudentIdFromRequest(HttpServletRequest request) {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = extractTokenFromHeader(authorizationHeader);

            try {
                Claims claims = decodeJwt(token, SECRET_KEY);
                Object studentId = claims.get("student_id");

                if (studentId == null) {
                    throw new CustomException(ErrorNumber.ID_STUDENT_NOT_FOUND_IN_TOKEN);
                }

                return Integer.parseInt(studentId.toString());
            } catch (JwtException | IllegalArgumentException e) {
                throw new CustomException(ErrorNumber.TOKEN_EXPIRED);
            }
        } else {
            throw new CustomException(ErrorNumber.AUTHORITY_NOT_EXISTED);
        }
    }

    public static Claims decodeJwt(String token, String secret_key) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret_key)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (JwtException | IllegalArgumentException e) {
            throw new CustomException(ErrorNumber.TOKEN_EXPIRED);
        }
    }


    public static String extractTokenFromHeader(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        } else {
            throw new CustomException(ErrorNumber.AUTHORITY_NOT_EXISTED);
        }
    }

}
