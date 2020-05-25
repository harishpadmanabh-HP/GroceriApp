package com.hp.groceriapp.Retro;



import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.hp.groceriapp.Customer.CustomerModels.Cust_LoginModel;
import com.hp.groceriapp.Customer.CustomerModels.Cust_SearchModel;
import com.hp.groceriapp.Customer.CustomerModels.Cust_SignupModel;
import com.hp.groceriapp.Customer.CustomerModels.Cust_ViewModel;
import com.hp.groceriapp.Customer.CustomerModels.OrderResponse_Model;
import com.hp.groceriapp.Customer.CustomerModels.ProductList_Model;
import com.hp.groceriapp.Customer.CustomerModels.Push_To_Admin_Model;
import com.hp.groceriapp.Customer.CustomerModels.ShopListModel;
import com.hp.groceriapp.Shopowner.Model.AddStaffModel;
import com.hp.groceriapp.Shopowner.Model.AddproductModel;
import com.hp.groceriapp.Shopowner.Model.Categories_Model;
import com.hp.groceriapp.Shopowner.Model.Delete_Pdt_Model;
import com.hp.groceriapp.Shopowner.Model.Delete_Staff_Model;
import com.hp.groceriapp.Shopowner.Model.Edit_product_Model;
import com.hp.groceriapp.Shopowner.Model.FreeStaffModel;
import com.hp.groceriapp.Shopowner.Model.Login_model;
import com.hp.groceriapp.Shopowner.Model.ProductlistModel;
import com.hp.groceriapp.Shopowner.Model.PushtoStaffModel;
import com.hp.groceriapp.Shopowner.Model.Reg_model;
import com.hp.groceriapp.Shopowner.Model.Shp_SingleProductModel;
import com.hp.groceriapp.Shopowner.Model.Shp_ViewStaffModel;
import com.hp.groceriapp.Shopowner.Model.Single_Product_model;
import com.hp.groceriapp.Shopowner.Model.StaffsListModel;
import com.hp.groceriapp.Staff.Models.AcceptOrderModel;
import com.hp.groceriapp.Staff.Models.DeliverModel;
import com.hp.groceriapp.Staff.Models.OrderList_To_Staff_Model;
import com.hp.groceriapp.Staff.Models.StaffDetailsModel;
import com.hp.groceriapp.Staff.Models.Staff_Login_Model;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Apis {

   @GET("adminlogin_action.php?")
   Call<Login_model>LOGIN_MODEL_CALL(@Query("phone")String phone,
                                     @Query("password")String password,
                                     @Query("device_token") String device_token);

   @GET("adminregs_action.php?")
   Call<Reg_model>REG_MODEL_CALL(@Query("name")String name,
                                 @Query("email")String email,
                                 @Query("phone")String phone,
                                 @Query("shop_name")String shop_name,
                                 @Query("building_address")String building_address,
                                 @Query("password")String password,
                                 @Query("device_token") String device_token);

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
                                               @Part MultipartBody.Part file,
                                               @Part("category_name") RequestBody category_name
                                               );
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

   @GET("customer_login.php?")
   Call<Cust_LoginModel> CUST_LOGIN_MODEL_CALL(@Query("phone") String phone,
                                               @Query("password") String password);

   @GET("customer_registration.php?")
   Call<Cust_SignupModel>CUST_SIGNUP_MODEL_CALL(@Query("name") String name,
                                                @Query("email") String email,
                                                @Query("phone") String phone,
                                                @Query("password") String password,
                                                @Query("device_token") String device_token);


   @GET("customer_view.php?")
   Call<Cust_ViewModel>CUST_VIEW_MODEL_CALL(@Query("id") String customerid);

   @GET("view_staff.php?")
   Call<Shp_ViewStaffModel>SHP_VIEW_STAFF_MODEL_CALL(@Query("id") String adminid,
                                                     @Query("staff_id") String staff_id);

   @GET("view_single_product.php?")
   Call<Shp_SingleProductModel>SHP_SINGLE_PRODUCT_MODEL_CALL(@Query("adminid") String adminid,
                                                             @Query("product_id") String product_id);


   @GET("shop_list.php")
   Call<ShopListModel>SHOP_LIST_MODEL_CALL();

   @GET("view_product.php?")
   Call<ProductList_Model> customerViewProducts(@Query("adminid") String adminid);

   @POST("order_product.php")
   Call<OrderResponse_Model> orderProducts(@Body RequestBody requestBody);

   @GET("stafflogin_action.php?")
   Call<Staff_Login_Model> staffLogin(@Query("emp_id") String emp_id,
                                      @Query("pin") String pin,
                                      @Query("device_token") String device_token);

   @GET("pushnotification.php?")
   Call<Push_To_Admin_Model> pushtoAdmin(@Query("id") String id,
                                         @Query("custmer_id") String custmer_id);

   @GET("free_staff.php?")
   Call<FreeStaffModel> freestaffs(@Query("id") String id);

   @GET("pushToStaff.php?")
   Call<PushtoStaffModel> pushToStaff(@Query("id") String id,
                                      @Query("custmer_id") String custmer_id,
                                      @Query("staff_id") String Staffid);

   @GET("viewOrderProduct.php?")
   Call<OrderList_To_Staff_Model> orderListStaff(@Query("id") String id,
                                                 @Query("customer_id") String custmer_id);

   @GET("Staff_accept_order.php?")
   Call<AcceptOrderModel> acceptOrder(@Query("staff_id") String staff_id,
                                      @Query("id") String id);

   @GET("delivered.php?")
   Call<DeliverModel> deliverOrder(@Query("staff_id") String staff_id,
                                   @Query("id") String id,
                                   @Query("customer_id") String customer_id);

   @GET("view_staff.php?")
   Call<StaffDetailsModel> staffDetailsCall(@Query("id") String id,
                                            @Query("staff_id") String staff_id);

   @GET("product_view_byname.php?")
   Call<Cust_SearchModel>searchProductsCall(@Query("product_name") String product_name,
                                            @Query("id") String id);

   @GET("delete_product.php?")
   Call<Delete_Pdt_Model>deleteProduct(@Query("product_id") String product_id);

   @GET("view_category.php")
   Call<Categories_Model>categoriesListCall();

   @Multipart
   @POST("edit_product.php")
   Call<Edit_product_Model>editProductsCall(@Part("product_name") RequestBody product_name,
                                            @Part("product_id") RequestBody product_id,
                                            @Part("quantity") RequestBody quantity,
                                            @Part("price") RequestBody price,
                                            @Part("rack_no") RequestBody rack_no,
                                            @Part("brand") RequestBody brand,
                                            @Part MultipartBody.Part file
                                            );
   @Multipart
   @POST("edit_product.php")
   Call<Edit_product_Model>editProductsCall(@Part("product_name") RequestBody product_name,
                                            @Part("product_id") RequestBody product_id,
                                            @Part("quantity") RequestBody quantity,
                                            @Part("price") RequestBody price,
                                            @Part("rack_no") RequestBody rack_no,
                                            @Part("brand") RequestBody brand
                                            );


   @GET("view_single_product.php?")
   Call<Single_Product_model>singleProdcutCall(@Query("adminid") String adminid,
                                               @Query("product_id") String product_id);

   @GET("view_product_by_category.php?")
   Call<ProductList_Model>filterCall(@Query("adminid") String adminid,
                                    @Query("category_name") String category_name);

   @GET("delete_staff.php?")
   Call<Delete_Staff_Model>deleteStaffCall(@Query("staff_id") String staff_id);
}