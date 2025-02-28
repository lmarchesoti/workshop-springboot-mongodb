package dev.lmarchesoti.workshopmongo.resources.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class URL {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone(ZoneId.of("GMT"));

    public static String decodeParam(String text) {
        try {
            return URLDecoder.decode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

    public static Instant decodeDate(String textDate, Instant defaultDate) {
        try {
            return LocalDate.parse(textDate, DATE_TIME_FORMATTER).atStartOfDay().toInstant(ZoneOffset.of("+00:00"));
        } catch (DateTimeParseException exception) {
            return defaultDate;
        }
    }
}
