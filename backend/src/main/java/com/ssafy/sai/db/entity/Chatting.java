package com.ssafy.sai.db.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Chatting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    @JoinColumn(name = "member_id")
    private Member member;
    @Column(name = "message")
    private String message;
    @Column(name = "send_at")
    private Timestamp sendAt;
    @Column(name = "is_request")
    private Boolean isRequest;
}
