package com.hp.groceriapp.Shopowner.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.groceriapp.BuildConfig;
import com.hp.groceriapp.R;
import com.hp.groceriapp.Retro.Retro;
import com.hp.groceriapp.Shopowner.Model.AddStaffModel;
import com.hp.groceriapp.Shopowner.Model.Edit_product_Model;
import com.hp.groceriapp.Shopowner.Model.Single_Product_model;
import com.hp.groceriapp.Utils.FragmentSwitcher;
import com.hp.groceriapp.Utils.IOnBackPressed;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import dmax.dialog.SpotsDialog;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditProductFragment extends Fragment implements IOnBackPressed {


    private ImageView pdtImage;
    private TextInputEditText pdtNameEdt;
    private TextInputEditText pdtBrandEdt;
    private TextInputEditText pdtQuantityEdt;
    private TextInputEditText pdtPriceEdt;
    private TextInputEditText pdtRackNoEdt;
    private AppCompatAutoCompleteTextView categories;
    private MaterialButton addphotoBtn;
    private MaterialButton addProductbtn;
    private AppPreferences appPreferences;
    private AlertDialog pd;
    private String admin_id, product_id;
    private boolean isphotoChanged;
    File filedp;
    private File imgFile;
    private boolean isPhototaken;

    private String pictureFilePath;
    MultipartBody.Part filePart;

    public EditProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_edit_product, container, false);
        initView(root);
        appPreferences = AppPreferences.getInstance(getActivity(), getResources().getString(R.string.app_name));
        pd = new SpotsDialog(getActivity(), R.style.CustomAlert);
        admin_id = appPreferences.getData("adminid");
        product_id = appPreferences.getData("edit_pdt_id");
        isphotoChanged = false;

        showDetails(admin_id, product_id);


        addProductbtn.setOnClickListener(v -> {
            saveDetails(isphotoChanged);

        });

        pdtImage.setOnClickListener(v -> {
            isphotoChanged=true;
            selectImage();

        });

        return root;
    }

    private void saveDetails(boolean isphotoChanged) {
        pd.show();

        if (isphotoChanged) {


            Log.e("PHOTO TAKEN", String.valueOf(isPhototaken));

            String newName = pdtNameEdt.getText().toString();
            String newBrand = pdtBrandEdt.getText().toString();
            String newQuantity = pdtQuantityEdt.getText().toString();
            String newprice = pdtPriceEdt.getText().toString();
            String newRack = pdtRackNoEdt.getText().toString();
            String newCategory = categories.getText().toString();

            RequestBody nameBody = RequestBody.create(MediaType.parse("text/plain"), newName);
            RequestBody brandBody = RequestBody.create(MediaType.parse("text/plain"), newBrand);
            RequestBody qtyBody = RequestBody.create(MediaType.parse("text/plain"), newQuantity);
            RequestBody priceBody = RequestBody.create(MediaType.parse("text/plain"), newprice);
            RequestBody rackBody = RequestBody.create(MediaType.parse("text/plain"), newRack);
            RequestBody categoryBody = RequestBody.create(MediaType.parse("text/plain"), newCategory);
            RequestBody pdt_id_Body = RequestBody.create(MediaType.parse("text/plain"), product_id);

            try {
                filePart = MultipartBody.Part.createFormData("avatar", imgFile.getName(), RequestBody.create(MediaType.parse("image/*"), imgFile));

            }catch (Exception e)
            {
                Toast.makeText(getContext(), "No image  file ", Toast.LENGTH_SHORT).show();
            }

            new Retro().getApi().editProductsCall(nameBody, pdt_id_Body, qtyBody, priceBody, rackBody,brandBody,filePart).enqueue(new Callback<Edit_product_Model>() {
                @Override
                public void onResponse(Call<Edit_product_Model> call, Response<Edit_product_Model> response) {
                    Edit_product_Model edit_product_model = response.body();
                    if (edit_product_model.getStatus().equalsIgnoreCase("success")) {

                        pd.dismiss();
                        Toast.makeText(getContext(), "Product changes saved .", Toast.LENGTH_SHORT).show();
                        new FragmentSwitcher().replaceFragment(new ProductsFragment(), getActivity());

                    } else {
                        pd.dismiss();
                     //   new FragmentSwitcher().replaceFragment(new ProductsFragment(), getActivity());

                        Toast.makeText(getContext(), "Something went wrong .", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Edit_product_Model> call, Throwable t) {
                    pd.dismiss();
                    Toast.makeText(getContext(), "edit api failure : " + t, Toast.LENGTH_SHORT).show();

                }
            });




        } else {

            String newName = pdtNameEdt.getText().toString();
            String newBrand = pdtBrandEdt.getText().toString();
            String newQuantity = pdtQuantityEdt.getText().toString();
            String newprice = pdtPriceEdt.getText().toString();
            String newRack = pdtRackNoEdt.getText().toString();
            String newCategory = categories.getText().toString();

            RequestBody nameBody = RequestBody.create(MediaType.parse("text/plain"), newName);
            RequestBody brandBody = RequestBody.create(MediaType.parse("text/plain"), newBrand);
            RequestBody qtyBody = RequestBody.create(MediaType.parse("text/plain"), newQuantity);
            RequestBody priceBody = RequestBody.create(MediaType.parse("text/plain"), newprice);
            RequestBody rackBody = RequestBody.create(MediaType.parse("text/plain"), newRack);
            RequestBody categoryBody = RequestBody.create(MediaType.parse("text/plain"), newCategory);
            RequestBody pdt_id_Body = RequestBody.create(MediaType.parse("text/plain"), product_id);

            new Retro().getApi().editProductsCall(nameBody, pdt_id_Body, qtyBody, priceBody, rackBody,brandBody).enqueue(new Callback<Edit_product_Model>() {
                @Override
                public void onResponse(Call<Edit_product_Model> call, Response<Edit_product_Model> response) {
                    Edit_product_Model edit_product_model = response.body();
                    if (edit_product_model.getStatus().equalsIgnoreCase("success")) {

                        pd.dismiss();
                        Toast.makeText(getContext(), "Product changes saved .", Toast.LENGTH_SHORT).show();
                        new FragmentSwitcher().replaceFragment(new ProductsFragment(), getActivity());

                    } else {
                        pd.dismiss();
                        new FragmentSwitcher().replaceFragment(new ProductsFragment(), getActivity());

                        Toast.makeText(getContext(), "Something went wrong .", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Edit_product_Model> call, Throwable t) {
                    pd.dismiss();
                    Toast.makeText(getContext(), "edit api failure : " + t, Toast.LENGTH_SHORT).show();

                }
            });


        }
    }

    private void showDetails(String admin_id, String product_id) {
        pd.show();
        new Retro().getApi().singleProdcutCall(admin_id, product_id).enqueue(new Callback<Single_Product_model>() {
            @Override
            public void onResponse(Call<Single_Product_model> call, Response<Single_Product_model> response) {
                Single_Product_model single_product_model = response.body();


                if (single_product_model.getStatus().equalsIgnoreCase("Success")) {
                    //null check for image
                    if (!single_product_model.getProduct_Details().getPhoto().equals("")) {


                        Glide.with(getActivity()).load(single_product_model.getProduct_Details().getPhoto()).into(pdtImage);
                    } else {
                        pdtImage.setImageResource(R.drawable.noitem);
                    }
                    pdtNameEdt.setText(single_product_model.getProduct_Details().getProduct_name());
                    pdtBrandEdt.setText(single_product_model.getProduct_Details().getBrand());
                    pdtQuantityEdt.setText(single_product_model.getProduct_Details().getQuantity());
                    pdtPriceEdt.setText(single_product_model.getProduct_Details().getPrice());
                    pdtRackNoEdt.setText(single_product_model.getProduct_Details().getRack_no());
                    categories.setText(single_product_model.getProduct_Details().getCategory_name());

                    pd.dismiss();

                } else {
                    pd.dismiss();
                    Toast.makeText(getActivity(), "Product not found !", Toast.LENGTH_SHORT).show();
                    //go back to product page
                    new FragmentSwitcher().replaceFragment(new ProductsFragment(), getActivity());
                }


            }

            @Override
            public void onFailure(Call<Single_Product_model> call, Throwable t) {
                Toast.makeText(getActivity(), "Product api failure ! " + t, Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });

    }

    @Override
    public boolean onBackPressed() {
        FragmentTransaction t = getActivity().getSupportFragmentManager().beginTransaction();
        t.replace(R.id.frameLayout, new ProductsFragment());
        t.commit();
        return true;
    }

    private void initView(View root) {
        pdtImage = root.findViewById(R.id.pdtImage);
        pdtNameEdt = root.findViewById(R.id.pdtNameEdt);
        pdtBrandEdt = root.findViewById(R.id.pdtBrandEdt);
        pdtQuantityEdt = root.findViewById(R.id.pdtQuantityEdt);
        pdtPriceEdt = root.findViewById(R.id.pdtPriceEdt);
        pdtRackNoEdt = root.findViewById(R.id.pdtRackNoEdt);
        categories = root.findViewById(R.id.categories);
        addphotoBtn = root.findViewById(R.id.addphotoBtn);
        addProductbtn = root.findViewById(R.id.addProductbtn);
    }
    private void selectImage() {

        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(getContext());
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {
                    takePicture();
                } else if (options[item].equals("Choose from Gallery")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(intent, 2);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 1) {
                //File for upload
                imgFile = new File(pictureFilePath);
                if (imgFile.exists()) {
                    isPhototaken = true;
                    Bitmap bitmap;
                    BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
                    bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath(),
                            bitmapOptions);
                    pdtImage.setImageBitmap(bitmap);

                    Toast.makeText(getContext(), "Photo Chosed for upload", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "imgFile not exist", Toast.LENGTH_SHORT).show();

                }
            } else if (requestCode == 2) {
                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};
                Cursor c = getActivity().getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();
                int columnIndex = c.getColumnIndex(filePath[0]);
                String picturePath = c.getString(columnIndex);
                c.close();
                imgFile = new File(picturePath);
                isPhototaken = true;

                Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
                Log.e("gallery imgpath", picturePath + "");
                pdtImage.setImageBitmap(thumbnail);
            }
        }
    }

    private void takePicture() {

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra(MediaStore.EXTRA_FINISH_ON_COMPLETION, true);
        if (cameraIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            //startActivityForResult(cameraIntent, REQUEST_PICTURE_CAPTURE);

            File pictureFile = null;
            try {
                pictureFile = getPictureFile();
            } catch (Exception ex) {
                Toast.makeText(getContext(),
                        "Photo file can't be created, please try again",
                        Toast.LENGTH_SHORT).show();
                return;
            }
            if (pictureFile != null) {
                Uri photoURI = FileProvider.getUriForFile(Objects.requireNonNull(getActivity()),
                        BuildConfig.APPLICATION_ID + ".provider", pictureFile);


                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(cameraIntent, 1);
            }
        }
    }

    private File getPictureFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String pictureFile = "Groceri" + timeStamp;
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(pictureFile, ".jpg", storageDir);
        pictureFilePath = image.getAbsolutePath();
        return image;
    }

}
