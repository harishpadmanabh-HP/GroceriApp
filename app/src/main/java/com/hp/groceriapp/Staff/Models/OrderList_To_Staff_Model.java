package com.hp.groceriapp.Staff.Models;

import java.util.List;

public class OrderList_To_Staff_Model {
    /**
     * status : Success
     * Product_details : [{"product_id":"2","product_name":"IPAD","quantity":"2","brand":"apple i pad","price":50000,"rack_no":"5","photo":"http://srishti-systems.info/projects/groceryShop/uploads/574740-groceri202003162147049186940111976698450.jpg","id":"1"}]
     * Customer_details : {"customer_id":"2","name":"harish","email":"harishpadmanabh@gmail.com","phone":"7012069385"}
     * total : 50000
     */

    private String status;
    private CustomerDetailsBean Customer_details;
    private int total;
    private List<ProductDetailsBean> Product_details;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public CustomerDetailsBean getCustomer_details() {
        return Customer_details;
    }

    public void setCustomer_details(CustomerDetailsBean Customer_details) {
        this.Customer_details = Customer_details;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<ProductDetailsBean> getProduct_details() {
        return Product_details;
    }

    public void setProduct_details(List<ProductDetailsBean> Product_details) {
        this.Product_details = Product_details;
    }

    public static class CustomerDetailsBean {
        /**
         * customer_id : 2
         * name : harish
         * email : harishpadmanabh@gmail.com
         * phone : 7012069385
         */

        private String customer_id;
        private String name;
        private String email;
        private String phone;

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
    }

    public static class ProductDetailsBean {
        /**
         * product_id : 2
         * product_name : IPAD
         * quantity : 2
         * brand : apple i pad
         * price : 50000
         * rack_no : 5
         * photo : http://srishti-systems.info/projects/groceryShop/uploads/574740-groceri202003162147049186940111976698450.jpg
         * id : 1
         */

        private String product_id;
        private String product_name;
        private String quantity;
        private String brand;
        private int price;
        private String rack_no;
        private String photo;
        private String id;

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

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
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

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }
}
