package com.cloud.core.service;

import com.cloud.api.entity.CustomerAddressVO;
import com.cloud.api.service.ICustomerAddressService;
import com.cloud.core.repository.CustomerAddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.*;

@Slf4j
@Service
public class CustomerAddressService implements ICustomerAddressService {

	@Autowired
	private CustomerAddressRepository customerAddressRepository;

	@Override
	public CustomerAddressVO save(CustomerAddressVO arg) {
		Assert.notNull(arg, "参数不能为空！");

		List<CustomerAddressVO> list = new ArrayList<>(Arrays.asList(arg));
		int size = customerAddressRepository.save(list);
		log.info("CustomerAddressService.save执行{}条", size);

		if (CollectionUtils.isEmpty(list) || list.get(0).getAddressId() == null) {
			throw new IllegalArgumentException();
		}

		return this.query(list.get(0).getAddressId());
	}

	@Override
	public Boolean delete(Map<String, ?> map) {
		Assert.notEmpty(map, "参数不能为空！");
		customerAddressRepository.delete(map);
		return Boolean.TRUE;
	}

	@Override
	public CustomerAddressVO update(CustomerAddressVO arg) {
		Assert.notNull(arg, "参数不能为空！");
		List<CustomerAddressVO> list = new ArrayList<>(Arrays.asList(arg));
		int size = customerAddressRepository.update(list);
		log.info("CustomerAddressService.save执行{}条", size);
		return this.query(arg.getAddressId());
	}

	@Override
	public CustomerAddressVO query(Long addressId) {
		Assert.notNull(addressId, "参数不能为空！");
		customerAddressRepository.queryById(addressId);
		return null;
	}

	@Override
	public List<CustomerAddressVO> query(Map<String, ?> map) {
		map = MapUtils.isEmpty(map) ? new HashMap<>() : map;
		List<CustomerAddressVO> list = customerAddressRepository.query(map);
		return list;
	}

	@Override
	public Long size(Map<String, ?> map) {
		map = MapUtils.isEmpty(map) ? new HashMap<>() : map;
		Long size = customerAddressRepository.size(map);
		return size;
	}

}
