package com.minidam.adam.controller;

import com.minidam.adam.bean.AssetUploadRequest;
import com.minidam.adam.bean.AssetUploadResponse;
import com.minidam.adam.service.AssetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adam")
public class AssetUploadController {

    private AssetService assetService;

    public AssetUploadController(AssetService assetService) {
        this.assetService = assetService;
    }

    @PostMapping("/assets")
    public ResponseEntity<AssetUploadResponse> assetUpload(@RequestBody AssetUploadRequest assetUploadRequest) {
        AssetUploadResponse assetUploadResponse = assetService.getPresignedUrlFromS3(assetUploadRequest.getFileName());

        return ResponseEntity.ok(assetUploadResponse);
    }
}
