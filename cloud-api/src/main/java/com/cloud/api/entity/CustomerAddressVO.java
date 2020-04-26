package com.cloud.api.entity;

import com.cloud.api.base.BaseVO;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class CustomerAddressVO extends BaseVO {
    private static final long serialVersionUID = -4142419115585955273L;

    private Long addressId;
    private Long customerId;
    private String nickName;
    private String receiver;
    private String gender;
    private String phone;
    private String email;
    private String postCode;
    private String country;
    private String province;
    private String city;
    private String district;
    private String detail;
    private String addressDetail;
    private String addressDefault;
    private String addressTag;

}
