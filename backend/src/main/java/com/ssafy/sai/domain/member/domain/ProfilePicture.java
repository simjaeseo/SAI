package com.ssafy.sai.domain.member.domain;

import com.ssafy.sai.global.common.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class ProfilePicture extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long size;
    private String contentType;
    private String fileName;
    private String originalName;

    @OneToOne(mappedBy = "profilePicture")
    private Member member;

    public void updateMember(Member member) {
        this.member = member;
    }
}
