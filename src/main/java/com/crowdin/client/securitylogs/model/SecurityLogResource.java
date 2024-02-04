package com.crowdin.client.securitylogs.model;

import lombok.Data;

@Data
public class SecurityLogResource {
    Long id;
    String event;
    String info;
    Long userId;
    String location;
    String ipAddress;
    String deviceName;
    String createdAt;
}
