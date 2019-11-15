package com.simia.expert.model.entity.comment;

import com.simia.share.common.model.entity.AuditableDefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "comment")
public class CommentEntity extends AuditableDefaultEntity {
	
	@Column(name = "object_id")
	private UUID objectId;
	
	@Column(name = "text")
	private String text;
	
	@Column(name = "rating")
	private Integer rating;
	
	@Column(name = "owner_id")
	private UUID ownerId;
}
