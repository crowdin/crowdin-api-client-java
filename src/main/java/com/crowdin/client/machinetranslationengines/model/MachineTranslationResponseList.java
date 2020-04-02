package com.crowdin.client.machinetranslationengines.model;

import com.crowdin.client.core.model.Pagination;
import com.crowdin.client.core.model.ResponseList;
import com.crowdin.client.core.model.ResponseObject;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class MachineTranslationResponseList {

    private List<MachineTranslationResponseObject> data;
    private Pagination pagination;

    public static ResponseList<MachineTranslation> to(MachineTranslationResponseList machineTranslationResponseList) {
        return ResponseList.of(
                machineTranslationResponseList.getData().stream()
                        .map(MachineTranslationResponseObject::getData)
                        .map(ResponseObject::of)
                        .collect(Collectors.toList()),
                machineTranslationResponseList.getPagination()
        );
    }
}
