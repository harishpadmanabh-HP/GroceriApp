package com.hp.groceriapp.Customer.CustomerModels;

public class OrderResponse_Model {
    /**
     * status : Success
     * price : 250
     */

    private String status;
    private int price;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
