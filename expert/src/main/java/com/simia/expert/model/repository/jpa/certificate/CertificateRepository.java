package com.simia.expert.model.repository.jpa.certificate;

import com.simia.expert.model.entity.certificate.CertificateEntity;
import com.simia.share.common.model.enumerated.ObjectState;
import com.simia.share.common.repository.AuditableRepository;

import java.util.List;
import java.util.UUID;

public interface CertificateRepository extends AuditableRepository<CertificateEntity> {

    List<CertificateEntity> getAllByExpertIdAndObjectState(UUID expertId, ObjectState state);
}