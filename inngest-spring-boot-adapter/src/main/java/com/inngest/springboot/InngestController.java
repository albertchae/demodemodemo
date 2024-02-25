package com.inngest.springboot;

import com.inngest.CommHandler;
import com.inngest.CommResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class InngestController {
    @Autowired
    CommHandler commHandler;

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        commHandler.getHeaders().forEach(headers::add);
        return headers;
    }

    @GetMapping()
    public ResponseEntity<String> index() {
        String response = commHandler.introspect();
        return ResponseEntity.ok().headers(getHeaders()).body(response);
    }

    @PutMapping()
    public ResponseEntity<String> put() {
        String response = commHandler.register();
        return ResponseEntity.ok().headers(getHeaders()).body(response);
    }

    @PostMapping()
    public ResponseEntity<String> handleRequest(
        @RequestParam(name = "fnId") String functionId,
        @RequestBody String body
    ) {
        try {
            CommResponse response = commHandler.callFunction(functionId, body);

            return ResponseEntity.status(response.getStatusCode().getCode()).headers(getHeaders())
                .body(response.getBody());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(e.toString());
        }
    }
}