package com.otmetkaX.service;

import com.otmetkaX.exception.CustomException;
import com.otmetkaX.model.Security;

public interface SecurityService {
    public enum RoleEnum {
        CLIENT,
        EMPLOYEE
    }
    Security inlet(String phoneNumber, int code) throws CustomException;
    Security findByToken(String token) throws CustomException;
    Security findByPhone(String phone) throws CustomException;
}