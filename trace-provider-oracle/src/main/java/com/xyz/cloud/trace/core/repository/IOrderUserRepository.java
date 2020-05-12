package com.xyz.cloud.trace.core.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.xyz.cloud.api.entity.OrderUserVO;

@Mapper
@Repository
public interface IOrderUserRepository {

	int save(OrderUserVO arg);

	int saveBatch(List<OrderUserVO> list);

	int delete(Map<String, ?> map);

	int update(OrderUserVO arg);

	int updateBatch(List<OrderUserVO> list);

	OrderUserVO queryById(Long id);

	List<OrderUserVO> query(Map<String, ?> map);

	Long size(Map<String, ?> map);

}
