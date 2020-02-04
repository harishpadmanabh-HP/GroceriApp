package com.hp.groceriapp.Shopowner.Model;

public class Login_model {

    /**
     * status : Success
     * User_data : {"id":"1","name":"harish","email":"qwerty@gmail.com","phone":"7894561230","shop_name":"srishti","building_address":"trivandrm","password":"qwerty"}
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
         * id : 1
         * name : harish
         * email : qwerty@gmail.com
         * phone : 7894561230
         * shop_name : srishti
         * building_address : trivandrm
         * password : qwerty
         */

        private String id;
        private String name;
        private String email;
        private String phone;
        private String shop_name;
        private String building_address;
        private String password;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
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

        public String getShop_name() {
            return shop_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public String getBuilding_address() {
            return building_address;
        }

        public void setBuilding_address(String building_address) {
            this.building_address = building_address;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
