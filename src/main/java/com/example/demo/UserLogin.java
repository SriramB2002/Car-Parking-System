package com.example.demo;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLogin {
    @CrossOrigin(origins = "http://localhost:5000")
    @PostMapping("/data")
    public String login(@RequestBody String user) {
        return "success";
    }
}
