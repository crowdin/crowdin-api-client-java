package com.crowdin.client.users.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserResponseList {

    private List<UserResponseObject> data;
    private Pagination pagination;

    public static ResponseList<User> to(UserResponseList userResponseList) {
        return ResponseList.of(
                userResponseList.getData().stream()
                        .map(UserResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                userResponseList.getPagination()
        );
    }
}
