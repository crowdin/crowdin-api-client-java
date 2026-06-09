package com.crowdin.client.core.http.impl.json;

import com.crowdin.client.sourcefiles.model.DocxFileImportOptions;
import com.crowdin.client.sourcefiles.model.ImportOptions;
import com.crowdin.client.sourcefiles.model.OtherFileImportOptions;
import com.crowdin.client.sourcefiles.model.SpreadsheetFileImportOptions;
import com.crowdin.client.sourcefiles.model.XmlFileImportOptions;
import tools.jackson.core.JsonParser;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JsonNode;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.ValueDeserializer;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class FileImportOptionsDeserializer extends ValueDeserializer<ImportOptions> {

    private final ObjectMapper objectMapper;

    public FileImportOptionsDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public ImportOptions deserialize(JsonParser p, DeserializationContext ctxt) {
        JsonNode treeNode = ctxt.readTree(p);
        Iterable<String> iterable = treeNode.propertyNames();
        List<String> fields = StreamSupport
                .stream(iterable.spliterator(), false)
                .collect(Collectors.toList());
        if (fields.contains("cleanTagsAggressively")) {
            return this.objectMapper.readValue(treeNode.toString(), DocxFileImportOptions.class);
        } else if (fields.contains("firstLineContainsHeader")) {
            return this.objectMapper.readValue(treeNode.toString(), SpreadsheetFileImportOptions.class);
        } else if (fields.contains("translateContent")) {
            return this.objectMapper.readValue(treeNode.toString(), XmlFileImportOptions.class);
        } else {
            return this.objectMapper.readValue(treeNode.toString(), OtherFileImportOptions.class);
        }
    }
}
