package com.ssafy.sai.db.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

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
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    @NotNull
    private Long id;

    @Column
    @NotEmpty(message = "이메일은 필수 입력값입니다.")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식에 맞지 않습니다.")
    private String email;

    @NotEmpty(message = "비밀번호는 필수 입력값입니다.")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\\d~!@#$%^&*()+|=]{8,16}$\n",
            message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

    @NotBlank(message = "이름은 필수 입력값입니다.")
    private String name;

    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @NotNull
    private LocalDate birthday;
    @NotNull
    private String phone;

    @Column(name = "member_is_consultant")
    @Enumerated(EnumType.STRING)
    private MemberStatus memberStatus;

    @Column(name = "profile_picture_url")
    private String profilePicture;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "campus_id", nullable = false)
    private Campus campus;

    @OneToOne(mappedBy = "member", fetch = LAZY)
    private Chatting chatting;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "favorite_job_id")
    private FavoriteJob favoriteJob;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "favorite_enterprise_id")
    private FavoriteEnterprise favoriteEnterprise;
}
