package com.cloud.api.service;

import com.cloud.api.entity.CustomerAddressVO;

import java.util.List;
import java.util.Map;

public interface ICustomerAddressService {

    CustomerAddressVO save(CustomerAddressVO arg);

    Boolean delete(Map<String, ?> map);

    CustomerAddressVO update(CustomerAddressVO arg);

    CustomerAddressVO query(Long addressId);

    List<CustomerAddressVO> query(Map<String, ?> map);

    Long size(Map<String, ?> map);

}
