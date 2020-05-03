package com.xyz.cloud.core.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.xyz.cloud.api.entity.ProductVO;

@Mapper
@Repository
public interface ProductRepository {

	int save(List<ProductVO> list);

	int delete(Map<String, ?> map);

	int update(List<ProductVO> list);

	ProductVO queryById(Long id);

	List<ProductVO> query(Map<String, ?> map);

	Long size(Map<String, ?> map);

}
