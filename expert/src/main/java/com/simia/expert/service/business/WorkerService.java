package com.simia.expert.service.business;

import com.simia.expert.model.entity.business.WorkerEntity;
import com.simia.share.common.model.dto.expert.business.LiteWorkerDto;
import com.simia.share.common.model.dto.expert.business.WorkerDto;
import com.simia.share.common.model.dto.expert.comment.CommentDto;
import com.simia.share.common.service.auditable.AuditableService;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author vitalij
 * @version 1.0
 */
public interface WorkerService extends AuditableService<WorkerDto, WorkerEntity> {

    List<WorkerDto> getByWorkingSpaceId(UUID workingSpaceId);

    WorkerDto findByUserIdAndBusinessId(UUID userId, UUID businessId);

    List<WorkerDto> findByUserId(UUID userId);

    boolean checkWorkerExistByPhone(String phone);

    WorkerDto getById(UUID id, boolean setUsers);

    List<WorkerDto> getByBusinessId(UUID businessId, boolean setUsers);

    Page<WorkerDto> getByBusinessId(UUID businessId, boolean setUsers, Integer page, Integer size);

    List<LiteWorkerDto> getLiteWorkerByBusinessId(UUID id);

    List<LiteWorkerDto> getLiteWorkerByIds(List<UUID> ids);

    List<WorkerDto> findByUserIdAndCorporationId(UUID userId, UUID corporationId);

    List<WorkerDto> getByCorporationId(UUID corporationId);

    Page<WorkerDto> getByCorporationId(UUID corporationId, Integer page, Integer size);

    void setUsers(List<WorkerDto> result);

    boolean existByUserIdAndCorporationId(UUID userId, UUID corporationId);

    boolean existByUserIdAndBusinessId(UUID userId, UUID businessId);

    LiteWorkerDto getLiteWorkerById(UUID workerId);

    Map<UUID, LiteWorkerDto> getLiteWorkerMapByIds(Collection<UUID> collect);

    Map<UUID, List<WorkerDto>> getWorkerMapByBusinessIds(List<UUID> businessIds);

    WorkerDto createWorkerWithUser(WorkerDto worker);

    CommentDto addComment(UUID objectId, UUID userId, CommentDto comment);

    CommentDto updateComment(UUID userId, CommentDto comment);

    void deleteComment(UUID commentId, UUID userId);
}
