package backendgrabstudent.backend_GrabStudent.Enums;

import java.util.Arrays;

public enum PostTypeEnum
{
    RIDER,
    PASSENGER;

    public static boolean isValidType(String type) {
        return Arrays.stream(values()).anyMatch(e -> e.name().equals(type.toUpperCase()));
    }
}
