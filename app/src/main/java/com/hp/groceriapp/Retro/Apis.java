package com.hp.groceriapp.Retro;



import com.hp.groceriapp.Shopowner.Model.AddStaffModel;
import com.hp.groceriapp.Shopowner.Model.AddproductModel;
import com.hp.groceriapp.Shopowner.Model.Login_model;
import com.hp.groceriapp.Shopowner.Model.ProductlistModel;
import com.hp.groceriapp.Shopowner.Model.Reg_model;
import com.hp.groceriapp.Shopowner.Model.StaffsListModel;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Apis {

   @GET("adminlogin_action.php?")
   Call<Login_model>LOGIN_MODEL_CALL(@Query("phone")String phone,
                                     @Query("password")String password);

   @GET("adminregs_action.php?")
   Call<Reg_model>REG_MODEL_CALL(@Query("name")String name,
                                 @Query("email")String email,
                                 @Query("phone")String phone,
                                 @Query("shop_name")String shop_name,
                                 @Query("building_address")String building_address,
                                 @Query("password")String password);

   @GET("view_product.php?")
   Call<ProductlistModel> PRODUCTLIST_MODEL_CALL(@Query("adminid") String id);



   @Multipart
   @POST("add_product.php")
   Call<AddproductModel> ADDPRODUCT_MODEL_CALL(
                                               @Part("product_name") RequestBody product_name,
                                               @Part("quantity") RequestBody quantity,
                                               @Part("brand") RequestBody brand,
                                               @Part("price") RequestBody price,
                                               @Part("rack_no") RequestBody rack_no,
                                               @Part("id") RequestBody id,
                                               @Part MultipartBody.Part file);
   @GET("staff_view.php?")
   Call<StaffsListModel>STAFFS_LIST_MODEL_CALL(@Query("id") String id);

   @Multipart
   @POST("add_staff.php")
   Call<AddStaffModel>ADD_STAFF_MODEL_CALL(@Part("emp_id")RequestBody emp_id,
                                           @Part("phone")RequestBody phone,
                                           @Part("name")RequestBody name,
                                           @Part("pin")RequestBody pin,
                                           @Part("id")RequestBody id,
                                           @Part MultipartBody.Part file);
}