package com.hp.groceriapp.Staff.Models;

public class Staff_Login_Model {
    /**
     * status : Success
     * User_data : {"staff_id":"20","name":"Adarsh","photo":"http://srishti-systems.info/projects/groceryShop/uploads/300378-2-flower-wallpaper.preview.jpg","emp_id":"15","phone":"84512369755","pin":"67","id":"3","status":"Free","device_token":"dfsfdsfdfg"}
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
         * staff_id : 20
         * name : Adarsh
         * photo : http://srishti-systems.info/projects/groceryShop/uploads/300378-2-flower-wallpaper.preview.jpg
         * emp_id : 15
         * phone : 84512369755
         * pin : 67
         * id : 3
         * status : Free
         * device_token : dfsfdsfdfg
         */

        private String staff_id;
        private String name;
        private String photo;
        private String emp_id;
        private String phone;
        private String pin;
        private String id;
        private String status;
        private String device_token;

        public String getStaff_id() {
            return staff_id;
        }

        public void setStaff_id(String staff_id) {
            this.staff_id = staff_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getEmp_id() {
            return emp_id;
        }

        public void setEmp_id(String emp_id) {
            this.emp_id = emp_id;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getPin() {
            return pin;
        }

        public void setPin(String pin) {
            this.pin = pin;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDevice_token() {
            return device_token;
        }

        public void setDevice_token(String device_token) {
            this.device_token = device_token;
        }
    }
}
