package com.simplon.securiteratt.constant;

public class JWTUtil {
    public static final long EXPIRE_ACCESS_TOKEN = 10*60*1000;
    public static final String ISSUER = "SpringBootApp";
    public static final  String SECRET_KEY = "Omayma17122001";
    public static final  long EXPIRE_REFRESH_TOKEN = 120*60*1000;//2 hours
    public static final String BEARER_PRIFIX="Bearer ";
    public static final String AUTH_HEADER = "Authorization";

}
