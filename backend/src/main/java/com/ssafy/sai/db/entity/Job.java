package com.ssafy.sai.db.entity;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity
public class Job {

    @Id @GeneratedValue
    @Column(name = "job_id")
    private Long id;

    private String name;

//    @ManyToOne(fetch = LAZY)
//    @JoinColumn(name = "favorite_job_id")
//    private FavoriteJob favoriteJob;



}
