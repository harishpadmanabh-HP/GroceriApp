package com.hp.groceriapp.Customer.CustomerModels;

import java.util.List;

public class Cust_SearchModel {

    /**
     * status : Success
     * Product_Details : [{"id":"2","product_id":"4","product_name":"IPAD","quantity":"36","brand":"apple i pad ","price":"25000","rack_no":"5","photo":"http://srishti-systems.info/projects/groceryShop/uploads/331583-groceri202004010947451139179692.jpg"},{"id":"2","product_id":"6","product_name":"IPAD","quantity":"39","brand":"apple i pad ","price":"25000","rack_no":"5","photo":"http://srishti-systems.info/projects/groceryShop/uploads/439028-groceri202004010947451139179692.jpg"},{"id":"2","product_id":"10","product_name":"IPAD","quantity":"30","brand":"apple I pad","price":"25000","rack_no":"5","photo":"http://srishti-systems.info/projects/groceryShop/uploads/692048-groceri20200401101146940709990.jpg"}]
     */

    private String status;
    private List<ProductDetailsBean> Product_Details;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ProductDetailsBean> getProduct_Details() {
        return Product_Details;
    }

    public void setProduct_Details(List<ProductDetailsBean> Product_Details) {
        this.Product_Details = Product_Details;
    }

    public static class ProductDetailsBean {
        /**
         * id : 2
         * product_id : 4
         * product_name : IPAD
         * quantity : 36
         * brand : apple i pad
         * price : 25000
         * rack_no : 5
         * photo : http://srishti-systems.info/projects/groceryShop/uploads/331583-groceri202004010947451139179692.jpg
         */

        private String id;
        private String product_id;
        private String product_name;
        private String quantity;
        private String brand;
        private String price;
        private String rack_no;
        private String photo;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getRack_no() {
            return rack_no;
        }

        public void setRack_no(String rack_no) {
            this.rack_no = rack_no;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }
    }
}
