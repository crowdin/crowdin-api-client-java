package com.crowdin.client.applications.installations.model;

import com.crowdin.client.core.model.OrderByField;
import com.crowdin.client.core.model.Pagination;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ListApplicationInstallationsParams extends Pagination {

    private Long installedBy;
    private List<OrderByField> orderBy;
}
