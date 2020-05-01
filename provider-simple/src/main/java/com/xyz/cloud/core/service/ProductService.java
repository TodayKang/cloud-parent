package com.xyz.cloud.core.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.xyz.cloud.api.entity.ProductVO;
import com.xyz.cloud.api.service.IProductService;
import com.xyz.cloud.core.repository.ProductRepository;
import com.xyz.cloud.redis.RedisService;
import com.xyz.lang.constant.ErrorCode;

@Service
public class ProductService implements IProductService {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private RedisService redisService;

	@Override
	public ProductVO save(ProductVO arg) {
		Assert.notNull(arg, ErrorCode.ERROR_PARAM);

		List<ProductVO> list = new ArrayList<ProductVO>(Arrays.asList(arg));
		productRepository.save(list);

		redisService.deleteLike("ProductService*");
		return this.query(list.get(0).getProductId());
	}

	@Override
	public Boolean delete(Map<String, ?> map) {
		Assert.notEmpty(map, ErrorCode.ERROR_PARAM);

		productRepository.delete(map);
		redisService.deleteLike("ProductService*");
		return Boolean.TRUE;
	}

	@Override
	public ProductVO update(ProductVO arg) {
		Assert.notNull(arg, ErrorCode.ERROR_PARAM);

		List<ProductVO> list = new ArrayList<ProductVO>(Arrays.asList(arg));
		productRepository.update(list);

		redisService.deleteLike("ProductService*");
		return this.query(list.get(0).getProductId());
	}

	@Override
	public ProductVO query(Long id) {
		Assert.notNull(id, ErrorCode.ERROR_PARAM);

		String key = "ProductService_queryById_" + id;
		ProductVO product = (ProductVO) redisService.get(key);
		if (product == null) {
			product = productRepository.queryById(id);
			redisService.set(key, product);
		}

		return product;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<ProductVO> query(Map<String, ?> map) {
		map = MapUtils.isEmpty(map) ? new HashMap<>() : map;

		String key = "ProductService_queryList_" + map.hashCode();
		List<ProductVO> list = (List<ProductVO>) redisService.get(key);
		if (CollectionUtils.isEmpty(list)) {
			list = productRepository.query(map);
			redisService.set(key, list);
		}

		return list;
	}

	@Override
	public Long size(Map<String, ?> map) {
		map = MapUtils.isEmpty(map) ? new HashMap<>() : map;

		String key = "ProductService_size_" + map.hashCode();
		Long size = (Long) redisService.get(key);
		if (size == null || size.longValue() < 1L) {
			size = productRepository.size(map);
			redisService.set(key, size);
		}

		return size;
	}

}
