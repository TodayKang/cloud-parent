package com.xyz.cloud.api.entity;

import com.xyz.cloud.api.base.BaseVO;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderUserVO extends BaseVO {
	private static final long serialVersionUID = -8163958961718268562L;

	// 订单用户主键
	private Long orderUserId;

	// 订单ID
	private Long orderId;

	// 下单用户ID
	private Long userId;

	// 订单类型
	private String orderType;

	// 订单系统
	private String bizType;

	// 订单是否删除
	private String deleteFlag;

	// 创建时间
	private Date createTime;

}
