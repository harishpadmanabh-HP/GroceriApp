package com.hp.groceriapp.Customer.CustomerModels;

import java.util.List;

public class ShopListModel {
    /**
     * status : Success
     * Shop_Details : [{"id":"1","name":"harish","email":"qwerty@gmail.com","phone":"7894561230","shop_name":"srishti","building_address":"trivandrm"},{"id":"3","name":"Harish","email":"harishpadmanabh@gmail.com","phone":"7012069385","shop_name":"BigM","building_address":"tvm"},{"id":"4","name":"harish","email":"harish@gmail.com","phone":"7894560230","shop_name":"srishti","building_address":"trivandrm"}]
     */

    private String status;
    private List<ShopDetailsBean> Shop_Details;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ShopDetailsBean> getShop_Details() {
        return Shop_Details;
    }

    public void setShop_Details(List<ShopDetailsBean> Shop_Details) {
        this.Shop_Details = Shop_Details;
    }

    public static class ShopDetailsBean {
        /**
         * id : 1
         * name : harish
         * email : qwerty@gmail.com
         * phone : 7894561230
         * shop_name : srishti
         * building_address : trivandrm
         */

        private String id;
        private String name;
        private String email;
        private String phone;
        private String shop_name;
        private String building_address;

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
    }
}
