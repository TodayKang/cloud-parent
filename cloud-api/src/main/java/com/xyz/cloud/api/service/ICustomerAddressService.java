package com.xyz.cloud.api.service;

import java.util.List;
import java.util.Map;

import com.xyz.cloud.api.entity.CustomerAddressVO;

public interface ICustomerAddressService {

	CustomerAddressVO save(CustomerAddressVO arg);

	Boolean delete(Map<String, ?> map);

	CustomerAddressVO update(CustomerAddressVO arg);

	CustomerAddressVO query(Long addressId);

	List<CustomerAddressVO> query(Map<String, ?> map);

	Long size(Map<String, ?> map);

}
