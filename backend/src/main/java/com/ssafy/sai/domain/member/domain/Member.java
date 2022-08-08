package com.ssafy.sai.domain.member.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ssafy.sai.domain.member.dto.request.ConsultantUpdateRequest;
import com.ssafy.sai.domain.member.dto.request.MemberUpdateRequest;
import com.ssafy.sai.domain.member.dto.response.CampusConsultantDto;
import com.ssafy.sai.global.common.BaseEntity;
import com.ssafy.sai.domain.job.domain.InterestedEnterprise;
import com.ssafy.sai.domain.job.domain.InterestedJob;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@ToString
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(unique = true)
    private String email;

    private String password;

    private String name;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private String phone;

    @Column(name = "member_is_consultant")
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    @OneToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "profile_picture_id")
    private ProfilePicture profilePicture;

    private int year;
    @ManyToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "campus_id")
    private Campus campus;

    @ElementCollection(fetch = EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "member", cascade = ALL)
    private List<InterestedJob> interestedJobs = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "member", cascade = ALL)
    private List<InterestedEnterprise> interestedEnterprises = new ArrayList<>();

    public void updateCampus(Campus campus) {
        this.campus = campus;
    }

    public void updateMember(MemberUpdateRequest request) {
        this.phone = request.getPhone();
    }

    public void updateMember(ConsultantUpdateRequest request) {
        this.phone = request.getPhone();
    }

    public Member(String password) {
        this.password = password;
    }

    public void updateProfilePicture(ProfilePicture profilePicture) {
        if (profilePicture != null) {
            this.profilePicture = profilePicture;
        } else {
            this.profilePicture = null;
        }
    }
}
