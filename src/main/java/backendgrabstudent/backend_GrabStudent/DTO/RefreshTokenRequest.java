package backendgrabstudent.backend_GrabStudent.DTO;

import lombok.Data;

@Data
public class RefreshTokenRequest {
    private String refreshToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
