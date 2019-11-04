package com.simia.share.common.exception.messages;

/**
 * @author vitalij
 */
public class KarmaExceptionMessage {

    private KarmaExceptionMessage() {
    }

    public static final ExceptionMessage CAR_NOT_FOUND = new ExceptionMessage(1400, 400, "Car not found");
    public static final ExceptionMessage SERVICE_CLASS_NOT_FOUND = new ExceptionMessage(1410, 400, "Service class not found");
    public static final ExceptionMessage SERVICE_PRICE_NOT_FOUND = new ExceptionMessage(1411, 400, "Service price not found");
    public static final ExceptionMessage DONT_HAVE_PERMISSION_TO_ACTION_BUSINESS = new ExceptionMessage(1420, 401, "You don't have permission to action this business");
    public static final ExceptionMessage BUSINESS_NOT_FOUND = new ExceptionMessage(1421, 404, "Business not found");
    public static final ExceptionMessage BUSINESS_ID_EMPTY = new ExceptionMessage(1422, 400, "Business id is empty");
    public static final ExceptionMessage BUSINESS_NOT_HAVE_WORKING_TIME = new ExceptionMessage(1423, 401, "Business does not have work time");

    public static final ExceptionMessage WORKING_TIME_BUSY = new ExceptionMessage(1424, 403, "This time is busy other worker");

    public static final ExceptionMessage SERVICE_NOT_FOUND = new ExceptionMessage(1425, 400, "Service not found");
    public static final ExceptionMessage SERVICE_NOT_CHOOSE = new ExceptionMessage(1426, 400, "Service not choose");
    public static final ExceptionMessage PACKAGE_NOT_FOUND = new ExceptionMessage(1427, 400, "Package not found ");

    public static final ExceptionMessage WORKING_SPACE_NOT_FOUND_IN_THIS_BUSINESS = new ExceptionMessage(1428, 404, "Business does not have this working space ");
    public static final ExceptionMessage WORKING_SPACE_ID_EMPTY = new ExceptionMessage(1429, 400, "Working space id is null ");
    public static final ExceptionMessage NOT_ENOUGH_TIME_FOR_RECORD = new ExceptionMessage(1430, 400, "Not enough time for create record, choose another time");
    public static final ExceptionMessage RECORD_NOT_FOUND = new ExceptionMessage(1431, 404, "Record not found");

    public static final ExceptionMessage TIME_BEGIN_EMPTY = new ExceptionMessage(1432, 400, "Time begin is empty");
    public static final ExceptionMessage PACKAGE_NOT_FOUND_IN_BUSINESS = new ExceptionMessage(1433, 404, "This package not found in business");
    public static final ExceptionMessage BUSINESS_NOT_WORK_THIS_DAY = new ExceptionMessage(1434, 400, "Business does not work this day");
    public static final ExceptionMessage BUSINESS_NOT_WORK_THIS_TIME = new ExceptionMessage(1435, 400, "Business does not work this time");

    public static final ExceptionMessage WORKING_TIME_EXIST_IN_THIS_BUSINESS = new ExceptionMessage(1436, 400, "Time working already exist in this business");
    public static final ExceptionMessage WORKING_TIME_NOT_FOUND = new ExceptionMessage(1437, 404, "Time working not found");

    public static final ExceptionMessage DONT_HAVE_PERMISSION_TO_ACTION_RECORD = new ExceptionMessage(1438, 400, "You don't have permission to action this record");

    public static final ExceptionMessage TIME_BEGIN_PAST = new ExceptionMessage(1439, 400, "Try to choose past time");


    public static final ExceptionMessage ANONYMOUS_CANT_COMMENT = new ExceptionMessage(1440, 401, "Anonymous user can't comment");
    public static final ExceptionMessage COMMENT_FOR_USER_EXIST = new ExceptionMessage(1441, 400, "Comment for current user exist");
    public static final ExceptionMessage CURRENT_USER_CANT_EDIT_THIS_COMMENT = new ExceptionMessage(1442, 401, "Current user cant't edit this comment");
    public static final ExceptionMessage COMMENT_NOT_FOUND = new ExceptionMessage(1443, 404, "Comment not found");


    public static final ExceptionMessage MEDIA_NOT_FOUND_BY_ID = new ExceptionMessage(1450, 404, "Media not found by id");
    public static final ExceptionMessage PACKAGE_NOT_HAVE_SERVICE = new ExceptionMessage(1451, 400, "Package dont have any services ");

    public static final ExceptionMessage CORPORATION_ID_REQUIRED_FOR_THIS_ACTION = new ExceptionMessage(1452, 400, "Corporation id required for this action");

    public static final ExceptionMessage CAR_ID_EMPTY = new ExceptionMessage(1453, 400, "Car id is empty");
    public static final ExceptionMessage SERVICE_PRICE_NOT_FOUND_IN_BUSINESS = new ExceptionMessage(1454, 404, "Service price not found in this business");
    public static final ExceptionMessage TARGET_ID_IS_EMPTY = new ExceptionMessage(1455, 400, "Target id is empty");
    public static final ExceptionMessage STATUS_TYPE_IS_EMPTY = new ExceptionMessage(1456, 400, "Status type is empty");

    public static final ExceptionMessage FILTER_ATTRIBUTE_NOT_FOUND = new ExceptionMessage(1457, 400, "Filter attribute not found");
    public static final ExceptionMessage FILTER_ATTRIBUTE_NOT_FOUND_BY_SERVICE_TYPE = new ExceptionMessage(1458, 400, "Filter attribute not found by this service type");
    public static final ExceptionMessage FILTER_ATTRIBUTE_NOT_FOUND_WITH_PRICE = new ExceptionMessage(1459, 400, "Filter attribute with price not found");

    public static final ExceptionMessage SERVICE_ID_IS_EMPTY = new ExceptionMessage(1460, 400, "Service id is empty");
    public static final ExceptionMessage WORKING_SPACE_ID_IS_EMPTY = new ExceptionMessage(1461, 400, "Working space id is empty");
    public static final ExceptionMessage USER_ALREADY_EXIST_LIKE_WORKER_IN_BUSINESS = new ExceptionMessage(1462, 400, "User already exist like worker in this business");
    public static final ExceptionMessage WORKER_NOT_FOUND = new ExceptionMessage(1463, 404, "Worker not found");

    public static final ExceptionMessage SERVICE_CLASS_ID_IS_EMPTY = new ExceptionMessage(1464, 400, "Service class id is empty");
    public static final ExceptionMessage PRICE_ID_IS_EMPTY = new ExceptionMessage(1465, 400, "Price id is empty");
    public static final ExceptionMessage PAR_SERVICE_CLASS_ID_AND_PRICE_ID_EXIST = new ExceptionMessage(1466, 400, "Par service id and price id exist");
    public static final ExceptionMessage PACKAGE_ID_IS_EMPTY = new ExceptionMessage(1467, 400, "Package id is empty");
    public static final ExceptionMessage PAR_SERVICE_CLASS_ID_AND_PACKAGE_ID_EXIST = new ExceptionMessage(1468, 400, "Par service id and package id exist");
    public static final ExceptionMessage PAR_CAR_ID_AND_FILTER_ATTRIBUTE_ID_EXIST = new ExceptionMessage(1469, 400, "Par car id and attribute id exist");
    public static final ExceptionMessage FILTER_ATTRIBUTE_ID_IS_EMPTY = new ExceptionMessage(1470, 400, "Filter attribute id is empty");
    public static final ExceptionMessage CAN_NOT_CHANGE_STATUS_CANCELED_OR_COMPLETED_RECORD = new ExceptionMessage(1471, 400, "You can't change status in canceled or completed record");
    public static final ExceptionMessage ALL_OBJECT_ID_NOT_EQUALS = new ExceptionMessage(1472, 400, "All object id must be equals");

    public static final ExceptionMessage BUSINESS_CATEGORY_BY_CODE_EXIST = new ExceptionMessage(1473, 400, "Business category with this code exist");
    public static final ExceptionMessage BUSINESS_CATEGORY_ID_EMPTY = new ExceptionMessage(1474, 400, "Business category id empty");
    public static final ExceptionMessage BUSINESS_CATEGORY_NOT_FOUND = new ExceptionMessage(1475, 400, "Business category not found");
    public static final ExceptionMessage DIFFERENT_BUSINESS_OR_CATEGORY_OF_BUSINESS = new ExceptionMessage(1476, 400, "You can't create working stace for different business or category of business");
    public static final ExceptionMessage TIME_IS_NOT_CORRECT = new ExceptionMessage(1477, 400, "Time is not correct");

    public static final ExceptionMessage WORKING_SPACE_INDEX_NUMBER_EXIST = new ExceptionMessage(1477, 400, "Working space index number exist");


    public static final ExceptionMessage CHAT_NOT_FOUND = new ExceptionMessage(1478, 404, "Chat not found");
    public static final ExceptionMessage NOT_PERMISSION_TO_CHAT = new ExceptionMessage(1479, 400, "You don't have permission to this chat");

    public static final ExceptionMessage TRY_CHANGE_DIFFERENT_BUSINESS = new ExceptionMessage(1480, 400, "You try to change different business");

    public static final ExceptionMessage CLIENT_NOT_FOUND = new ExceptionMessage(1481, 400, "Client not found");

    public static final ExceptionMessage USER_NOT_CLIENT_FOR_BUSINESS = new ExceptionMessage(1482, 400, "Client not client for business");

    public static final ExceptionMessage BUSINESS_IDS_OR_CORPORATION_IDS_SHOUT_BE_FULL = new ExceptionMessage(1483, 400, "Business ids or corporation ids shout be full");

    public static final ExceptionMessage FEEDBACK_FOR_RECORD_EXIST = new ExceptionMessage(1484, 400, "Feedback for record exist");

    public static final ExceptionMessage PIN_DOES_NOT_FOUNT = new ExceptionMessage(1485, 400, "Pin doesn't fount");

    public static final ExceptionMessage BUSINESS_MUST_BE_FROM_ONE_CORPORATION = new ExceptionMessage(1486, 400, "Business must be from one corporation");

    public static final ExceptionMessage ALL_TYPE_NOT_EQUALS = new ExceptionMessage(1487, 400, "All type must be equals");

    public static final ExceptionMessage DAYS_OF_WEEK_REPEATED = new ExceptionMessage(1488, 400, "Days can't be repeated");

    public static final ExceptionMessage TYPE_IS_NULL = new ExceptionMessage(1489, 400, "Type is null");

    public static final ExceptionMessage BUSINESS_TIME_ONLY = new ExceptionMessage(1490, 400, "These days business hours are only");

    public static final ExceptionMessage WORKER_NOT_WORK_THIS_TIME = new ExceptionMessage(1491, 400, "Worker does not work this time");
    public static final ExceptionMessage BUSINESS_DOES_NOT_ANY_WORKER = new ExceptionMessage(1492, 400, "Business does not any worker");
    public static final ExceptionMessage NOT_ENOUGH_TIME_CREATE_RECORD_FOR_THIS_WORKER = new ExceptionMessage(1493, 400, "Not enough time for create record from this worker, choose another time");
    public static final ExceptionMessage WORKER_ID_EMPTY = new ExceptionMessage(1494, 400, "Worker id is empty");
    public static final ExceptionMessage THIS_DAY_DO_NOT_WORK_WORKERS = new ExceptionMessage(1495, 400, "This day don't work workers");

    public static final ExceptionMessage AGENT_EXIST = new ExceptionMessage(1496, 400, "Agent exist");
    public static final ExceptionMessage CURRENT_USER_NOT_AGENT = new ExceptionMessage(1497, 400, "Current user not a agent");

    public static final ExceptionMessage TAG_NOT_FOUND = new ExceptionMessage(1498, 404, "Tag not found");
    
    public static final ExceptionMessage TAG_WITH_THIS_NAME_EXIST = new ExceptionMessage(1499, 400, "Tag with this name exist");

    public static final ExceptionMessage COUNTRY_NOT_FOUND = new ExceptionMessage(1500, 400, "Country not found");
    public static final ExceptionMessage CITY_NOT_FOUND = new ExceptionMessage(1501, 400, "City not found");
    public static final ExceptionMessage DISTRICT_NOT_FOUND = new ExceptionMessage(1502, 400, "District not found");
}
