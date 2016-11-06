package utils;

import java.util.Random;

public class Utils {
    public String generateTestEmail() {
        return getRandomString() + "@mail.com";
    }

    private String getRandomString() {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) {
            int index = (int) (rnd.nextFloat() * chars.length());
            salt.append(chars.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
}