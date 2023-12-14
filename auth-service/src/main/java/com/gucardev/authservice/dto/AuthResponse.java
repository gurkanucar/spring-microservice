package com.gucardev.authservice.dto;

import java.util.Set;

public record AuthResponse(String username, Set<String> roles) {}
