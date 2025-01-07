package backendgrabstudent.backend_GrabStudent.Enums;

import java.util.Arrays;

public enum NotificationTypeEnum {
    SEND_REQUEST,
    CANCEL_REQUEST,
    ACCEPT_REQUEST,
    REJECT_REQUEST,
    REVIEW,
    CANCEL_RIDE
    ;

    public static boolean isValidType(String type) {
        return Arrays.stream(values()).anyMatch(e -> e.name().equals(type.toUpperCase()));
    }
}
