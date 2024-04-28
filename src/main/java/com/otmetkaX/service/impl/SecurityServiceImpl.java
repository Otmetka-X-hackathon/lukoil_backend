package com.otmetkaX.service.impl;

import com.otmetkaX.config.JwtTokenProvider;
import com.otmetkaX.exception.CustomException;
import com.otmetkaX.model.Security;
import com.otmetkaX.repository.SecurityRepository;
import com.otmetkaX.service.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SecurityServiceImpl implements SecurityService {

    private final int CODE = 1111;
    private final SecurityRepository repository;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public SecurityServiceImpl(SecurityRepository repository,  JwtTokenProvider jwtTokenProvider) {
        this.repository = repository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public Security inlet(String phoneNumber, int code) throws CustomException {
        validatePhoneNumber(phoneNumber);
        validateCode(code);
        Optional<Security> user =  repository.findByPhone(phoneNumber);
        if (!user.isPresent()) {
            String token = jwtTokenProvider.generateToken(phoneNumber);
            repository.save(new Security(phoneNumber, token, "CLIENT"));
            return findByPhone(phoneNumber);
        }
        return findByPhone(phoneNumber);
    }

    @Override
    public Security findByToken(String token) throws CustomException {
        Optional<Security> user =  repository.findByToken(token);
        if (!user.isPresent()) {
            throw new CustomException("TOKEN_NOT_FOUND", 404);
        }
        return user.get();
    }

    @Override
    public Security findByPhone(String phone) throws CustomException {
        Optional<Security> user = repository.findByPhone(phone);
        if (!user.isPresent()) {
            throw new CustomException("PHONE_NUMBER_NOT_FOUND", 404);
        }
        return user.get();
    }

    private void validatePhoneNumber(String phoneNumber) throws CustomException {
        String strippedNumber = phoneNumber.replaceAll("[^0-9]", "");
        if (strippedNumber.length() < 10 || strippedNumber.length() > 15) {
            throw new CustomException("PHONE_NUMBER_LENGTH_INVALID", 422);
        }
        if (!strippedNumber.matches("\\d{10,}")) {
            throw new CustomException("PHONE_NUMBER_INVALID", 422);
        }
    }

    private void validateCode(int code) throws CustomException {
        if (code != CODE) {
            throw  new CustomException("CODE_INVALID", 422);
        }
    }

    public void validateRole(String role) throws CustomException {
        try {
            RoleEnum.valueOf(role);
        } catch (IllegalArgumentException | NullPointerException e) {
            throw new CustomException("ROLE_INVALID", 422);
        }
    }
}
