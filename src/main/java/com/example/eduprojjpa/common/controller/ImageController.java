package com.example.eduprojjpa.common.controller;

import com.example.eduprojjpa.common.utils.S3PreSignedUrlGenerator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ImageController {

    private final S3PreSignedUrlGenerator s3PreSignedUrlGenerator;

    //PresignedUrl을 만드는 메서드입니다.
    @GetMapping("/presigned-url")
    public ResponseEntity<String> S3GeneratePreSignedURL(
            @RequestParam String fileName
    ) {
        try {
            String preSignedUrl = s3PreSignedUrlGenerator.getPreSignedUrl(fileName);
            return ResponseEntity.ok(preSignedUrl);
        } catch (Exception e) {
            log.error("Error generating Pre-Signed URL", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error generating Pre-Signed URL");
        }
    }
}
