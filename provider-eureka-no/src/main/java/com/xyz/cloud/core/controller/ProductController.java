package com.xyz.cloud.core.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xyz.cloud.api.entity.ProductVO;
import com.xyz.cloud.api.service.IProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(tags = { "provider产品信息相关接口" })
@RequestMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {

	@Autowired
	private IProductService productService;

	@ApiOperation(value = "保存产品信息")
	@PostMapping(value = "/save")
	public ProductVO save(@ApiParam(required = true) @RequestBody ProductVO arg) {
		return productService.save(arg);
	}

	@ApiOperation(value = "删除产品信息")
	@PostMapping(value = "/delete")
	public Boolean delete(@ApiParam(required = true) @RequestBody Map<String, Object> map) {
		return productService.delete(map);
	}

	@ApiOperation(value = "修改产品信息")
	@PostMapping(value = "/update")
	public ProductVO update(@ApiParam(required = true) @RequestBody ProductVO arg) {
		return productService.update(arg);
	}

	@ApiOperation(value = "根据产品ID查询")
	@GetMapping(value = "/queryById/{id}")
	public ProductVO query(@PathVariable("id") Long id) {
		return productService.query(id);
	}

	@ApiOperation(value = "查询产品信息列表")
	@PostMapping(value = "/query")
	public List<ProductVO> query(@ApiParam(value = "查询对象") @RequestBody Map<String, Object> map) {

		// for search
		if (MapUtils.getString(map, "keyWord") != null) {
			String keyWord = MapUtils.getString(map, "keyWord");
			map.put("keyWord", StringUtils.trimToNull(keyWord));
		}

		return productService.query(map);
	}

	@ApiOperation(value = "查询列表总条数")
	@PostMapping(value = "/size")
	public Long size(@ApiParam(value = "查询对象") @RequestBody Map<String, Object> map) {
		return productService.size(map);
	}

}
