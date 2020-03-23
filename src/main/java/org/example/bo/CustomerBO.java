package org.example.bo;

import org.example.dao.CustomerDAO;

public class CustomerBO {

    CustomerDAO customerDAO = new CustomerDAO();

    public String getCustomer(){
        return customerDAO.getCustomer();
    }

}
