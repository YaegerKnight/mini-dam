package com.minidam.adam.bean;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data 
@Builder 
@Getter 
@Setter 
public class AssetUploadRequest {
    
    private String name;
}
