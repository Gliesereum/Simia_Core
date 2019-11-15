package com.simia.expert.model.entity.information;

import com.simia.share.common.model.entity.AuditableDefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class InformationEntity extends AuditableDefaultEntity {
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "video_url")
	private String videoUrl;
	
	@Column(name = "iso_code")
	private String isoCode;
	
	@Column(name = "index")
	private Integer index;
	
}
