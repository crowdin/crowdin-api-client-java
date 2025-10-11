package com.crowdin.client.teams.model;

import com.crowdin.client.core.model.OrderByField;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class ListGroupTeamsParams {
    @NonNull
    private final Long groupId;
    private List<OrderByField> orderByList;
}
