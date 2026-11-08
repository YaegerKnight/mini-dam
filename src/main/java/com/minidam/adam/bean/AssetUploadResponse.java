package com.minidam.adam.bean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssetUploadResponse {

    String assetId;

    String presignedUrl;
}
