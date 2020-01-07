package com.neusoft.customer.service;

import com.neusoft.customer.dao.CustomerDao;
import com.neusoft.entity.customer.*;
import com.neusoft.entity.customer.PageBean;
import com.neusoft.entity.customer.vo.CustomerSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CustomerService {
    @Autowired
    public CustomerDao customerDao;

    /**
     * 查询所有客户
     * @param currentPage
     * @param rows
     * @return
     */

    public List<Map<String,Object>> findCustomerByPage(int currentPage, int rows){

        List<Map<String,Object>> listReturn = new ArrayList();
        Map<String,Object> map = new HashMap<>();

        PageBean<CustomerSelect> pageBean = new PageBean<>();

        pageBean.setCurrentPage(currentPage);
        pageBean.setRows(rows);

        int totalCount =customerDao.findTotalCount();
        pageBean.setTotalCount(totalCount);

        int start = (currentPage - 1) * rows;
        List<CustomerSelect> list = customerDao.findByPage(start,rows);
        pageBean.setList(list);

        int totalPage = (totalCount % rows) == 0 ? (totalCount/rows) : (totalCount/rows + 1);
        pageBean.setTotalPage(totalPage);

        if(list.size()>0){
            map.put("code",0);
            map.put("data",pageBean);
        }
        else {
            map.put("code", 1);
        }
        listReturn.add(map);
        return listReturn;
    }

    /**
     * 根据Id查找客户
     * @return
     */
    public List<Map<String,Object>> selectCustomerById(int id){
        List<CustomerSelect>  list = customerDao.selectCustomerById(id);
        List<Map<String,Object>> listReturn = new ArrayList();
      Map<String,Object> map = new HashMap<>();
        if(list.size()>0){
            map.put("code",0);
            map.put("data",list);
        }
        else{
            map.put("code",1);
        }
        listReturn.add(map);
        return listReturn;
    }

    /**
     * 添加客户
     * @param customer
     * @return
     */
    public List<Map<String,Object>> addCustomer(Customer customer) {
        int code = customerDao.addCustomer(customer);
        int customerId = customerDao.selectCustomerID();//获得刚刚插入数据的id
        List<CustomerSelect>  list = customerDao.selectCustomerById(customerId);//根据Id获得客户数据
        List<Map<String,Object>> listReturn = new ArrayList();
        Map<String, Object> map = new HashMap<>();
        if(code == 0 ) {
            map.put("code",0);
            map.put("data",list);
        }
        else {
            map.put("code", 1);
            map.put("msg", "客户名称太受欢迎了，换一个吧");
        }
        listReturn.add(map);
            return listReturn;
    }

    /**
     * 更新客户信息
     * @param customer
     * @return
     */
    public  List<Map<String,Object>>  updateCustomer(Customer customer){
        int code = customerDao.updateCustomer(customer);
        List<CustomerSelect>  list = customerDao.selectCustomerById(customer.getCustomerId());
        List<Map<String,Object>> listReturn = new ArrayList();
        Map<String,Object> map = new HashMap<>();
        if(code == 0 ) {
            map.put("code",0);
            map.put("data",list);
        }
        else
            map.put("code",1);
        listReturn.add(map);
        return listReturn;
    }

    /**
     * 客户账户启用/禁用
     * @param customer
     * @return
     */
    public List<Map<String,Object>> updateCustomerIsUse(Customer customer){
        int code = customerDao.updateCustomerIsUse(customer);
        List<Map<String,Object>> listReturn = new ArrayList();
        Map<String,Object> map = new HashMap<>();
        if(code == 0){
            List<CustomerSelect>  list = customerDao.selectCustomerById(customer.getCustomerId());
            map.put("code",0);
            map.put("data",list);
        }
        else
            map.put("code",1);
        listReturn.add(map);
        return listReturn;
    }

    public Map userLogin(Customer customer) {
        int code = customerDao.userLogin(customer);
        Map<String, Object> map = new HashMap<>();
        if(code == 0){
            map.put("code",0);
            map.put("msg", "登陆成功");
        }
        else {
            map.put("code", 1);
            map.put("msg", "登陆失败");
        }
        return  map;
    }



}