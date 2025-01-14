package com.Sintad_test.config.interfaces;

import jakarta.servlet.http.HttpServletRequest;

public interface TokenProvider {
    String getToken(HttpServletRequest request);
}
