package org.smart4j.chapterDemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapterDemo.model.Customer;
import org.smart4j.chapterDemo.service.CustomerService;
import org.smart4j.framework.annotation.Action;
import org.smart4j.framework.annotation.Controller;
import org.smart4j.framework.annotation.Inject;
import org.smart4j.framework.bean.Param;
import org.smart4j.framework.bean.View;

import java.util.List;

/**
 * @author zhangrh by 2016/12/2
 */
@Controller
public class CustomerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    @Inject
    private CustomerService customerService;

    /**
     * 列表页面
     */
    @Action("get:/customerList")
    public View customer(Param param){
        LOGGER.info("进入方法，代表配置成功，框架基本功能已经完成");
        List<Customer> customerList = customerService.getCustomerList();
        return new View("customer.jsp").addModel("customerList",customerList);
    }

}
