package com.xyz.cloud.api.service;

import java.util.List;
import java.util.Map;

import com.xyz.cloud.api.entity.OrderUserVO;

public interface IOrderUserService {

	OrderUserVO save(OrderUserVO arg);

	List<OrderUserVO> saveBatch(List<OrderUserVO> list);

	Boolean delete(Map<String, ?> map);

	OrderUserVO update(OrderUserVO arg);

	List<OrderUserVO> updateBatch(List<OrderUserVO> list);

	OrderUserVO query(Long id);

	List<OrderUserVO> query(Map<String, ?> map);

	Long size(Map<String, ?> map);

}
