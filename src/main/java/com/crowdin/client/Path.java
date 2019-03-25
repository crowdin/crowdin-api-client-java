package com.crowdin.client;

public interface Path {

    String BRANCHES = "/users/%s/projects/%s/branches";
    String BRANCH = "/users/%s/projects/%s/branches/%s";

    String BRANCH_DIRECTORY = "/users/%s/projects/%s/branches/%s/files";
    String SUB_DIRECTORIES = "/users/%s/projects/%s/directories/%s/directories";
    String PROJECT_DIRECTORY = "/users/%s/projects/%s/directories";
    String DIRECTORY = "/users/%s/projects/%s/directories/%s";

    String BRANCH_FILES = "/users/%s/projects/%s/branches/%s/files";
    String DIRECTORY_FILES = "/users/v/projects/%s/directories/%s/files";
    String PROJECT_FILES = "/users/%s/projects/%s/files";
    String FILE = "/users/%s/projects/%s/files/%s";
    String FILE_RAW = "/users/%s/projects/%s/files/%s/raw";

    String GLOSSARIES = "/users/%s/glossaries";
    String GLOSSARY = "/users/%s/glossaries/$s";
    String GLOSSARY_EXPORT = "/users/%s/glossaries/$s/export";
    String GLOSSARY_IMPORT = "/users/%s/glossaries/%s/import";
    String GLOSSARY_RAW = "/users/%s/glossaries/%s/raw";
    String GROUP_GLOSSARIES = "/users/%s/groups/%s/glossaries";
    String PROJECT_GLOSSARIES_EXPORT = "/users/%s/projects/%s/glossaries/export";
    String PROJECT_GLOSSARIES_IMPORT = "/users/%s/projects/%s/glossaries/import";
    String PROJECT_GLOSSARIES_RAW = "/users/%s/projects/%s/glossaries/raw";
    String GLOSSARIES_EXPORT_JOBS = "/users/%s/glossaries%s/%s/export/jobs/%s";
    String GLOSSARIES_IMPORT_JOBS = "/users/%s/glossaries%s/%s/import/jobs/%s";
    String PROJECT_GLOSSARIES_EXPORT_JOBS = "/users/%s/glossaries%s/%s/export/jobs/%s";
    String PROJECT_GLOSSARIES_IMPORT_JOBS = "/users/%s/glossaries%s/%s/import/jobs/%s";

    String SUB_GROUPS = "/users/%s/groups/%s/groups";
    String GROUPS = "/users/%s/groups";
    String GROUP = "/users/%s/groups/%s";

    String BACKGROUND = "/users/%s/projects/%s/background";
    String LOGO = "/users/%s/projects/%s/logo";

    String ISSUES = "/users/%s/projects/%s/issues";
    String ISSUE = "/users/%s/projects/%s/issues/%s";

    String PSEUDO_EXPORT_JOBS = "/users/%s/projects/%s/pseudo/export/jobs/%s";
    String PSEUDO_EXPORT = "/users/%s/projects/%s/pseudo/export";
    String PSEUDO_RAW = "/users/%s/projects/%s/pseudo/raw";

    String TMS_GROUP_UPLOAD_JOBS = "/users/%s/groups/%s/tms/%s/raw/jobs/%s";
    String TMS_PROJECT_UPLOAD_JOBS = "/users/%s/projects/%s/tms/raw/jobs/%s";
    String TMS_GROUPS = "/users/%s/groups/%s/tms";
    String TM = "/users/%s/groups/%s/tms/%s";
    String TM_GROUP_EXPORT = "/users/%s/groups/%s/tms/%s/export";
    String TM_GROUP_UPLOAD_RAW = "/users/%s/groups/%s/tms/%s/raw";
    String TMS_PROJECT = "/users/%s/projects/%s/tms";
    String TM_PROJECT_EXPORT = "/users/%s/projects/%s/tms/export";
    String TM_PROJECT_UPLOAD_RAW = "/users/%s/projects/%s/tms/raw";

    String LANGUAGES = "/users/%s/languages";
    String LANGUAGE = "/users/%s/languages/%s";

    String PRE_TRANSLATE = "/users/%s/projects/%s/pre-translate";

    String PREVIEW = "/users/%s/storages/%s/preview";

    String BRANCHES_PROGRESS = "/users/%s/projects/%s/branches/%s/languages/progress";
    String DIRECTORIES_PROGRESS = "/users/%s/projects/%s/directories/%s/languages/progress";
    String FILES_PROGRESS = "/users/%s/projects/%s/files/%s/languages/progress";
    String PROJECTS_PROGRESS = "/users/%s/projects/%s/languages/progress";

    String PROJECTS = "/users/%s/projects";
    String PROJECT = "/users/%s/projects/%s";
    String GROUP_PROJECTS = "/users/%s/groups/%s/projects";

    String REFERENCES = "/users/%s/projects/%s/files/%s/references";
    String REFERENCE = "/users/%s/projects/%s/files/%s/references/%s";

    String REVISIONS = "/users/%s/projects/%s/files/%s/revisions";
    String REVISION = "/users/%s/projects/%s/files/%s/revisions/%s";

    String SCREENSHOTS = "/users/%s/projects/%s/screenshots";
    String SCREENSHOT = "/users/%s/projects/%s/screenshots/%s";

    String SETTINGS = "/users/%s/projects/%s/settings";

    String STORAGES = "/users/%s/storages";
    String STORAGE = "/users/%s/storages/%s";

    String FILE_SRTINGS = "/users/%s/projects/%s/files/%s/strings";
    String FILE_STRING = "/users/%s/projects/%s/files/%s/strings/%s";
    String PROJECTS_STRINGS = "/users/%s/projects/%s/strings";
    String PROJECTS_STRING = "/users/%s/projects/%s/strings/%s";

    String TERMS = "/users/%s/glossaries/%s/terms";
    String TERM = "/users/%s/glossaries/%s/terms/%s";

    String PROJECT_TRANSLATIONS = "/users/%s/projects/%s/translations/builds";
    String DOWNLOAD_TRANSLATION = "/users/%s/projects/%s/translations/builds/%s/download";
    String PROJECT_TRANSLATION = "/users/%s/projects/%s/translations/builds/%s";
    String UPLOAD_LANGUAGE_TRANSLATIONS = "/users/%s/projects/%s/translations/%s";

    String WEBHOOKS = "/users/%s/projects/%s/webhooks";
    String WEBHOOK = "/users/%s/projects/%s/webhooks/%s";
}
