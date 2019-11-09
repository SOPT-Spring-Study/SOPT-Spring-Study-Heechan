package com.example.demo.controller;

import com.example.demo.service.StringService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/string")
public class StringController {

    private final StringService stringService;

    public StringController(StringService stringService) {
        this.stringService = stringService;
    }

    @GetMapping("/append")
    public String append(@RequestParam String str1, @RequestParam String str2) {
        return stringService.append(str1, str2);
    }

    @GetMapping("/contains")
    public boolean contains(@RequestParam String str1, @RequestParam String str2) {
        return stringService.contains(str1, str2);
    }

    @GetMapping("/len")
    public int len(@RequestParam String str1) {
        return stringService.len(str1);
    }

    @GetMapping("/equals")
    public boolean equals(@RequestParam String str1, @RequestParam String str2) {
        return stringService.equals(str1, str2);
    }
}
