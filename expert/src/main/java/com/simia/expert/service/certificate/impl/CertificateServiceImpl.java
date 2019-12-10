package com.simia.expert.service.certificate.impl;

import com.simia.expert.model.entity.certificate.CertificateEntity;
import com.simia.expert.model.repository.jpa.certificate.CertificateRepository;
import com.simia.expert.service.certificate.CertificateService;
import com.simia.expert.service.expert.ExpertService;
import com.simia.share.common.converter.DefaultConverter;
import com.simia.share.common.exception.CustomException;
import com.simia.share.common.model.dto.expert.certificate.CertificateDto;
import com.simia.share.common.model.dto.expert.expert.ExpertDto;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.service.auditable.impl.AuditableServiceImpl;
import com.simia.share.common.util.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static com.simia.share.common.exception.messages.ExpertExceptionMessage.*;


@Slf4j
@Service
public class CertificateServiceImpl extends AuditableServiceImpl<CertificateDto, CertificateEntity> implements CertificateService {

    private static final Class<CertificateDto> DTO_CLASS = CertificateDto.class;
    private static final Class<CertificateEntity> ENTITY_CLASS = CertificateEntity.class;

    private final CertificateRepository certificateRepository;

    @Autowired
    public CertificateServiceImpl(CertificateRepository certificateRepository, DefaultConverter defaultConverter) {
        super(certificateRepository, defaultConverter, DTO_CLASS, ENTITY_CLASS);
        this.certificateRepository = certificateRepository;
    }

    @Autowired
    private ExpertService expertService;

    @Override
    @Transactional
    public CertificateDto create(CertificateDto dto) {
        ExpertDto expert = getCurrentExpert();
        if (expert == null) {
            throw new CustomException(NOT_EXPERT_TO_ACTION);
        }
        dto.setExpertId(expert.getId());
        return super.create(dto);
    }

    @Override
    @Transactional
    public CertificateDto update(CertificateDto dto) {
        checkPermissionToActionCertificate(dto.getId());
        return super.update(dto);
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        checkPermissionToActionCertificate(id);
        super.delete(id);
    }

    @Override
    public List<CertificateDto> getAllByExpertId(UUID id) {
        List<CertificateDto> result = null;
        if (id != null) {
            List<CertificateEntity> entities = certificateRepository.getAllByExpertIdAndObjectState(id, ObjectState.ACTIVE);
            result = converter.convert(entities, dtoClass);
        }
        return result;
    }

    private void checkPermissionToActionCertificate(UUID id) {
        CertificateDto certificate = getByIdAndObjectState(id, ObjectState.ACTIVE);
        if (certificate == null) {
            throw new CustomException(CERTIFICATE_NOT_FOUND);
        }
        ExpertDto expert = getCurrentExpert();
        if (expert == null || certificate.getExpertId().equals(expert.getId())) {
            throw new CustomException(NOT_PERMISSION_TO_CERTIFICATE);
        }
    }

    private ExpertDto getCurrentExpert() {
        SecurityUtil.checkUserByBanStatus();
        return expertService.getByUserId(SecurityUtil.getUserId());
    }
}