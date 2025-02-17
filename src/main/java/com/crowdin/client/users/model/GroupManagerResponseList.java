package com.crowdin.client.users.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class GroupManagerResponseList {
    private List<GroupManagerResponseObject> data;
    private Pagination pagination;

    public static ResponseList<GroupManager> to(GroupManagerResponseList groupManagerResponseList) {
        return ResponseList.of(
                groupManagerResponseList.getData().stream()
                        .map(GroupManagerResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                groupManagerResponseList.getPagination()
        );
    }
}
