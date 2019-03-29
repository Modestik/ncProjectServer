package nc.test.dao;

import nc.test.model.Customer;


public interface CustomerDao {

    Customer getCustomer(String name);

    void update(Customer customer);

    void insert(Customer cust);
}
