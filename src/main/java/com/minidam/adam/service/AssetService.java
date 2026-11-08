package com.minidam.adam.service;

import com.minidam.adam.bean.AssetUploadResponse;
import com.minidam.adam.bean.repository.AssetRepository;
import com.minidam.adam.enums.AssetStatusEnum;
import com.minidam.adam.model.Asset;
import com.minidam.adam.model.Original;
import com.minidam.adam.service.s3.S3Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service 
public class AssetService {
    String baseS3Path = "originals";

    private AssetRepository assetRepository;
    private S3Service s3Service;

    public AssetService(AssetRepository assetRepository, S3Service s3Service) {
        this.assetRepository = assetRepository;
        this.s3Service = s3Service;
    }

    public AssetUploadResponse getPresignedUrlFromS3(String fileName){

        // Generate asset ID
        String assetId = UUID.randomUUID().toString();
        fileName = "zoro.jpg";

        // S3 key path building
        String s3Key = String.join("/", baseS3Path, assetId, fileName);
        log.info("S3Key generated: {}", s3Key);
        Original originalS3KeyPath = new Original();
        originalS3KeyPath.setS3Key(s3Key);

        // Build DB record
        Asset asset = Asset.builder()
                .id(assetId)
                .filename(fileName)
                .s3Key(originalS3KeyPath)
                .status(AssetStatusEnum.PENDING)
                .build();

        assetRepository.save(asset);
        String presignedUrl = s3Service.generatePresignedUrl("", s3Key);

        AssetUploadResponse assetUploadResponse = AssetUploadResponse.builder()
                .assetId(assetId)
                .presignedUrl(presignedUrl)
                .build();
        return assetUploadResponse;
    }
}
