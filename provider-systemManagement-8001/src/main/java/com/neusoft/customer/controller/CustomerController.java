//package com.neusoft.customer.controller;
//
//import com.neusoft.customer.service.CustomerService;
//import com.neusoft.entity.customer.*;
//import io.swagger.annotations.ApiOperation;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.List;
//import java.util.Map;
//
//@Controller
//public class CustomerController {
//@Autowired
//  public CustomerService customerService;
//
//
//
//    /**
//     * 查询所有客户
//     * @param pageBean
//     * @return
//     */
//    @ApiOperation(value = "上传图片")
//    @RequestMapping("/backend/customer/getCustomerList")
//    @ResponseBody
//    public List<Map<String,Object>> findCustomerByPage(@RequestBody PageBean pageBean){
//        List<Map<String,Object>> list = customerService.findCustomerByPage(pageBean.getCurrentPage(),pageBean.getRows());//测试{"currentPage":1,"rows":5}
//        return list;
//    }
//
//    /**
//     * 根据Id查找客户
//     * @param customer
//     * @return
//     */
//    @ApiOperation(value = "上传图片")
//    @RequestMapping("/backend/customer/getCustomerDetail")
//    @ResponseBody
//    public List<Map<String, Object>> selectCustomerById(@RequestBody Customer customer){ //测试{"customerId":30}
//        int id = customer.getCustomerId();
//        List<Map<String,Object>> list = customerService.selectCustomerById(id);
//        return list;
//
//    }
//
//    /**
//     * 添加客户
//     * @param customer
//     * @return
//     */
//    @ApiOperation(value = "上传图片")
//    @RequestMapping("/backend/customer/addCustomer")
//    @ResponseBody
//    public  List<Map<String,Object>> addCustomer(@RequestBody Customer customer){ //测试 {"customerNumber":1,"customerName":"5","customePassword":"666","customerIsUsed":0}
//        Date now = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//格式化时间
//        customer.setGmtCreate(dateFormat.format(now));//创建时间
//        customer.setGmtModified(dateFormat.format(now));//更改时间，添加操作默认为创建时间
//        List<Map<String,Object>> list  = customerService.addCustomer(customer);
//        return list;
//    }
//
//    /**
//     * 更新客户信息
//     * @param customer
//     * @return
//     */
//    @ApiOperation(value = "上传图片")
//    @RequestMapping("/backend/customer/updateAccount")
//    @ResponseBody
//    public List<Map<String,Object>> updateCustomer(@RequestBody Customer customer){ // {"customerId":30,"customerName":"5","version":2,"customerIsUsed":0}
//        List<Map<String,Object>> list = customerService.updateCustomer(customer);
//        return list;
//    }
//
//
//    /**
//     * 客户账户启用/禁用
//     * @param customer
//     * @return
//     */
//    @ApiOperation(value = "上传图片")
//    @RequestMapping("/backend/customer/updateAccountByStatus")
//    @ResponseBody
//    public  List<Map<String,Object>>  updateCustomerIsUse(@RequestBody Customer customer) {  // {"customerId":30,"customerIsUsed":1}
//        List<Map<String, Object>> list = customerService.updateCustomerIsUse(customer);
//        return list;
//    }
//
//
//}
