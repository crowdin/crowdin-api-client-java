package com.crowdin.client.sourcefiles;

import com.crowdin.client.core.http.JsonTransformer;
import com.crowdin.client.core.http.impl.json.JacksonJsonTransformer;
import com.crowdin.client.sourcefiles.model.*;
import org.junit.jupiter.api.Test;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileOptionsDeserializationTest {

    private final JsonTransformer jsonTransformer = new JacksonJsonTransformer();

    @Test
    public void importOptionsRoundTripByFileType() {
        HtmlFileImportOptions html = new HtmlFileImportOptions();
        html.setExcludedElements(singletonList("p"));
        html.setInlineTags(singletonList("span"));
        html.setHideAttributeValues(true);
        html.setContentSegmentation(true);
        html.setSrxStorageId(1L);
        assertImportRoundTrip("html", html);

        HtmlWithFrontMatterFileImportOptions fmHtml = new HtmlWithFrontMatterFileImportOptions();
        fmHtml.setExcludedElements(singletonList("p"));
        fmHtml.setExcludedFrontMatterElements(singletonList("title"));
        fmHtml.setInlineTags(singletonList("b"));
        fmHtml.setHideAttributeValues(false);
        assertImportRoundTrip("fm_html", fmHtml);

        MdFileImportOptions md = new MdFileImportOptions();
        md.setExcludedFrontMatterElements(singletonList("author"));
        md.setExcludeCodeBlocks(true);
        md.setInlineTags(singletonList("kbd"));
        assertImportRoundTrip("md", md);

        MdxV1FileImportOptions mdxV1 = new MdxV1FileImportOptions();
        mdxV1.setExcludedFrontMatterElements(singletonList("slug"));
        mdxV1.setExcludeCodeBlocks(false);
        assertImportRoundTrip("mdx_v1", mdxV1);

        MdxV2FileImportOptions mdxV2 = new MdxV2FileImportOptions();
        mdxV2.setExcludedFrontMatterElements(singletonList("id"));
        mdxV2.setExcludeCodeBlocks(true);
        mdxV2.setSrxStorageId(5L);
        assertImportRoundTrip("mdx_v2", mdxV2);

        WebXmlFileImportOptions webXml = new WebXmlFileImportOptions();
        webXml.setInlineTags(singletonList("i"));
        webXml.setHideAttributeValues(true);
        assertImportRoundTrip("webxml", webXml);

        IdmlFileImportOptions idml = new IdmlFileImportOptions();
        idml.setInlineHyperlinkText(true);
        idml.setContentSegmentation(false);
        assertImportRoundTrip("idml", idml);

        AdocFileImportOptions adoc = new AdocFileImportOptions();
        adoc.setExcludeIncludeDirectives(true);
        assertImportRoundTrip("adoc", adoc);

        VdfFileImportOptions vdf = new VdfFileImportOptions();
        vdf.setConvertIcu(true);
        vdf.setAddGenderArgument(true);
        assertImportRoundTrip("vdf", vdf);

        StringCatalogFileImportOptions stringCatalog = new StringCatalogFileImportOptions();
        stringCatalog.setImportKeyAsSource(true);
        stringCatalog.setImportTranslations(false);
        assertImportRoundTrip("auto", stringCatalog);

        SpreadsheetFileImportOptions spreadsheet = new SpreadsheetFileImportOptions();
        spreadsheet.setFirstLineContainsHeader(true);
        spreadsheet.setImportHiddenSheets(true);
        spreadsheet.setImportHiddenRows(false);
        spreadsheet.setImportEqSuggestions(true);
        spreadsheet.setAutoApproveImported(false);
        spreadsheet.setTranslateHidden(true);
        spreadsheet.setAddToTm(false);
        spreadsheet.setContentSegmentation(true);
        spreadsheet.setSrxStorageId(7L);
        assertImportRoundTrip("csv", spreadsheet);

        XmlFileImportOptions xml = new XmlFileImportOptions();
        xml.setTranslateContent(true);
        xml.setInlineTags(singletonList("b"));
        assertImportRoundTrip("xml", xml);

        DocxFileImportOptions docx = new DocxFileImportOptions();
        docx.setCleanTagsAggressively(true);
        docx.setTranslateExcelSheetNames(true);
        docx.setWordHighlightColors(singletonList("yellow"));
        assertImportRoundTrip("docx", docx);
    }

    @Test
    public void vsdxImportOptionsAreReturnedAsDocx() {
        VsdxFileImportOptions vsdx = new VsdxFileImportOptions();
        vsdx.setCleanTagsAggressively(true);
        vsdx.setTranslateHyperlinkUrls(false);
        vsdx.setContentSegmentation(true);
        vsdx.setSrxStorageId(3L);

        File file = parseFile("vsdx", "importOptions", jsonTransformer.convert(vsdx));
        assertEquals(DocxFileImportOptions.class, file.getImportOptions().getClass());
        DocxFileImportOptions docx = (DocxFileImportOptions) file.getImportOptions();
        assertTrue(docx.getCleanTagsAggressively());
        assertEquals(Integer.valueOf(3), docx.getSrxStorageId());
    }

    @Test
    public void importOptionsResolvedByKeysWithoutFileType() {
        assertEquals(HtmlFileImportOptions.class, parseFile(null, "importOptions", "{\"excludedElements\":[\"p\"]}").getImportOptions().getClass());
        assertEquals(HtmlWithFrontMatterFileImportOptions.class, parseFile(null, "importOptions", "{\"excludedElements\":[\"p\"],\"excludedFrontMatterElements\":[\"a\"]}").getImportOptions().getClass());
        assertEquals(MdFileImportOptions.class, parseFile(null, "importOptions", "{\"excludeCodeBlocks\":true}").getImportOptions().getClass());
        assertEquals(IdmlFileImportOptions.class, parseFile(null, "importOptions", "{\"inlineHyperlinkText\":true}").getImportOptions().getClass());
        assertEquals(StringCatalogFileImportOptions.class, parseFile(null, "importOptions", "{\"importKeyAsSource\":true}").getImportOptions().getClass());
        assertEquals(AdocFileImportOptions.class, parseFile(null, "importOptions", "{\"excludeIncludeDirectives\":true}").getImportOptions().getClass());
        assertEquals(VdfFileImportOptions.class, parseFile(null, "importOptions", "{\"convertIcu\":true}").getImportOptions().getClass());
        assertEquals(OtherFileImportOptions.class, parseFile(null, "importOptions", "{\"contentSegmentation\":true}").getImportOptions().getClass());
        assertEquals(OtherFileImportOptions.class, parseFile("json", "importOptions", "{\"contentSegmentation\":true}").getImportOptions().getClass());
    }

    @Test
    public void exportOptionsRoundTrip() {
        MdFileExportOptions md = new MdFileExportOptions();
        md.setExportPattern("/%locale%/%original_file_name%");
        md.setStrongMarker(MdMarker.UNDERSCORE);
        md.setEmphasisMarker(MdMarker.ASTERISK);
        md.setUnorderedListBullet(MdUnorderedListBullet.PLUS);
        md.setTableColumnWidth(MdTableColumnWidth.CONSOLIDATE);
        md.setFrontMatterQuotes(MdFrontMatterQuotes.SINGLE);
        assertExportRoundTrip("md", md);
        assertExportRoundTrip(null, md);

        MdxV1FileExportOptions mdxV1 = new MdxV1FileExportOptions();
        mdxV1.setExportPattern("/%locale%/%original_file_name%");
        mdxV1.setStrongMarker(MdMarker.ASTERISK);
        mdxV1.setTableColumnWidth(MdTableColumnWidth.EVENLY_DISTRIBUTE_CELLS);
        assertExportRoundTrip("mdx_v1", mdxV1);

        MdxV2FileExportOptions mdxV2 = new MdxV2FileExportOptions();
        mdxV2.setExportPattern("/%locale%/%original_file_name%");
        mdxV2.setUnorderedListBullet(MdUnorderedListBullet.ASTERISKS);
        assertExportRoundTrip("mdx_v2", mdxV2);

        DocxFileExportOptions docx = new DocxFileExportOptions();
        docx.setExportPattern("/%locale%/%original_file_name%");
        docx.setAllowWordStyleOptimization(false);
        docx.setTranslateExcelExcludeColors(true);
        assertExportRoundTrip("docx", docx);
        assertExportRoundTrip(null, docx);

        GeneralFileExportOptions general = new GeneralFileExportOptions();
        general.setExportPattern("/%locale%/%original_file_name%");
        assertExportRoundTrip("json", general);
    }

    @Test
    public void exportOptionsSerialization() {
        MdFileExportOptions md = new MdFileExportOptions();
        md.setExportPattern("/a");
        md.setStrongMarker(MdMarker.ASTERISK);
        md.setUnorderedListBullet(MdUnorderedListBullet.DASH);
        md.setTableColumnWidth(MdTableColumnWidth.EVENLY_DISTRIBUTE_CELLS);
        md.setFrontMatterQuotes(MdFrontMatterQuotes.AUTO);
        assertEquals(
                "{\"exportPattern\":\"/a\",\"strongMarker\":\"asterisk\",\"unorderedListBullet\":\"dash\",\"tableColumnWidth\":\"evenly_distribute_cells\",\"frontMatterQuotes\":\"auto\"}",
                jsonTransformer.convert(md));
    }

    private void assertImportRoundTrip(String fileType, ImportOptions options) {
        File file = parseFile(fileType, "importOptions", jsonTransformer.convert(options));
        assertInstanceOf(ImportOptions.class, file.getImportOptions());
        assertEquals(options.getClass(), file.getImportOptions().getClass());
        assertEquals(options, file.getImportOptions());
    }

    private void assertExportRoundTrip(String fileType, ExportOptions options) {
        File file = parseFile(fileType, "exportOptions", jsonTransformer.convert(options));
        assertEquals(options.getClass(), file.getExportOptions().getClass());
        assertEquals(options, file.getExportOptions());
    }

    private File parseFile(String fileType, String optionsKey, String optionsJson) {
        String json = "{\"data\":{\"id\":1,"
                + (fileType != null ? "\"type\":\"" + fileType + "\"," : "")
                + "\"" + optionsKey + "\":" + optionsJson + "}}";
        FileResponseObject response = jsonTransformer.parse(json, FileResponseObject.class);
        assertInstanceOf(File.class, response.getData());
        return (File) response.getData();
    }
}
