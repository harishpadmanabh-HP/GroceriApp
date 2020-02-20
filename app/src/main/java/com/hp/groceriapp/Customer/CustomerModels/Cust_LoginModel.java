package com.hp.groceriapp.Customer.CustomerModels;

public class Cust_LoginModel {
    /**
     * status : Success
     * User_data : {"customer_id":"2","name":"aarthi","email":"aarthi.sics@gmail.com","phone":"8825558630","password":"aarthi@1","device_token":""}
     */

    private String status;
    private UserDataBean User_data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UserDataBean getUser_data() {
        return User_data;
    }

    public void setUser_data(UserDataBean User_data) {
        this.User_data = User_data;
    }

    public static class UserDataBean {
        /**
         * customer_id : 2
         * name : aarthi
         * email : aarthi.sics@gmail.com
         * phone : 8825558630
         * password : aarthi@1
         * device_token :
         */

        private String customer_id;
        private String name;
        private String email;
        private String phone;
        private String password;
        private String device_token;

        public String getCustomer_id() {
            return customer_id;
        }

        public void setCustomer_id(String customer_id) {
            this.customer_id = customer_id;
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

        public String getDevice_token() {
            return device_token;
        }

        public void setDevice_token(String device_token) {
            this.device_token = device_token;
        }
    }
}
