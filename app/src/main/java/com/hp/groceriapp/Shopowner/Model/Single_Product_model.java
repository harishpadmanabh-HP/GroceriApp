package com.hp.groceriapp.Shopowner.Model;

public class Single_Product_model
{

    /**
     * status : Success
     * Product_Details : {"id":"1","product_id":"1","product_name":"Asus editned","quantity":"2","brand":"","price":"23445","rack_no":"","photo":"http://srishti-systems.info/projects/groceryShop/uploads/824965-appiconill.jpg","category_name":""}
     */

    private String status;
    private ProductDetailsBean Product_Details;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ProductDetailsBean getProduct_Details() {
        return Product_Details;
    }

    public void setProduct_Details(ProductDetailsBean Product_Details) {
        this.Product_Details = Product_Details;
    }

    public static class ProductDetailsBean {
        /**
         * id : 1
         * product_id : 1
         * product_name : Asus editned
         * quantity : 2
         * brand :
         * price : 23445
         * rack_no :
         * photo : http://srishti-systems.info/projects/groceryShop/uploads/824965-appiconill.jpg
         * category_name :
         */

        private String id;
        private String product_id;
        private String product_name;
        private String quantity;
        private String brand;
        private String price;
        private String rack_no;
        private String photo;
        private String category_name;

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

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }
    }
}
