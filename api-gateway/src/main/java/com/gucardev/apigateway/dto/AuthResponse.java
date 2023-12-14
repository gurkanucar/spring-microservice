package com.gucardev.apigateway.dto;

import java.util.Set;

public record AuthResponse(String username, Set<String> roles) {}
