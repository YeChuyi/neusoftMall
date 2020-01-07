package com.neusoft.customer.dao;

import com.neusoft.entity.customer.Customer;
import com.neusoft.entity.customer.vo.CustomerSelect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public class CustomerDao {
    @Autowired
    public JdbcTemplate jdbcTemplate;



    /**
     * 查询所有用户且分页:返回客户列表
     * @param start
     * @param rows
     * @return
     */
    public List<CustomerSelect> findByPage(int start, int rows){
        String sql = "select * from customer limit ?,? ";
        List<CustomerSelect> list = jdbcTemplate.query(sql,new BeanPropertyRowMapper(CustomerSelect.class),start,rows);
        return  list;
    }


    /**
     * 根据id查用户
     * @param id
     * @return
     */
    public List<CustomerSelect> selectCustomerById(int id){
        String sql = "select * from customer where customer_id = "+ id;
        List< CustomerSelect> list = jdbcTemplate.query(sql,new BeanPropertyRowMapper<CustomerSelect>(CustomerSelect.class));
        return  list;
    }


    /**
     *新增操作
     * @param customer
     * @return
     */
    public int addCustomer(Customer customer){
        String name = "admin";
        String sql = "insert into customer (customer_number,customer_password,customer_name,customer_is_used,created_by,gmt_create,last_modified_by,gmt_modified,is_deleted,sort_no,version )" +
                "  values(?,?,?,?,'"+ name + "',?,'" + name + "',?,0,1,1)";
        Object args[] =  {customer.getCustomerNumber(),customer.getCustomerPassword(),
                          customer.getCustomerName(),customer.getCustomerIsUsed(),customer.getGmtCreate(),
                          customer.getGmtModified()};
        int code = selectCustomerName(customer.getCustomerName());
        if (code == 1){//根据用户名查询，若没有相同的用户名则可插入这条数据
            int temp = jdbcTemplate.update(sql , args);
            if (temp >0)
                return 0;
            else
                return 1;
        }
        else
            return 1;
    }

    /**
     * 根据用户名查询用户是否存在
     * @param name
     * @return
     */
    public int selectCustomerName(String name){
        String sql = "select * from customer where customer_name = '"+ name + "'" ;
        List list =  jdbcTemplate.queryForList(sql);
        if(list.size()>0)//如果列表为空则没有相同的用户
            return 1;
        else
            return 0;
    }

    /**
     * 更新操作
     * @param customer
     * @return
     */
    public int updateCustomer(Customer customer){
        String sql = "update customer set customer_name = ? ,customer_is_used = ? ,version = ?,gmt_modified = ? where customer_id = ?";
        Object args[] =  { customer.getCustomerName(),customer.getCustomerIsUsed(),customer.getVersion(),customer.getGmtModified(),customer.getCustomerId()};
        int code = selectCustomerName(customer.getCustomerName());//查询修改的客户名是否已存在了
        if(code == 0){
            int temp = jdbcTemplate.update(sql,args);//更新的行数
            if(temp > 0) {
                return 0;
            }
            else {
                return 1;
            }
        }
        else
            return 1;
    }


    /**
     * 查询用户id
     * @return
     */
    public int selectCustomerID(){
        String sql = "select Max(customer_id) as customerId from customer";
        List list = jdbcTemplate.queryForList(sql);
         Map map = (Map)list.get(0);
         int id = (int)map.get("customerId");
        return id;
    }

    /**
     * 启用/禁用账号
     * @param customer
     * @return
     */
    public int updateCustomerIsUse(Customer customer){
        String sql = "update customer set customer_is_used = ? where customer_id = ?";
        Object args[] = {customer.getCustomerIsUsed(),customer.getCustomerId()};
        int temp = jdbcTemplate.update(sql,args);
        if(temp > 0)
                return 0;
        else
                return 1;
    }




    /**
     * 登陆
     * @param customer
     * @return
     */
    public int userLogin(Customer customer){
        String sql = "select customer_number,customer_password from customer where customer_number= ? and customer_password = ? ";
        Object args[] =  { customer.getCustomerNumber(),customer.getCustomerPassword()};
        Map<String, Object> map = jdbcTemplate.queryForMap(sql,args);
        if(map.size() > 0)
            return 0;
        else
            return 1;
    }

    /**
     * 注册
     * @param customer
     * @return
     */
    public int registered(Customer customer){
        String sql = "insert  into customer (customer_number,customer_password ,customer_name)value (?,?,?)";
        Object args[] =  { customer.getCustomerNumber(),customer.getCustomerPassword(),customer.getCustomerName()};
        int code = selectCustomerName(customer.getCustomerName());
        if(code == 0){
            int temp = jdbcTemplate.update(sql,args);
            if(temp > 0)
                return  0;
            else
                return 1;
        }
        else
            return 1;
    }

    /**
     * 修改密码
     * @param customer
     * @return
     */
    public int   updatePassWord(Customer customer){
        String sql = "update customer set customer_password = ? where customer_id = ?";
        String sql1 = "select customer_password from customer where customer_id = '" + customer.getCustomerId() + "'";
        Object args[] =  { customer.getCustomerNewPassword(),customer.getCustomerId()};
          Map map  = jdbcTemplate.queryForMap(sql1);
        if(map.size()>0){
            if (map.get("customer_password").equals(customer.getCustomerPassword())){
                int code = jdbcTemplate.update(sql, args);
                if (code > 0)
                    return 0;
                else
                    return 1;
            }
            else
                return 1;
        }
        else
            return 1;
    }

    /**
     * 查询所有用户且分页:返回总记录数
     * @return
     */
    public int findTotalCount(){
          String sql ="select count(*) from customer";
          int temp = jdbcTemplate.queryForObject(sql,Integer.class);
          return temp;
    }



}
