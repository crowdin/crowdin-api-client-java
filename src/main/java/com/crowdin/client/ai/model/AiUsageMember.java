package com.crowdin.client.ai.model;

import lombok.Data;

import java.util.Date;

@Data
public class AiUsageMember {
    private User user;
    private Double dailyCostLimit;
    private Double dailyCostSpent;
    private Date dailyResetAt;
    private Double monthlyCostLimit;
    private Double monthlyCostSpent;
    private Date monthlyResetAt;

    @Data
    public static class User {
        private Long id;
        private String username;
        private String fullName;
        private String avatarUrl;
    }
}
