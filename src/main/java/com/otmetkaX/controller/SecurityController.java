package com.otmetkaX.controller;

import com.otmetkaX.exception.CustomException;
import com.otmetkaX.model.Security;
import com.otmetkaX.response.ResponseMessage;
import com.otmetkaX.response.ResponseMessageObject;
import com.otmetkaX.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/lukoil")
public class SecurityController {
    private final SecurityService service;
    @Autowired
    public SecurityController(SecurityService service) {
        this.service = service;
    }
    @PostMapping("login")
    public CompletableFuture<ResponseEntity<ResponseMessage>> postSecurity(@RequestParam String phoneNumber, @RequestParam int code) throws CustomException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Security user = service.inlet(phoneNumber, code);
                ResponseMessage response = new ResponseMessageObject("Successes", null, 200, user);
                return ResponseEntity.status(HttpStatus.OK).body(response);
            } catch (CustomException e) {
                ResponseMessage response = new ResponseMessage("Failed", e.getMessage(), e.getErrorCode(), null);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
            }
        });
    }

}
