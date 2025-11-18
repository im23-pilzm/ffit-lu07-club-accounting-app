package ch.bzz.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {

    @GetMapping("/api/hello")
    public String hello() {
        log.debug("Received request for /api/hello");
        return "Hello World!";
    }
}
