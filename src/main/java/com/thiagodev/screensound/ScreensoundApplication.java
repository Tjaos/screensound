package com.thiagodev.screensound;

import com.thiagodev.screensound.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreensoundApplication implements CommandLineRunner {
    private final Principal principal;

    public ScreensoundApplication(Principal principal){
        this.principal = principal;
    }

    @Override
    public void run(String... args) {
        principal.exibeMenu();
    }


    public static void main(String[] args) {
        SpringApplication.run(ScreensoundApplication.class, args);
    }
}
