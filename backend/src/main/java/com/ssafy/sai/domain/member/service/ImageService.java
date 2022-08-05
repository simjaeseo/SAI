package com.ssafy.sai.domain.member.service;

import com.ssafy.sai.domain.member.domain.Image;
import com.ssafy.sai.domain.member.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ImageService {
    private final ImageRepository imageRepository;
    private String dir = "C:/Users/multicampus/Documents/S07P12C206";
    private Path fileDir;
    private final String IMAGE = "image";

    @PostConstruct
    public void postConstruct() {
        fileDir = Paths.get(dir).toAbsolutePath().normalize();

        try {
            Files.createDirectories(fileDir);
        } catch (IOException e) {

        }
    }

    public Image uploadImage(MultipartFile file) {
        String uploadImageName = StringUtils.cleanPath(file.getOriginalFilename());
        System.out.println(file.getContentType());
        long size = file.getSize();
        String realName = UUID.randomUUID().toString() + "_" + uploadImageName;
        Path targetLocation = fileDir.resolve(realName);

        try {
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Image img = Image.builder()
                .fileName(realName)
                .size(size)
                .originalName(uploadImageName)
                .mimetype(file.getContentType())
                .url(targetLocation.toString())
                .build();

        imageRepository.save(img);

        return img;
    }
}
