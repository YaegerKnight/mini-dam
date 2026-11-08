package com.minidam.adam.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.minidam.adam.enums.AssetStatusEnum;

import lombok.Builder;

@Document(collection = "assets")
@Builder
public record Asset (@Id String id, String filename, @Indexed(unique=true, sparse=true) String sha256, AssetStatusEnum status, Original s3Key){
}
