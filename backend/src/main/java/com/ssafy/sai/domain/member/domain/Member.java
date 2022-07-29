package com.ssafy.sai.domain.member.domain;

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

    @ManyToOne(fetch = LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "campus_id")
    private Campus campus;

    @OneToMany(mappedBy = "member")
    private List<InterestedJob> interestedJobs = new ArrayList<>();

    @OneToMany(mappedBy = "member")
    private List<InterestedEnterprise> interestedEnterprises = new ArrayList<>();

    public void addFavoriteEnterprise(InterestedEnterprise interestedEnterprise) {
        interestedEnterprises.add(interestedEnterprise);
        interestedEnterprise.addMember(this);
    }

    public void addFavoriteJob(InterestedJob interestedJob) {
        interestedJobs.add(interestedJob);
        interestedJob.addMember(this);
    }

    public void updateCampus(Campus campus) {
        this.campus = campus;
        campus.getMembers().add(this);
    }

    public void updateMember(MemberUpdateRequest request) {
        this.birthday = request.getBirthday();
        this.campus = request.getCampus();
        this.phone = request.getPhone();

        if (campus != null) {
            updateCampus(campus);
        }
    }
}
