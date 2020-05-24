package com.hp.groceriapp.Shopowner.Model;

import java.util.List;

public class Categories_Model {

    /**
     * status : Success
     * Category_Details : [{"category_id":"1","category_name":"Dairy and Eggs"},{"category_id":"2","category_name":"Flours and Sooji"},{"category_id":"3","category_name":"Oil and Ghee"},{"category_id":"4","category_name":"Salt and Sugar"},{"category_id":"5","category_name":"Tea and Coffee"},{"category_id":"6","category_name":"Rice and Rice Product"},{"category_id":"7","category_name":"Spices and Masalas"},{"category_id":"8","category_name":"Others"},{"category_id":"9","category_name":"Beauty Products"}]
     */

    private String status;
    private List<CategoryDetailsBean> Category_Details;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CategoryDetailsBean> getCategory_Details() {
        return Category_Details;
    }

    public void setCategory_Details(List<CategoryDetailsBean> Category_Details) {
        this.Category_Details = Category_Details;
    }

    public static class CategoryDetailsBean {
        /**
         * category_id : 1
         * category_name : Dairy and Eggs
         */

        private String category_id;
        private String category_name;

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
        }
    }
}
