package com.hp.groceriapp.Customer.CustomerModels;

import java.util.List;

public class Cust_ViewModel {
    /**
     * status : Success
     * Customer_Details : [{"custmer_id":"3","name":"arathi","email":"arathisurendran@gmail.com","phone":"1232344567","password":"aarthi@1"}]
     */

    private String status;
    private List<CustomerDetailsBean> Customer_Details;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CustomerDetailsBean> getCustomer_Details() {
        return Customer_Details;
    }

    public void setCustomer_Details(List<CustomerDetailsBean> Customer_Details) {
        this.Customer_Details = Customer_Details;
    }

    public static class CustomerDetailsBean {
        /**
         * custmer_id : 3
         * name : arathi
         * email : arathisurendran@gmail.com
         * phone : 1232344567
         * password : aarthi@1
         */

        private String custmer_id;
        private String name;
        private String email;
        private String phone;
        private String password;

        public String getCustmer_id() {
            return custmer_id;
        }

        public void setCustmer_id(String custmer_id) {
            this.custmer_id = custmer_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
