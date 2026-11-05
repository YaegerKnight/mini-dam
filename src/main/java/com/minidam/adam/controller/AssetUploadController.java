package com.minidam.adam.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.minidam.adam.bean.AssetUploadRequest;

@RequestMapping("/s3")
public class AssetUploadController {
    

    @PostMapping("/assets")
    public String assetUpload(@RequestBody AssetUploadRequest assetUploadRequest) {
        return new String();
    }
}
