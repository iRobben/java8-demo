package org.smart4j.chapterDemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapterDemo.model.Customer;
import org.smart4j.framework.helper.DatabaseHelper;

import java.util.List;
import java.util.Map;

/**
 * 提供客户数据服务
 * @author DELL by 2016/11/19
 */
public class CustomerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
    /**
     * 获取客户列表
     * @return
     */
    public List<Customer> getCustomerList(){

            String sql = "select * from customer";
            //1.
            /*conn = DatabaseHelper.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                Customer customer = new Customer();
                customer.setId(rs.getInt("id"));
                customer.setName(rs.getString("name"));
                customer.setContact(rs.getString("contact"));
                customer.setEmail(rs.getString("email"));
                customer.setTelephone(rs.getString("telephone"));
                customer.setRemark(rs.getString("remark"));
                customerList.add(customer);
            }*/
            //2.利用dbutils
            return DatabaseHelper.queryEntityList(Customer.class,sql);

    }

    /**
     * 获取客户
     * @param id
     * @return
     */
    public Customer getCustomer(long id){
        // TODO: 2016/11/19
        return null;
    }

    /**
     * 创建用户
     * @param paramMap
     * @return
     */
    public boolean createCustomer(Map<String,Object> paramMap){
        // TODO: 2016/11/19
        return false;
    }

    /**
     * 修改用户
     * @param id
     * @param paramMap
     * @return
     */
    public boolean updateCustomer(long id, Map<String,Object> paramMap){
        // TODO: 2016/11/19
        return false;
    }

    /**
     * 删除用户
     * @return
     */
    public boolean deleteCustomer(long id){
        // TODO: 2016/11/19
        return false;
    }
}
