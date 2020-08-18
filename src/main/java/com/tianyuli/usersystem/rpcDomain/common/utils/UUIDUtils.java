package com.tianyuli.usersystem.rpcDomain.common.utils;

import java.util.UUID;

public class UUIDUtils {

    public static String get() {
        return UUID.randomUUID().toString();
    }
}
