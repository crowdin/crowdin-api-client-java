package com.crowdin.client;

public interface Path {

    String BRANCHES = "/projects/%s/branches";
    String BRANCH = "/projects/%s/branches/%s";

    String BRANCH_DIRECTORY = "/projects/%s/branches/%s/files";
    String SUB_DIRECTORIES = "/projects/%s/directories/%s/directories";
    String PROJECT_DIRECTORY = "/projects/%s/directories";
    String DIRECTORY = "/projects/%s/directories/%s";

    String BRANCH_FILES = "/projects/%s/branches/%s/files";
    String DIRECTORY_FILES = "/projects/%s/directories/%s/files";
    String PROJECT_FILES = "/projects/%s/files";
    String FILE = "/projects/%s/files/%s";
    String FILE_RAW = "/projects/%s/files/%s/raw";

    String GLOSSARIES = "/glossaries";
    String GLOSSARY = "/glossaries/$s";
    String GLOSSARY_EXPORT = "/glossaries/$s/export";
    String GLOSSARY_IMPORT = "/glossaries/%s/import";
    String GLOSSARY_RAW = "/glossaries/%s/raw";
    String GROUP_GLOSSARIES = "/groups/%s/glossaries";
    String PROJECT_GLOSSARIES_EXPORT = "/projects/%s/glossaries/export";
    String PROJECT_GLOSSARIES_IMPORT = "/projects/%s/glossaries/import";
    String PROJECT_GLOSSARIES_RAW = "/projects/%s/glossaries/raw";
    String GLOSSARIES_EXPORT_JOBS = "/glossaries%s/%s/export/jobs/%s";
    String GLOSSARIES_IMPORT_JOBS = "/glossaries%s/%s/import/jobs/%s";
    String PROJECT_GLOSSARIES_EXPORT_JOBS = "/glossaries%s/%s/export/jobs/%s";
    String PROJECT_GLOSSARIES_IMPORT_JOBS = "/glossaries%s/%s/import/jobs/%s";

    String SUB_GROUPS = "/groups/%s/groups";
    String GROUPS = "/groups";
    String GROUP = "/groups/%s";

    String BACKGROUND = "/projects/%s/background";
    String LOGO = "/projects/%s/logo";

    String ISSUES = "/projects/%s/issues";
    String ISSUE = "/projects/%s/issues/%s";

    String PSEUDO_EXPORT_JOBS = "/projects/%s/pseudo/export/jobs/%s";
    String PSEUDO_EXPORT = "/projects/%s/pseudo/export";
    String PSEUDO_RAW = "/projects/%s/pseudo/raw";

    String TMS_GROUP_UPLOAD_JOBS = "/groups/%s/tms/%s/raw/jobs/%s";
    String TMS_PROJECT_UPLOAD_JOBS = "/projects/%s/tms/raw/jobs/%s";
    String TMS_GROUPS = "/groups/%s/tms";
    String TM = "/groups/%s/tms/%s";
    String TM_GROUP_EXPORT = "/groups/%s/tms/%s/export";
    String TM_GROUP_UPLOAD_RAW = "/groups/%s/tms/%s/raw";
    String TMS_PROJECT = "/projects/%s/tms";
    String TM_PROJECT_EXPORT = "/projects/%s/tms/export";
    String TM_PROJECT_UPLOAD_RAW = "/projects/%s/tms/raw";

    String LANGUAGES = "/languages";
    String LANGUAGE = "/languages/%s";

    String PRE_TRANSLATE = "/projects/%s/pre-translate";

    String PREVIEW = "/storages/%s/preview";

    String BRANCHES_PROGRESS = "/projects/%s/branches/%s/languages/progress";
    String DIRECTORIES_PROGRESS = "/projects/%s/directories/%s/languages/progress";
    String FILES_PROGRESS = "/projects/%s/files/%s/languages/progress";
    String PROJECTS_PROGRESS = "/projects/%s/languages/progress";

    String PROJECTS = "/projects";
    String PROJECT = "/projects/%s";
    String GROUP_PROJECTS = "/groups/%s/projects";

    String REFERENCES = "/projects/%s/files/%s/references";
    String REFERENCE = "/projects/%s/files/%s/references/%s";

    String REVISIONS = "/projects/%s/files/%s/revisions";
    String REVISION = "/projects/%s/files/%s/revisions/%s";

    String SCREENSHOTS = "/projects/%s/screenshots";
    String SCREENSHOT = "/projects/%s/screenshots/%s";

    String STORAGES = "/storages";
    String STORAGE = "/storages/%s";

    String FILE_SRTINGS = "/projects/%s/files/%s/strings";
    String FILE_STRING = "/projects/%s/files/%s/strings/%s";
    String PROJECTS_STRINGS = "/projects/%s/strings";
    String PROJECTS_STRING = "/projects/%s/strings/%s";

    String TERMS = "/glossaries/%s/terms";
    String TERM = "/glossaries/%s/terms/%s";

    String PROJECT_TRANSLATIONS = "/projects/%s/translations/builds";
    String DOWNLOAD_TRANSLATION = "/projects/%s/translations/builds/%s/download";
    String PROJECT_TRANSLATION = "/projects/%s/translations/builds/%s";
    String UPLOAD_LANGUAGE_TRANSLATIONS = "/projects/%s/translations/%s";

    String WEBHOOKS = "/projects/%s/webhooks";
    String WEBHOOK = "/projects/%s/webhooks/%s";
}
