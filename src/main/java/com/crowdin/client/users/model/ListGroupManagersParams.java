package com.crowdin.client.users.model;

import com.crowdin.client.core.model.OrderByField;
import lombok.Data;

import java.util.List;

@Data
public class ListGroupManagersParams {
    private List<Long> teamIds;
    private List<OrderByField> orderBy;
}
