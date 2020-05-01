package com.xyz.cloud.api.entity;

import javax.validation.constraints.NotNull;

import com.xyz.cloud.api.base.BaseVO;

import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductLimitVO extends BaseVO {
	private static final long serialVersionUID = 8669744267468127745L;

	@NotNull
	private Long limitId;

	// 产品ID
	@NotNull
	private Long productId;

	// 一次购买
	@NotNull
	private Long oneBuyLimit;

}
