package com.example.eduprojjpa.common.utils;

import java.util.Objects;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Slf4j
@Aspect
@Component
public class S3DeleteAspect {

    private final S3ObjectDeleter s3ObjectDeleter;

    public S3DeleteAspect(S3ObjectDeleter s3ObjectDeleter) {
        this.s3ObjectDeleter = s3ObjectDeleter;
    }

    private static final String S3_DELETE_OBJECT_HEADER = "X-Previous-Image-URL";

    @Pointcut("@annotation(com.example.eduprojjpa.common.annotation.S3DeleteObject)")
    public void s3DeleteObjectPointCut() {
    }

    @After("s3DeleteObjectPointCut()")
    public void afterS3DeleteObject() {
        HttpServletRequest request =
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String targetObjectUrl = request.getHeader(S3_DELETE_OBJECT_HEADER);

        if (Objects.nonNull(targetObjectUrl) && !targetObjectUrl.isEmpty()) {
            s3ObjectDeleter.deleteObjectByObjectUrl(targetObjectUrl);
            log.debug("Delete S3 Object {}", targetObjectUrl);
        }
    }

}
