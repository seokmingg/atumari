package org.example.atumari.common.storage;

import java.io.InputStream;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

/**
 * S3에 첨부파일을 저장하는 공통 유틸리티입니다.
 *
 * <p>AWS SDK 기본 자격 증명 체인을 사용하므로 로컬과 EC2에서 같은 코드를
 * 사용할 수 있습니다. 현재 배포에서는 두 환경 모두 환경변수로 자격 증명을
 * 전달합니다.</p>
 */
public final class S3Storage {

    private static S3Client client;
    private static String bucket;

    private S3Storage() {
    }

    public static synchronized void configure(
            String region,
            String configuredBucket,
            String accessKey,
            String secretKey
    ) {
        if (configuredBucket == null || configuredBucket.isBlank()) {
            throw new IllegalStateException("S3_BUCKET 설정이 없습니다.");
        }

        close();
        S3ClientBuilder builder = S3Client.builder()
                .region(Region.of(region));

        boolean hasAccessKey = accessKey != null && !accessKey.isBlank();
        boolean hasSecretKey = secretKey != null && !secretKey.isBlank();
        if (hasAccessKey != hasSecretKey) {
            throw new IllegalStateException(
                    "AWS_ACCESS_KEY_ID와 AWS_SECRET_ACCESS_KEY를 모두 설정해야 합니다."
            );
        }
        if (hasAccessKey) {
            builder.credentialsProvider(StaticCredentialsProvider.create(
                    AwsBasicCredentials.create(accessKey, secretKey)
            ));
        }

        client = builder.build();
        bucket = configuredBucket;
    }

    public static void upload(
            String objectKey,
            InputStream inputStream,
            long contentLength,
            String contentType
    ) {
        PutObjectRequest.Builder request = PutObjectRequest.builder()
                .bucket(getBucket())
                .key(objectKey);

        if (contentType != null && !contentType.isBlank()) {
            request.contentType(contentType);
        }

        getClient().putObject(
                request.build(),
                RequestBody.fromInputStream(inputStream, contentLength)
        );
    }

    public static void delete(String objectKey) {
        getClient().deleteObject(DeleteObjectRequest.builder()
                .bucket(getBucket())
                .key(objectKey)
                .build());
    }

    public static ResponseBytes<GetObjectResponse> download(String objectKey) {
        return getClient().getObjectAsBytes(GetObjectRequest.builder()
                .bucket(getBucket())
                .key(objectKey)
                .build());
    }

    public static synchronized void close() {
        if (client != null) {
            client.close();
            client = null;
        }
    }

    private static S3Client getClient() {
        if (client == null) {
            throw new IllegalStateException("S3 클라이언트가 초기화되지 않았습니다.");
        }
        return client;
    }

    private static String getBucket() {
        if (bucket == null || bucket.isBlank()) {
            throw new IllegalStateException("S3 버킷이 초기화되지 않았습니다.");
        }
        return bucket;
    }
}
