package com.inngest.springbootdemo;

import com.inngest.springboot.InngestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/hello")
public class HelloWorldController {
    @GetMapping()
    public ResponseEntity<String> index() {
        return ResponseEntity.ok().body("hello world");
    }

}
