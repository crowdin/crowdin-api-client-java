package com.crowdin.client.reports.model;

import lombok.Data;

import java.util.List;

@Data
public class NetRateSchemes {
    private List<Match> tmMatch;
    private List<Match> mtMatch;
    private List<Match> suggestionMatch;
    private List<Match> aiMatch;
}
