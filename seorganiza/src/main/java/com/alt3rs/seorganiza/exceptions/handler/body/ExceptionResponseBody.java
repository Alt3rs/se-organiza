package com.alt3rs.seorganiza.exceptions.handler.body;

import java.time.Instant;

public record ExceptionResponseBody(
        Instant timestamp,
        Integer status,
        String error,
        String path) {
}
