package com.crowdin.client.sourcefiles.model;

import com.crowdin.client.core.model.EnumConverter;

public enum Type implements EnumConverter<Type> {
    AUTO, ANDROID, MACOSX, RESX, PROPERTIES, GETTEXT, TYPE, PHP, JSON, XML, INI, RC,
    RESW, RESJSON, QTTS, JOOMLA, CHROME, DTD, DKLANG, FLEX, NSH, WXL, XLIFF, HTML, HAML,
    TXT, CSV, MD, FLSNP, FM_HTML, FM_MD, MEDIAWIKI, DOCX, SBV, VTT, SRT;

    public static Type from(String value) {
        return Type.valueOf(value.toUpperCase());
    }

    @Override
    public String to(Type v) {
        return v.name().toLowerCase();
    }
}
