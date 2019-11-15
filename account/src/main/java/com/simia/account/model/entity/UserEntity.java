package com.simia.account.model.entity;

import com.simia.share.common.model.dto.account.enumerated.BanStatus;
import com.simia.share.common.model.dto.account.enumerated.Gender;
import com.simia.share.common.model.entity.AuditableDefaultEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

/**§
 * @author yvlasiuk
 * @version 1.0
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user")
public class UserEntity extends AuditableDefaultEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "address")
    private String address;
    
    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "cover_url")
    private String coverUrl;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "ban_status")
    @Enumerated(EnumType.STRING)
    private BanStatus banStatus;

    @Column(name = "last_sign_in")
    private LocalDateTime lastSignIn;

    @Column(name = "last_activity")
    private LocalDateTime lastActivity;
}
