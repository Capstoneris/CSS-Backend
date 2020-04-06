package de.hsh.capstoneris.bo;

import de.hsh.capstoneris.dao.CustomerDAO;

public class CustomerBO {

    CustomerDAO customerDAO = new CustomerDAO();

    public String getCustomer(){
        return customerDAO.getCustomer();
    }

}
