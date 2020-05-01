package com.xyz.cloud.api.entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xyz.cloud.api.base.BaseVO;

import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class ProductVO extends BaseVO {
	private static final long serialVersionUID = -1331748890497708702L;

	// 产品ID
	@NotNull
	private Long productId;

	// 品类ID
	@NotNull
	private Long categoryId;

	// 品类名称
	private String categoryName;

	// 成本价
	@NotNull
	private BigDecimal costPrice;

	// 售价
	@NotNull
	private BigDecimal sellPrice;

	// 市场价
	private BigDecimal marketPrice;

	// 售卖单位
	@NotNull
	private String saleUnit;

	// 产品名称
	@NotBlank
	private String productName;

	// 产品详述
	private String productDetail;

	// 产品标签，以,隔开
	private String tags;

	// 产品标签数组
	private String[] tagArray;

	// 产品封面图地址
	private String imagePath;

	// 是否可售
	@NotNull
	private String saleFlag;

	// 是否删除
	@NotNull
	private String deleteFlag;

	// 库存，-1为不限制库存
	@NotNull
	private Long stock;

	// 余量，-1位不限余量
	@NotNull
	private Long rest;

	// 产品评分
	@NotNull
	private Float productScore;

	// 产品销量
	@NotNull
	private Long sellQuantity;

	// 产品有效期开始时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull
	private Date onlineStart;

	// 产品有效期结束时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull
	private Date onlineEnd;

	public String[] getTagArray() {
		if (StringUtils.isNotBlank(this.tags)) {
			this.tagArray = StringUtils.split(this.tags, ",");
		}

		return tagArray;
	}

}
