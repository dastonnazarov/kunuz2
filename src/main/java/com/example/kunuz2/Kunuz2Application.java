package com.example.kunuz2;

import com.example.kunuz2.enums.ProfileRole;
import com.example.kunuz2.util.JwtUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Kunuz2Application {

    public static void main(String[] args) {
        SpringApplication.run(Kunuz2Application.class, args);
        System.out.println(JwtUtil.encode(2, ProfileRole.MODERATOR));
    }

}
