package com.iqbalnetwork.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDGenerator implements IRandomString{
    @Override
    public String random() {
        return UUID.randomUUID().toString();
    }
}