package com.ssafy.sai.domain.member.domain;

import com.ssafy.sai.global.common.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.nio.file.Path;
import java.sql.Blob;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class Image extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "image_id")
    private Long id;
    private Long size;
    private String mimetype;
    private String originalName;
    private String fileName;
    private String url;
}
