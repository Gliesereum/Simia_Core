package com.simia.share.common.exception.messages;

/**
 * @author vitalij
 */
public class ExpertExceptionMessage {

    private ExpertExceptionMessage() {
    }

    public static final ExceptionMessage CERTIFICATE_NOT_FOUND = new ExceptionMessage(1400, 400, "Certificate not found");
    public static final ExceptionMessage NOT_PERMISSION_TO_CERTIFICATE = new ExceptionMessage(1401, 400, "You don't have permission to this certificate");
    public static final ExceptionMessage FOLLOWER_NOT_FOUND = new ExceptionMessage(1402, 400, "Follower not found");
    public static final ExceptionMessage NOT_PERMISSION_TO_FOLLOWER = new ExceptionMessage(1403, 400, "You don't have permission to this follower");
    public static final ExceptionMessage GIVE_AWAY_NOT_FOUND = new ExceptionMessage(1404, 400, "Give away not found");
    public static final ExceptionMessage GIVE_AWAY_TIME_EXPIRED = new ExceptionMessage(1405, 400, "Give away not found");
    public static final ExceptionMessage INCORRECT_START_DATE = new ExceptionMessage(1406, 400, "Incorrect start date");
    public static final ExceptionMessage MEDIA_IS_NULL = new ExceptionMessage(1407, 400, "Media can't be null");
    public static final ExceptionMessage NOT_PERMISSION_TO_CONTENT = new ExceptionMessage(1408, 400, "You don't have permission to this content");
    public static final ExceptionMessage COMMENT_FOR_USER_EXIST = new ExceptionMessage(1409, 400, "Comment for current user exist");
    public static final ExceptionMessage CURRENT_USER_CANT_EDIT_THIS_COMMENT = new ExceptionMessage(1410, 401, "Current user cant't edit this comment");
    public static final ExceptionMessage COMMENT_NOT_FOUND = new ExceptionMessage(1411, 404, "Comment not found");
    public static final ExceptionMessage EXPERT_FOR_USER_EXIST = new ExceptionMessage(1412, 400, "Expert for user exist");
    public static final ExceptionMessage EXPERT_FOR_USER_NOT_EXIST = new ExceptionMessage(1413, 400, "Expert for user not exist");
    public static final ExceptionMessage CATEGORY_NOT_FOUND = new ExceptionMessage(1414, 404, "Category not found");
    public static final ExceptionMessage NOT_EXPERT_TO_ACTION = new ExceptionMessage(1415, 404, "You must be expert to do this action");
    public static final ExceptionMessage EXPERT_NOT_FOUND = new ExceptionMessage(1416, 404, "Expert not found");
    public static final ExceptionMessage NOT_PERMISSION_TO_PACKAGE = new ExceptionMessage(1417, 400, "You don't have permission to this package");
    public static final ExceptionMessage CREATE_PACKAGE_WITH_OUT_CONTENT = new ExceptionMessage(1418, 400, "You can't create package without content");
    public static final ExceptionMessage CONTENT_NOT_FOUND = new ExceptionMessage(1419, 404, "Content not found");
    public static final ExceptionMessage PROMOTION_NOT_FOUND = new ExceptionMessage(1420, 404, "Promotion not found");
    public static final ExceptionMessage NOT_PERMISSION_TO_PROMOTION = new ExceptionMessage(1421, 400, "You don't have permission to this promotion");
    public static final ExceptionMessage PACKAGE_NOT_FOUND = new ExceptionMessage(1422, 404, "Package not found");
    public static final ExceptionMessage SPEAKER_NOT_FOUND = new ExceptionMessage(1423, 404, "Speaker not found");
    public static final ExceptionMessage NOT_ENOUGH_MONEY = new ExceptionMessage(1424, 400, "Not enough money for make transaction");
    public static final ExceptionMessage UNEXPECTED_ERROR = new ExceptionMessage(1425, 400, "Unexpected error, please make transaction later");




}
