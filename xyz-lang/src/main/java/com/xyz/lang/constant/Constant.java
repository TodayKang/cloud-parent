package com.xyz.lang.constant;

import org.apache.commons.lang3.RandomUtils;

public class Constant {

	public static final String ERROR_CODE = "-1";
	public static final String ERROR_MESSAGE = "ERROR";

	public static final String SUCCESS_CODE = "0";
	public static final String SUCCESS_MESSAGE = "SUCCESS";

	// 缓存超时时间
	public static final Long cacheSec = 180L;
	public static final Long cacheNullSec = 60L;

	// 防止缓存雪崩，超时时间加随机数
	public static Long randomSec = RandomUtils.nextLong(cacheSec, 2 * cacheSec);

}
