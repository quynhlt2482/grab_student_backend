package backendgrabstudent.backend_GrabStudent.Exception;

public class ErrorResponse {
    private String message;
    private String errorCode; // Mã lỗi (nếu cần)
    private long timestamp;   // Thời gian xảy ra lỗi

    public ErrorResponse(String message) {}
    public ErrorResponse(String message, String errorCode) {
        this.message = message;
        this.errorCode = errorCode;
        this.timestamp = System.currentTimeMillis();
    }

    // Getters và Setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
