package com.hp.groceriapp.Retro;



import com.hp.groceriapp.Shopowner.Model.Login_model;
import com.hp.groceriapp.Shopowner.Model.Reg_model;

import retrofit2.Call;
import retrofit2.http.GET;
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
   }