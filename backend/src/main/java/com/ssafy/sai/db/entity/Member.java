package com.ssafy.sai.db.entity;

import com.ssafy.sai.api.request.MemberUpdateRequest;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

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

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "favorite_job_id")
    private FavoriteJob favoriteJob;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "favorite_enterprise_id")
    private FavoriteEnterprise favoriteEnterprise;

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
