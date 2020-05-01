package com.xyz.cloud.api.service;

import java.util.List;
import java.util.Map;

import com.xyz.cloud.api.entity.ProductVO;

public interface IProductService {

	ProductVO save(ProductVO arg);

	Boolean delete(Map<String, ?> map);

	ProductVO update(ProductVO arg);

	ProductVO query(Long id);

	List<ProductVO> query(Map<String, ?> map);

	Long size(Map<String, ?> map);

}
