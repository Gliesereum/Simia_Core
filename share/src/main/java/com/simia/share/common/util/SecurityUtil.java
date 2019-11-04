package com.simia.share.common.util;

import com.simia.share.common.exception.client.ClientException;
import com.simia.share.common.model.dto.account.enumerated.BanStatus;
import com.simia.share.common.model.dto.account.user.UserDto;
import com.simia.share.common.security.model.UserAuthentication;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;
import java.util.UUID;

import static com.simia.share.common.exception.messages.CommonExceptionMessage.USER_IS_ANONYMOUS;
import static com.simia.share.common.exception.messages.UserExceptionMessage.USER_IN_BAN;

/**
 * @author yvlasiuk
 * @version 1.0
 */
public class SecurityUtil {

    private static final UUID ANONYMOUS_ID = UUID.fromString("00000000-0000-0000-0000-000000000000");

    private SecurityUtil() {
    }

    public static UserAuthentication getUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        return (UserAuthentication) authentication;
    }

    public static UUID getUserId() {
        UUID result = null;
        UserAuthentication user = getUser();
        if ((user != null) && (!user.isAnonymous())) {
            result = user.getUser().getId();
        }
        return result;
    }

    public static List<UUID> getUserCorporationIds() {
        List<UUID> result = null;
        UserAuthentication user = getUser();
        if ((user != null) && (user.getUser() != null) && (CollectionUtils.isNotEmpty(user.getUser().getCorporationIds()))) {
            result = user.getUser().getCorporationIds();
        }
        return result;
    }

    public static UserDto getUserModel() {
        UserDto result = null;
        UserAuthentication user = getUser();
        if ((user != null) && (!user.isAnonymous())) {
            result = user.getUser();
        }
        return result;
    }

    public static boolean userHavePermissionToCorporation(UUID corporationId) {
        boolean result = false;
        List<UUID> userCorporationIds = getUserCorporationIds();
        if (CollectionUtils.isNotEmpty(userCorporationIds) && userCorporationIds.contains(corporationId)) {
            result = true;
        }
        return result;
    }

    public static boolean isAnonymous() {
        UserAuthentication user = getUser();
        return (user == null) || user.isAnonymous();
    }

    public static UUID getApplicationId() {
        UUID result = null;
        UserAuthentication user = getUser();
        if ((user != null) && (user.getApplication() != null)) {
            result = user.getApplication().getId();
        }
        return result;
    }

    public static String getJwtToken() {
        UserAuthentication user = getUser();
        return (user != null) ? user.getJwtToken() : null;
    }

    public static void checkUserByBanStatus() {
        if (isAnonymous()) {
            throw new ClientException(USER_IS_ANONYMOUS);
        }
        UserDto user = SecurityUtil.getUser().getUser();
        if ((user.getBanStatus() != null) && user.getBanStatus().equals(BanStatus.BAN)) {
            throw new ClientException(USER_IN_BAN);
        }
    }

    public static UUID getAnonymousId() {
        return ANONYMOUS_ID;
    }
}
