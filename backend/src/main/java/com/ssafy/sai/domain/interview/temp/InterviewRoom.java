package com.ssafy.sai.domain.interview.temp;

import com.ssafy.sai.global.common.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InterviewRoom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interview_room_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private InterviewRoomType interviewRoomType;

    @OneToMany(mappedBy = "interviewRoom")
    List<InterviewParticipant> interviewParticipants = new ArrayList<>();
}
