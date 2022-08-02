package com.ssafy.sai.domain.member.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ssafy.sai.domain.member.dto.request.MemberUpdateRequest;
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
    @ManyToOne(fetch = LAZY, cascade = ALL)
    @JoinColumn(name = "campus_id")
    private Campus campus;

    @ElementCollection(fetch = EAGER)
    @Builder.Default
    private List<String> roles = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "member")
    private List<InterestedJob> interestedJobs = new ArrayList<>();

    @JsonManagedReference
    @OneToMany(mappedBy = "member")
    private List<InterestedEnterprise> interestedEnterprises = new ArrayList<>();

    public void updateCampus(Campus campus) {
        this.campus = campus;
    }

    public void updateMember(MemberUpdateRequest request) {
        this.profilePicture = request.getProfilePicture();
        this.phone = request.getPhone();
    }
}
