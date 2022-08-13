package com.ssafy.sai.domain.member.dto.response;

import com.ssafy.sai.domain.member.domain.Member;
import com.ssafy.sai.domain.member.domain.ProfilePicture;
import lombok.Data;

@Data
public class ProfileDto {

    private Long id;

    private String contentType;

    private String fileName;

    private Long size;

    public ProfileDto(ProfilePicture profilePicture) {
        this.id = profilePicture.getId();
        this.contentType = profilePicture.getContentType();
        this.fileName = profilePicture.getFileName();
        this.size = profilePicture.getSize();
    }
}
