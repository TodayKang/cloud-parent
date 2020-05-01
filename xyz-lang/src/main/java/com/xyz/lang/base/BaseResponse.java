package com.xyz.lang.base;

import java.io.Serializable;

import com.xyz.lang.constant.Constant;

import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse<T> implements Serializable {
	private static final long serialVersionUID = -13096982410780581L;

	/**
	 * 返回状态，默认false表示失败
	 */
	private Boolean status = Boolean.FALSE;

	/**
	 * 返回码，默认-1表示失败
	 */
	private String code = Constant.ERROR_CODE;

	/**
	 * 返回码信息说明
	 */
	private String message = Constant.ERROR_MESSAGE;

	/**
	 * 返回内容
	 */
	private T data;

}
