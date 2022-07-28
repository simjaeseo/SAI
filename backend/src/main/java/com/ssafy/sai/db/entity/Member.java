package com.ssafy.sai.db.entity;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
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

//    @NotBlank(message = "이름은 필수 입력값입니다.")
    private String name;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
//    @NotNull
    private LocalDate birthday;
//    @NotNull
    private String phone;

    @Column(name = "member_is_consultant")
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    @Column(name = "profile_picture_url")
    private String profilePicture;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "campus_id")
    private Campus campus;

    @OneToOne(mappedBy = "member", fetch = LAZY)
    private Chatting chatting;

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

    // 비밀번호 일치 체크
    public boolean matchPassword(PasswordEncoder passwordEncoder, String checkPassword){
        return passwordEncoder.matches(checkPassword, getPassword());
    }

    public void updateMember(PasswordEncoder passwordEncoder, String password, String name, String phone, Campus campus){
        this.password = passwordEncoder.encode(password);
        this.name = name;
        this.phone = phone;
        this.campus = campus;
    }

    //== 패스워드 암호화 ==//
    public void encodePassword(PasswordEncoder passwordEncoder){
        this.password = passwordEncoder.encode(password);
    }
}
