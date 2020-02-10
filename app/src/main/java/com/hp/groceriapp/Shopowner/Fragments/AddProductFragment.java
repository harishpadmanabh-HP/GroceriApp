package com.hp.groceriapp.Shopowner.Fragments;


import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.loader.content.CursorLoader;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.groceriapp.BuildConfig;
import com.hp.groceriapp.R;
import com.hp.groceriapp.Retro.Retro;
import com.hp.groceriapp.Shopowner.Model.AddproductModel;
import com.hp.groceriapp.Utils.FileUtils;
import com.hp.groceriapp.Utils.IOnBackPressed;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
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
public class AddProductFragment extends Fragment implements IOnBackPressed {


    private TextInputEditText mPdtNameEdt;
    private TextInputEditText mPdtBrandEdt;
    private TextInputEditText mPdtQuantityEdt;
    private TextInputEditText mPdtPriceEdt;
    private TextInputEditText mPdtRackNoEdt;
    private MaterialButton mAddphotoBtn;
    private MaterialButton mAddProductbtn;

    private static final int REQUEST_WRITE_PERMISSION = 20;

    private AppPreferences appPreferences;
    AddproductModel addproductModel;

    private AlertDialog pd;



    private static final String TAG = "CapturePicture";
    static final int REQUEST_PICTURE_CAPTURE = 1;
    private String pictureFilePath;
    private File imgFile;

    public AddProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_product, container, false);

        getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        appPreferences = AppPreferences.getInstance(getActivity(), getResources().getString(R.string.app_name));
        pd = new SpotsDialog(getActivity(),R.style.CustomAlert);

        initView(root);

        mAddProductbtn.setVisibility(View.GONE);

        //....DUMMY CREDENTIALS

        mPdtNameEdt.setText("ASUS");
        mPdtBrandEdt.setText("ASUS VIVO BOOK");
        mPdtQuantityEdt.setText("2");
        mPdtPriceEdt.setText("32154 Rs");
        mPdtRackNoEdt.setText("5");

        //.....DUMMY CREDENTIALS.....................................

        mAddphotoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityCompat.requestPermissions(getActivity(), new
                        String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.CAMERA}, REQUEST_WRITE_PERMISSION);


                takePicture();
            }
        });
        mAddProductbtn.setOnClickListener(view -> {
            File file = imgFile;

            if(imgFile == null ||
                    Objects.requireNonNull(mPdtNameEdt.getText()).toString().equals("")||
                    Objects.requireNonNull(mPdtBrandEdt.getText()).toString().equals("")||
                    Objects.requireNonNull(mPdtPriceEdt.getText()).toString().equals("")||
                    Objects.requireNonNull(mPdtQuantityEdt.getText()).toString().equals("")||
                    Objects.requireNonNull(mPdtRackNoEdt.getText()).toString().equals(""))
            {
                Snackbar.make(view,"All data are necessary to upload product", Snackbar.LENGTH_SHORT)
                        .show();            }
            else {
                pd.show();

               if(file.exists())
                    {

                        Bitmap myBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());


                        MultipartBody.Part filePart = MultipartBody.Part.createFormData("avatar", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));

                        new Retro().getApi().ADDPRODUCT_MODEL_CALL(mPdtNameEdt.getText().toString(),
                                mPdtQuantityEdt.getText().toString(),
                                mPdtBrandEdt.getText().toString(),
                                mPdtPriceEdt.getText().toString(),
                                mPdtRackNoEdt.getText().toString(),
                                appPreferences.getData("adminid"),
                                filePart
                                ).enqueue(new Callback<AddproductModel>() {
                            @Override
                            public void onResponse(Call<AddproductModel> call, Response<AddproductModel> response) {
                             addproductModel=response.body();
                             if(addproductModel.getStatus().equalsIgnoreCase("success")) {
                                 Snackbar.make(root, addproductModel.getStatus(), BaseTransientBottomBar.LENGTH_LONG).show();
                                 pd.dismiss();
                             }else
                             {
                                 Snackbar.make(root, addproductModel.getStatus(), BaseTransientBottomBar.LENGTH_LONG).show();
                                 pd.dismiss();
                             }
                            }

                            @Override
                            public void onFailure(Call<AddproductModel> call, Throwable t) {

                                pd.dismiss();
                                Toast.makeText(getContext(), "Failed product File upload    "+t, Toast.LENGTH_SHORT).show();
                            }
                        });

                    }else
                    {
                        Toast.makeText(getContext(), "file error", Toast.LENGTH_SHORT).show();
                    }




            }
        });




        return root;
    }

    private void initView(View root) {
        mPdtNameEdt =  root.findViewById(R.id.pdtNameEdt);
        mPdtBrandEdt =  root.findViewById(R.id.pdtBrandEdt);
        mPdtQuantityEdt = root. findViewById(R.id.pdtQuantityEdt);
        mPdtPriceEdt =  root.findViewById(R.id.pdtPriceEdt);
        mPdtRackNoEdt = root. findViewById(R.id.pdtRackNoEdt);
        mAddphotoBtn =  root.findViewById(R.id.addphotoBtn);
        mAddProductbtn =  root.findViewById(R.id.addProductbtn);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_WRITE_PERMISSION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    takePicture();
                } else {
                    Toast.makeText(getContext(), "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
        }
    }
    private void takePicture() {

        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cameraIntent.putExtra( MediaStore.EXTRA_FINISH_ON_COMPLETION, true);
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
                Uri photoURI=   FileProvider.getUriForFile(Objects.requireNonNull(getActivity()),
                        BuildConfig.APPLICATION_ID + ".provider", pictureFile);


                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(cameraIntent, REQUEST_PICTURE_CAPTURE);
            }
        }
    }

    private File getPictureFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String pictureFile = "Groceri" + timeStamp;
        File storageDir = getActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(pictureFile,  ".jpg", storageDir);
        pictureFilePath = image.getAbsolutePath();
        return image;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PICTURE_CAPTURE && resultCode == RESULT_OK) {


            //File for upload
            imgFile = new  File(pictureFilePath);
            if(imgFile.exists())
            {
                //Setting up Ui Buttons
                mAddProductbtn.setVisibility(View.VISIBLE);
                mAddphotoBtn.setVisibility(View.GONE);
                mAddphotoBtn.setEnabled(false);
                Toast.makeText(getContext(), "Photo Chosed for upload", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(getContext(), "imgFile not exist", Toast.LENGTH_SHORT).show();

            }
        }
    }


    @Override
    public boolean onBackPressed() {
        FragmentTransaction t = getActivity().getSupportFragmentManager().beginTransaction();
        t.replace(R.id.frameLayout, new ProductsFragment());
        t.commit();
        return true;
    }
}
