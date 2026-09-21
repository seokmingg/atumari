package org.example.atumari.common.storage;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import jakarta.annotation.PreDestroy;

/**
 * Spring 환경변수로 공통 S3 클라이언트를 초기화합니다.
 */
@Component
public class S3StorageInitializer {

    public S3StorageInitializer(Environment environment) {
        S3Storage.configure(
                environment.getRequiredProperty("atumari.storage.s3.region"),
                environment.getRequiredProperty("atumari.storage.s3.bucket")
        );
    }

    @PreDestroy
    public void close() {
        S3Storage.close();
    }
}
