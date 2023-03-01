/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.swiftride.utils;

/**
 *
 * @author sami
 */
public class Session {

    private static Session instance;

    private static String Email;
    private static String password;

    private Session(String Email, String password) {
        this.Email = Email;
        this.password = password;
    }

    public static Session getInstace(String Email, String password) {
        if (instance == null) {
            instance = new Session(Email, password);
        }
        return instance;
    }

    public static String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public static String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static void cleanUserSession() {
        Email = null;
        password = null;
        instance = null;
    }

    public static void updateUserSession(String Email, String password) {
        instance = new Session(Email, password);
    }

    @Override
    public String toString() {
        return "UserSession{"
                + "Email='" + Email + '\''
                + ", password=" + password
                + '}';
    }
}
