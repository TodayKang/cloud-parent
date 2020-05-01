package com.xyz.cloud.api.service;

import java.util.List;
import java.util.Map;

import com.xyz.cloud.api.entity.ProductLimitVO;

public interface IProductLimitService {

	ProductLimitVO save(ProductLimitVO arg);

	Boolean delete(Map<String, ?> map);

	ProductLimitVO update(ProductLimitVO arg);

	ProductLimitVO query(Long addressId);

	List<ProductLimitVO> query(Map<String, ?> map);

	Long size(Map<String, ?> map);

}
