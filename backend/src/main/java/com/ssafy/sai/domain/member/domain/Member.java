package com.ssafy.sai.domain.member.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ssafy.sai.domain.job.domain.Enterprise;
import com.ssafy.sai.domain.job.domain.Job;
import com.ssafy.sai.domain.member.dto.CampusDto;
import com.ssafy.sai.domain.member.dto.MemberDto;
import com.ssafy.sai.domain.member.dto.MemberUpdateRequest;
import com.ssafy.sai.global.common.BaseEntity;
import com.ssafy.sai.domain.job.domain.InterestedEnterprise;
import com.ssafy.sai.domain.job.domain.InterestedJob;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String name;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private LocalDate birthday;
    private String phone;

    @Column(name = "member_is_consultant")
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    @Column(name = "profile_picture_url")
    private String profilePicture;

    private int year;
    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "campus_id")
    private Campus campus;

    @JsonManagedReference
    @OneToMany(mappedBy = "member")
    private List<InterestedJob> interestedJobs = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "member")
    private List<InterestedEnterprise> interestedEnterprises = new ArrayList<>();

    public MemberDto toMemberDto() {
        return MemberDto.builder()
                .email(email)
                .name(name)
                .phone(phone)
                .year(year)
                .campus(new CampusDto(campus.getCity(), campus.getClassNumber()))
                .memberStatus(memberStatus)
                .birthday(birthday)
                .build();
    }

    public void updateCampus(Campus campus) {
        this.campus = campus;
    }

    public void updateMember(MemberUpdateRequest request) {
        this.phone = request.getPhone();
        this.profilePicture = request.getProfilePicture();
    }
}
