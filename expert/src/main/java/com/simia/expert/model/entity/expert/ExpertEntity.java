package com.simia.expert.model.entity.expert;

import com.simia.share.common.model.entity.AuditableDefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.UUID;


@Data
@NoArgsConstructor
public class ExpertEntity extends AuditableDefaultEntity {
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "additional")
	private String additional;
}
