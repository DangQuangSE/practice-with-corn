package dev.practice.foundations.web;

import java.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

public class RequestBodyLimitFilter extends OncePerRequestFilter {
    private static final long MAX_BODY_BYTES = 64 * 1024;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        // TODO BE-18: enforce the cap for known and unknown content lengths, then map rejection to 413 Problem Details.
        // Checking Content-Length alone is insufficient for chunked requests.
        chain.doFilter(request, response);
    }
}
