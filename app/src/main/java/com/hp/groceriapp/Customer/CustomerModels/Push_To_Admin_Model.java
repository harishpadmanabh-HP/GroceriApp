package com.hp.groceriapp.Customer.CustomerModels;

import java.util.List;

public class Push_To_Admin_Model {
    /**
     * payload : {"message":"Customer Order Details","Product_details":[{"product_id":"8","product_name":"ASUS","brand":"ASUS VIVO BOOK","rack_no":"5","photo":"http://srishti-systems.info/projects/groceryShop/uploads/329921-groceri202003161343401149335496946624370.jpg","price":"32154","quantity":"1"},{"product_id":"8","product_name":"ASUS","brand":"ASUS VIVO BOOK","rack_no":"5","photo":"http://srishti-systems.info/projects/groceryShop/uploads/329921-groceri202003161343401149335496946624370.jpg","price":"32154","quantity":"1"}],"Customer_details":{"customer_id":"4","name":"harish","email":"har@outlook.com","phone":"9400880421"},"total_price":32154}
     * result : {"multicast_id":8.9409557435489997E18,"success":0,"failure":1,"canonical_ids":0,"results":[{"error":"MissingRegistration"}]}
     */

    private PayloadBean payload;
    private ResultBean result;

    public PayloadBean getPayload() {
        return payload;
    }

    public void setPayload(PayloadBean payload) {
        this.payload = payload;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class PayloadBean {
        /**
         * message : Customer Order Details
         * Product_details : [{"product_id":"8","product_name":"ASUS","brand":"ASUS VIVO BOOK","rack_no":"5","photo":"http://srishti-systems.info/projects/groceryShop/uploads/329921-groceri202003161343401149335496946624370.jpg","price":"32154","quantity":"1"},{"product_id":"8","product_name":"ASUS","brand":"ASUS VIVO BOOK","rack_no":"5","photo":"http://srishti-systems.info/projects/groceryShop/uploads/329921-groceri202003161343401149335496946624370.jpg","price":"32154","quantity":"1"}]
         * Customer_details : {"customer_id":"4","name":"harish","email":"har@outlook.com","phone":"9400880421"}
         * total_price : 32154
         */

        private String message;
        private CustomerDetailsBean Customer_details;
        private int total_price;
        private List<ProductDetailsBean> Product_details;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public CustomerDetailsBean getCustomer_details() {
            return Customer_details;
        }

        public void setCustomer_details(CustomerDetailsBean Customer_details) {
            this.Customer_details = Customer_details;
        }

        public int getTotal_price() {
            return total_price;
        }

        public void setTotal_price(int total_price) {
            this.total_price = total_price;
        }

        public List<ProductDetailsBean> getProduct_details() {
            return Product_details;
        }

        public void setProduct_details(List<ProductDetailsBean> Product_details) {
            this.Product_details = Product_details;
        }

        public static class CustomerDetailsBean {
            /**
             * customer_id : 4
             * name : harish
             * email : har@outlook.com
             * phone : 9400880421
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
             * product_id : 8
             * product_name : ASUS
             * brand : ASUS VIVO BOOK
             * rack_no : 5
             * photo : http://srishti-systems.info/projects/groceryShop/uploads/329921-groceri202003161343401149335496946624370.jpg
             * price : 32154
             * quantity : 1
             */

            private String product_id;
            private String product_name;
            private String brand;
            private String rack_no;
            private String photo;
            private String price;
            private String quantity;

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

            public String getBrand() {
                return brand;
            }

            public void setBrand(String brand) {
                this.brand = brand;
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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getQuantity() {
                return quantity;
            }

            public void setQuantity(String quantity) {
                this.quantity = quantity;
            }
        }
    }

    public static class ResultBean {
        /**
         * multicast_id : 8.9409557435489997E18
         * success : 0
         * failure : 1
         * canonical_ids : 0
         * results : [{"error":"MissingRegistration"}]
         */

        private double multicast_id;
        private int success;
        private int failure;
        private int canonical_ids;
        private List<ResultsBean> results;

        public double getMulticast_id() {
            return multicast_id;
        }

        public void setMulticast_id(double multicast_id) {
            this.multicast_id = multicast_id;
        }

        public int getSuccess() {
            return success;
        }

        public void setSuccess(int success) {
            this.success = success;
        }

        public int getFailure() {
            return failure;
        }

        public void setFailure(int failure) {
            this.failure = failure;
        }

        public int getCanonical_ids() {
            return canonical_ids;
        }

        public void setCanonical_ids(int canonical_ids) {
            this.canonical_ids = canonical_ids;
        }

        public List<ResultsBean> getResults() {
            return results;
        }

        public void setResults(List<ResultsBean> results) {
            this.results = results;
        }

        public static class ResultsBean {
            /**
             * error : MissingRegistration
             */

            private String error;

            public String getError() {
                return error;
            }

            public void setError(String error) {
                this.error = error;
            }
        }
    }
}
