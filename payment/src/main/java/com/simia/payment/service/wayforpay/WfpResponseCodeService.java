package com.simia.payment.service.wayforpay;

import com.simia.payment.model.entity.wayforpay.WfpResponseCodeEntity;
import com.simia.share.common.model.dto.payment.wayforpay.WfpResponseCodeDto;
import com.simia.share.common.service.DefaultService;

/**
 * @author vitalij
 * @since 9/5/19
 */
public interface WfpResponseCodeService extends DefaultService<WfpResponseCodeDto, WfpResponseCodeEntity> {

    WfpResponseCodeDto getByCode(Integer code);

}
