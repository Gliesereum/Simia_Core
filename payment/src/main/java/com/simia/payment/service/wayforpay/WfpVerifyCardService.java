package com.simia.payment.service.wayforpay;

import com.simia.share.common.model.dto.payment.payment.RequestCardInfoDto;
import com.simia.share.common.model.dto.payment.payment.UserCardDto;
import com.simia.share.common.model.dto.payment.wayforpay.WfpCallBackResponseDto;
import com.simia.share.common.model.dto.payment.wayforpay.WfpCardDto;

import java.util.Map;

/**
 * @author vitalij
 * @version 1.0
 */
public interface WfpVerifyCardService {

    void verifyCardResponse(Map<String, String> map);

    String getFormVerifyCard();

    UserCardDto addNewCard(RequestCardInfoDto card);

    WfpCardDto saveCard(WfpCallBackResponseDto response);
}
