package common;

import java.time.Instant;

public record ApiResponse<T>(String message, T data, Instant timeStamp) {
    public static <T> ApiResponse<T> of(String message, T data) {
        return new ApiResponse<T>(message, data, Instant.now());
    }
}