package com.xyz.cloud.core.repository;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.xyz.cloud.api.entity.CustomerAddressVO;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CustomerAddressRepository {

	int save(List<CustomerAddressVO> list);

	int delete(Map<String, ?> map);

	int update(List<CustomerAddressVO> list);

	CustomerAddressVO queryById(Long id);

	List<CustomerAddressVO> query(Map<String, ?> map);

	Long size(Map<String, ?> map);

}