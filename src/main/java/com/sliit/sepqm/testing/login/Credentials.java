package com.sliit.sepqm.testing.login;

public class Credentials {
    private String email;
    private String password;
    public Credentials() {}
    public Credentials(String e, String p) { email = e; password = p; }
    public String getEmail()  { return email; }
    public void   setEmail(String e) { email = e; }
    public String getPassword()  { return password; }
    public void   setPassword(String p) { password = p; }
}
