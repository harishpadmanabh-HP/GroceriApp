package com.hp.groceriapp.Shopowner.Model;

import java.util.List;

public class Shp_ViewStaffModel {
    /**
     * status : Success
     * Staff_Details : [{"id":"1","staff_id":"9","name":"Surendran","photo":"http://srishti-systems.info/projects/groceryShop/uploads/490171-screenshot_20190318-114029.png","emp_id":"8","phone":"986543210","pin":"2"}]
     */

    private String status;
    private List<StaffDetailsBean> Staff_Details;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<StaffDetailsBean> getStaff_Details() {
        return Staff_Details;
    }

    public void setStaff_Details(List<StaffDetailsBean> Staff_Details) {
        this.Staff_Details = Staff_Details;
    }

    public static class StaffDetailsBean {
        /**
         * id : 1
         * staff_id : 9
         * name : Surendran
         * photo : http://srishti-systems.info/projects/groceryShop/uploads/490171-screenshot_20190318-114029.png
         * emp_id : 8
         * phone : 986543210
         * pin : 2
         */

        private String id;
        private String staff_id;
        private String name;
        private String photo;
        private String emp_id;
        private String phone;
        private String pin;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

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
    }
}
