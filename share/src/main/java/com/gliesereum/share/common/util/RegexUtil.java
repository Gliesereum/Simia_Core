package com.gliesereum.share.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Pattern;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public class RegexUtil {

    private RegexUtil() {
    }

    public static final String REQUEST_URI_REGEX = "\\/.*\\/.{2,3}\\/.{2,}";
    public static final String UUID_REGEX = "[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}";
    public static final String PHONE_PATTERN = "\\+?[0-9]{6,15}";

    public static final Pattern phonePattern = Pattern.compile(PHONE_PATTERN);
    public static final Pattern uuidPattern = Pattern.compile(UUID_REGEX);

    public static boolean isUUID(String str) {
        return uuidPattern.matcher(str).matches();
    }

    public static String removeUUIDToStar(String str) {
        if (StringUtils.isNotEmpty(str)) {
            str = str.replaceAll(UUID_REGEX, "*");
        }
        return str;
    }

    public static boolean phoneIsValid(String phone) {
        return StringUtils.isNotBlank(phone) && phonePattern.matcher(phone).matches();
    }
}
