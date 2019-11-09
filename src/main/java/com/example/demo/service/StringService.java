package com.example.demo.service;

import org.springframework.stereotype.Service;

@Service
public class StringService {

    public String append(String str1, String str2) {
        return str1 + str2;
    }

    public boolean contains(String str1, String str2) {
        return str1.contains(str2);
    }

    public int len(String str1) {
        return str1.length();
    }

    public boolean equals(String str1, String str2) {
        return str1.equals(str2);
    }
}
