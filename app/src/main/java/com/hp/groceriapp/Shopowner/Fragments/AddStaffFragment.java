package com.hp.groceriapp.Shopowner.Fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.groceriapp.BuildConfig;
import com.hp.groceriapp.R;
import com.hp.groceriapp.Retro.Retro;
import com.hp.groceriapp.Shopowner.Model.AddStaffModel;
import com.hp.groceriapp.Utils.IOnBackPressed;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.logging.Logger;

import de.hdodenhof.circleimageview.CircleImageView;
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
public class AddStaffFragment extends Fragment implements IOnBackPressed {


    private CircleImageView staffdp;
    private EditText staffname;
    private EditText staffphone;
    private EditText staffpin;
    private EditText staffempid;
    private MaterialButton addStaff;
    File filedp;
    private File imgFile;
    private boolean isPhototaken;
    private AddStaffModel addStaffModel;

    private String pictureFilePath;
    MultipartBody.Part filePart;

    public AddStaffFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_add_staff, container, false);
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        initView(root);
        AppPreferences appPreferences = AppPreferences.getInstance(getActivity(), getResources().getString(R.string.app_name));

        isPhototaken=false;

        staffdp.setOnClickListener(view -> {
            selectImage();
        });


        addStaff.setOnClickListener(view -> {
            Log.e("PHOTO TAKEN", String.valueOf(isPhototaken));
            if (!isPhototaken ||
                    !staffname.getText().toString().equals("") ||
                    !staffempid.getText().toString().equals("") ||
                    !staffphone.getText().toString().equals("") ||
                    !staffpin.getText().toString().equals("")) {
                Log.e("PHOTO TAKEN inside", String.valueOf(isPhototaken));

                RequestBody snameBody = RequestBody.create(MediaType.parse("text/plain"), staffname.getText().toString());
                RequestBody sempidBody = RequestBody.create(MediaType.parse("text/plain"), staffempid.getText().toString());
                RequestBody sphoneBody = RequestBody.create(MediaType.parse("text/plain"), staffphone.getText().toString());
                RequestBody spinBody = RequestBody.create(MediaType.parse("text/plain"), staffpin.getText().toString());
                RequestBody sidBody = RequestBody.create(MediaType.parse("text/plain"), appPreferences.getData("adminid"));

                try {
                    filePart = MultipartBody.Part.createFormData("avatar", imgFile.getName(), RequestBody.create(MediaType.parse("image/*"), imgFile));

                }catch (Exception e)
                {
                    Toast.makeText(getContext(), "No image  file ", Toast.LENGTH_SHORT).show();
                }
                new Retro().getApi().ADD_STAFF_MODEL_CALL(sempidBody,
                        sphoneBody, snameBody, spinBody, sidBody, filePart).enqueue(new Callback<AddStaffModel>() {
                    @Override
                    public void onResponse(Call<AddStaffModel> call, Response<AddStaffModel> response) {
                        addStaffModel = response.body();
                        if (addStaffModel.getStatus().equalsIgnoreCase("success")) {
                            Snackbar.make(root, "Staff Added Sucessfully", BaseTransientBottomBar.LENGTH_LONG).show();
                        } else {
                            Snackbar.make(root, "Something went wrong. Try Again Later", BaseTransientBottomBar.LENGTH_LONG).show();

                        }


                    }

                    @Override
                    public void onFailure(Call<AddStaffModel> call, Throwable t) {
                        Snackbar.make(root, "ADD STAFF API FAILURE : CHECK INTERNET SEVICES \n" + t, BaseTransientBottomBar.LENGTH_LONG).show();

                    }
                });


            } else {
                Toast.makeText(getContext(), "Fill All details", Toast.LENGTH_SHORT).show();

            }
        });


        return root;
    }

    private void selectImage() {

        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
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
    public boolean onBackPressed() {
        FragmentTransaction t = getActivity().getSupportFragmentManager().beginTransaction();
        t.replace(R.id.frameLayout, new StaffsFragment());
        t.commit();
        return true;
    }

    private void initView(View root) {
        staffdp = root.findViewById(R.id.staffdp);
        staffname = root.findViewById(R.id.staffname);
        staffphone = root.findViewById(R.id.staffphone);
        staffpin = root.findViewById(R.id.staffpin);
        staffempid = root.findViewById(R.id.staffempid);
        addStaff = root.findViewById(R.id.addstaffbtn);
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
                    staffdp.setImageBitmap(bitmap);

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
                Log.e("path of image from gallery......******************.........", picturePath + "");
                staffdp.setImageBitmap(thumbnail);
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
