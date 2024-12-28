package backendgrabstudent.backend_GrabStudent.Enums;

import java.util.Arrays;

public enum RideStatusEnum {
    GOING,
    DONE,
    CANCELED;

    public static boolean isValidStatus(String status) {
        return Arrays.stream(values()).anyMatch(e -> e.name().equals(status.toUpperCase()));
    }
}
