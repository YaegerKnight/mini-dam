package com.minidam.adam.bean.repository;

import com.minidam.adam.model.Asset;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository 
public interface AssetRepository extends MongoRepository<Asset,String> {

    Optional<Asset> findBySha256(String sha256);
}
