package com.simia.expert.service.certificate;

import com.simia.expert.model.entity.certificate.CertificateEntity;
import com.simia.share.common.model.dto.expert.certificate.CertificateDto;
import com.simia.share.common.service.auditable.AuditableService;

import java.util.List;
import java.util.UUID;

public interface CertificateService extends AuditableService<CertificateDto, CertificateEntity> {

    List<CertificateDto> getAllByExpertId(UUID id);
}