package com.otmetkaX.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class QRCodeResponseMessage extends ResponseMessage {

    @JsonProperty("jwtToken")
    private byte[] image;

    public QRCodeResponseMessage(String status, String message, int code, String jwtToken, byte[] image) {
        super(status, message, code, jwtToken);
        this.image = image;
    }
}
