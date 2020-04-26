package com.cloud.core.controller;

import java.util.List;
import java.util.Map;

import com.cloud.api.base.BaseResponse;
import com.cloud.api.entity.CustomerAddressVO;
import com.cloud.api.service.ICustomerAddressService;
import com.cloud.lang.util.ResponseUtil;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"用户收货地址相关接口"})
@RestController
@RequestMapping(value = "/address", produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressController {

    @Autowired
    private ICustomerAddressService customerAddressService;

    @ApiOperation(value = "保存")
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public BaseResponse<CustomerAddressVO> save(@ApiParam(required = true) @RequestBody CustomerAddressVO arg) {
        arg = customerAddressService.save(arg);
        return ResponseUtil.success(arg);
    }

    @ApiOperation(value = "删除")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public BaseResponse<Boolean> delete(@ApiParam(required = true) @RequestBody Map<String, Object> map) {
        Boolean status = customerAddressService.delete(map);
        return ResponseUtil.resp(status);
    }

    @ApiOperation(value = "修改")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public BaseResponse<CustomerAddressVO> update(@ApiParam(required = true) @RequestBody CustomerAddressVO arg) {
        arg = customerAddressService.update(arg);
        return ResponseUtil.success(arg);
    }

    @ApiOperation(value = "根据主键查询")
    @ApiResponses({@ApiResponse(code = 400, message = "请求参数非法")})
    @ApiImplicitParams({@ApiImplicitParam(paramType = "path", name = "addressId", dataType = "Integer", required = true, value = "地址ID")})
    @RequestMapping(value = "/query/{addressId}", method = RequestMethod.GET)
    public BaseResponse<CustomerAddressVO> query(@PathVariable("addressId") Long addressId) {
        CustomerAddressVO addressVO = customerAddressService.query(addressId);
        return ResponseUtil.success(addressVO);
    }

    @ApiOperation(value = "查询列表")
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public BaseResponse<List<CustomerAddressVO>> query(@ApiParam(value = "查询条件") @RequestBody Map<String, Object> map) {
        List<CustomerAddressVO> list = customerAddressService.query(map);
        return ResponseUtil.success(list);
    }

    @ApiOperation(value = "查询总条数")
    @RequestMapping(value = "/size", method = RequestMethod.POST)
    public BaseResponse<Long> size(@ApiParam(value = "查询条件") @RequestBody Map<String, Object> map) {
        Long size = customerAddressService.size(map);
        return ResponseUtil.success(size);
    }

}
