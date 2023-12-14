package com.gucardev.projectservice.exception;

public record ExceptionMessage(String timestamp, int status, String message, String path) {}
