package com.tianyuli.usersystem.rpcDomain.common.utils;

import java.util.Random;

public class RandomCaptcha {

    private static char[] randString = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public static String get() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int count = 0;
        int len = randString.length;
        while (count++ < 4) {
            sb.append(randString[random.nextInt(len)]);
        }

        return sb.toString();
    }
}
