package com.crowdin.client.core.http.impl.json;

import com.crowdin.client.projectsgroups.model.*;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class FileFormatSettingsDeserializer extends JsonDeserializer<FileFormatSettingsResource> {

    private final ObjectMapper objectMapper;

    public FileFormatSettingsDeserializer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public FileFormatSettingsResource deserialize(JsonParser parser, DeserializationContext ctx) throws IOException {
        JsonNode parentNode = parser.getCodec().readTree(parser);

        FileFormatSettingsResource resource = this.objectMapper.readValue(parentNode.toString(), FileFormatSettingsResource.class);

        Class<? extends FileFormatSettings> classToUse;
        String format = parentNode.get("format").asText();

        switch (format) {
            case "properties":
                classToUse = PropertiesFileFormatSettings.class;
                break;
            case "xml":
                classToUse = XmlFileFormatSettings.class;
                break;
            case "webxml":
                classToUse = WebXmlFileFormatSettings.class;
                break;
            case "html":
                classToUse = HtmlFileFormatSettings.class;
                break;
            case "adoc":
                classToUse = AdocFileFormatSettings.class;
                break;
            case "android":
                classToUse = AndroidFileFormatSettings.class;
                break;
            case "md":
                classToUse = MdFileFormatSettings.class;
                break;
            case "mdxV1":
                classToUse = MdxV1FileFormatSettings.class;
                break;
            case "mdxV2":
                classToUse = MdxV2FileFormatSettings.class;
                break;
            case "fmMd":
                classToUse = FmMdFileFormatSettings.class;
                break;
            case "fmHtml":
                classToUse = FmHtmlFileFormatSettings.class;
                break;
            case "madcapFlsnp":
                classToUse = MadcapFlsnpFileFormatSettings.class;
                break;
            case "docx":
                classToUse = DocxFileFormatSettings.class;
                break;
            case "idml":
                classToUse = IdmlFileFormatSettings.class;
                break;
            case "mif":
                classToUse = MifFileFormatSettings.class;
                break;
            case "dita":
                classToUse = DitaFileFormatSettings.class;
                break;
            case "mediawiki":
                classToUse = MediaWikiFileFormatSettings.class;
                break;
            case "arb":
                classToUse = ArbFileFormatSettings.class;
                break;
            case "json":
                classToUse = JsonFileFormatSettings.class;
                break;
            case "js":
                classToUse = JavaScriptFileFormatSettings.class;
                break;
            case "fjs":
                classToUse = FJSFileFormatSettings.class;
                break;
            case "macosx":
                classToUse = MacOSXFileFormatSettings.class;
                break;
            case "chrome":
                classToUse = ChromeFileFormatSettings.class;
                break;
            case "react_intl":
                classToUse = ReactIntlFileFormatSettings.class;
                break;
            case "txt":
                classToUse = TxtFileFormatSettings.class;
                break;
            default:
                classToUse = OtherFileFormatSettings.class;
                break;
        }

        JsonNode settingsNode = parentNode.get("settings");
        FileFormatSettings settings = this.objectMapper.readValue(settingsNode.toString(), classToUse);
        resource.setSettings(settings);

        return resource;
    }
}
