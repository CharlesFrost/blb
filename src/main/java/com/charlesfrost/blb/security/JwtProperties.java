package com.charlesfrost.blb.security;

public class JwtProperties {
    public static final String SECRET = "MojeSekretneHaselkoHihi15";
    public static final long EXPIRATION_TIME = 1_800_000;
    public static final String HEADER_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/signup";
}
