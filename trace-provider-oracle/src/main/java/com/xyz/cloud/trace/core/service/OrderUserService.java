package com.xyz.cloud.trace.core.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.xyz.cloud.api.entity.OrderUserVO;
import com.xyz.cloud.api.service.IOrderUserService;
import com.xyz.cloud.trace.core.repository.IOrderUserRepository;
import com.xyz.cloud.trace.redis.RedisService;
import com.xyz.lang.constant.ErrorCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderUserService implements IOrderUserService {

	@Autowired
	private RedisService redisService;

	@Autowired
	private IOrderUserRepository orderUserRepository;

	@Override
	public OrderUserVO save(OrderUserVO arg) {
		Assert.notNull(arg, ErrorCode.ERROR_PARAM);

		orderUserRepository.save(arg);
		redisService.deleteLike(this.getClass().getSimpleName() + "*");

		return orderUserRepository.queryById(arg.getOrderUserId());
	}

	@Override
	public List<OrderUserVO> saveBatch(List<OrderUserVO> list) {
		Assert.notEmpty(list, ErrorCode.ERROR_PARAM);

		orderUserRepository.saveBatch(list);
		redisService.deleteLike(this.getClass().getSimpleName() + "*");

		return list;
	}

	@Override
	public Boolean delete(Map<String, ?> map) {
		map = MapUtils.isEmpty(map) ? new HashMap<>() : map;

		orderUserRepository.delete(map);
		redisService.deleteLike(this.getClass().getSimpleName() + "*");

		return Boolean.TRUE;
	}

	@Override
	public OrderUserVO update(OrderUserVO arg) {
		Assert.notNull(arg, ErrorCode.ERROR_PARAM);

		orderUserRepository.update(arg);
		redisService.deleteLike(this.getClass().getSimpleName() + "*");

		return orderUserRepository.queryById(arg.getOrderUserId());
	}

	@Override
	public List<OrderUserVO> updateBatch(List<OrderUserVO> list) {
		Assert.notEmpty(list, ErrorCode.ERROR_PARAM);

		orderUserRepository.updateBatch(list);
		redisService.deleteLike(this.getClass().getSimpleName() + "*");

		return list;
	}

	@Override
	public OrderUserVO query(Long id) {
		Assert.notNull(id, ErrorCode.ERROR_PARAM);

		StringBuilder key = new StringBuilder();
		key.append(this.getClass().getSimpleName()).append("_id_").append(id);

		OrderUserVO orderUser = (OrderUserVO) redisService.get(key.toString());
		if (orderUser == null) {
			orderUser = orderUserRepository.queryById(id);
		}

		return orderUser;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<OrderUserVO> query(Map<String, ?> map) {
		map = MapUtils.isEmpty(map) ? new HashMap<>() : map;

		StringBuilder key = new StringBuilder();
		key.append(this.getClass().getSimpleName()).append("_query_").append(map.hashCode());

		List<OrderUserVO> list = (List<OrderUserVO>) redisService.get(key.toString());
		if (CollectionUtils.isEmpty(list)) {
			list = orderUserRepository.query(map);
			redisService.set(key.toString(), list);
		}

		return list;
	}

	@Override
	public Long size(Map<String, ?> map) {
		map = MapUtils.isEmpty(map) ? new HashMap<>() : map;

		log.info("UserOrderService.size:req={}", map);
		StringBuilder key = new StringBuilder();
		key.append(this.getClass().getSimpleName()).append("_size_").append(map.hashCode());

		Long size = (Long) redisService.get(key.toString());
		if (size == null) {
			size = orderUserRepository.size(map);
			redisService.set(key.toString(), size);
		}

		return size;
	}

}
