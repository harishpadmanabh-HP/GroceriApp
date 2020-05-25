package com.hp.groceriapp.Shopowner.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;
import com.harishpadmanabh.apppreferences.AppPreferences;
import com.hp.groceriapp.BuildConfig;
import com.hp.groceriapp.R;
import com.hp.groceriapp.Retro.Retro;
import com.hp.groceriapp.Shopowner.Model.AddStaffModel;
import com.hp.groceriapp.Shopowner.Model.Edit_Staff_Model;
import com.hp.groceriapp.Shopowner.Model.Single_Staff_Model;
import com.hp.groceriapp.Utils.FragmentSwitcher;
import com.hp.groceriapp.Utils.IOnBackPressed;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
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
public class EditStaffFragment extends Fragment implements IOnBackPressed {


    private CircleImageView staffdp;
    private EditText staffname;
    private EditText staffphone;
    private EditText staffpin;
    private EditText staffempid;
    private MaterialButton addstaffbtn;
    private AppPreferences appPreferences;
    String staffid, adminid;
    private AlertDialog pd;
    boolean isphotochanged;
    File filedp;
    private File imgFile;
    private boolean isPhototaken;
    private AddStaffModel addStaffModel;

    private String pictureFilePath;
    MultipartBody.Part filePart;



    public EditStaffFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_edit_staff, container, false);
        initView(root);
        isphotochanged = false;
        pd = new SpotsDialog(getActivity(), R.style.CustomAlert);
        appPreferences = AppPreferences.getInstance(getActivity(), getResources().getString(R.string.app_name));
        staffid = appPreferences.getData("context_staffid");
        adminid = appPreferences.getData("adminid");
        isPhototaken=false;

        showDetails(adminid, staffid);
        staffdp.setOnClickListener(view -> {
            isphotochanged=true;
            selectImage();
        });
        addstaffbtn.setOnClickListener(v -> {
            saveDetails(isphotochanged);
        });


        return root;
    }

    private void saveDetails(boolean isphotochanged) {

        if (isphotochanged) {
            pd.show();
            Log.e("PHOTO TAKEN", String.valueOf(isPhototaken));

            String newName = staffname.getText().toString();
            String newPhone = staffphone.getText().toString();
            String newPin = staffpin.getText().toString();
            String newEmpid = staffempid.getText().toString();

            RequestBody nameBody = RequestBody.create(MediaType.parse("text/plain"), newName);
            RequestBody phoneBody = RequestBody.create(MediaType.parse("text/plain"), newPhone);
            RequestBody pinBody = RequestBody.create(MediaType.parse("text/plain"), newPin);
            RequestBody empidBody = RequestBody.create(MediaType.parse("text/plain"), newEmpid);
            RequestBody staffidBody = RequestBody.create(MediaType.parse("text/plain"), staffid);
            try {
                filePart = MultipartBody.Part.createFormData("avatar", imgFile.getName(), RequestBody.create(MediaType.parse("image/*"), imgFile));

            }catch (Exception e)
            {
                Toast.makeText(getContext(), "No image  file ", Toast.LENGTH_SHORT).show();
            }
            new Retro().getApi().editStaffCall(nameBody, phoneBody, empidBody, pinBody, staffidBody,filePart).enqueue(new Callback<Edit_Staff_Model>() {
                @Override
                public void onResponse(Call<Edit_Staff_Model> call, Response<Edit_Staff_Model> response) {

                    Edit_Staff_Model edit_staff_model =response.body();
                    if(edit_staff_model.getStatus().equalsIgnoreCase("success"))
                    {
                        pd.dismiss();
                        Toast.makeText(getContext(), "Changes saved.", Toast.LENGTH_SHORT).show();
                        new FragmentSwitcher().replaceFragment(new StaffsFragment(), getActivity());

                    }

                }

                @Override
                public void onFailure(Call<Edit_Staff_Model> call, Throwable t) {

                    pd.dismiss();
                    Toast.makeText(getContext(), "edit staff api fail "+t, Toast.LENGTH_SHORT).show();

                }
            });



        } else {
            pd.show();
            String newName = staffname.getText().toString();
            String newPhone = staffphone.getText().toString();
            String newPin = staffpin.getText().toString();
            String newEmpid = staffempid.getText().toString();

            RequestBody nameBody = RequestBody.create(MediaType.parse("text/plain"), newName);
            RequestBody phoneBody = RequestBody.create(MediaType.parse("text/plain"), newPhone);
            RequestBody pinBody = RequestBody.create(MediaType.parse("text/plain"), newPin);
            RequestBody empidBody = RequestBody.create(MediaType.parse("text/plain"), newEmpid);
            RequestBody staffidBody = RequestBody.create(MediaType.parse("text/plain"), staffid);

            new Retro().getApi().editStaffCall(nameBody, phoneBody, empidBody, pinBody, staffidBody).enqueue(new Callback<Edit_Staff_Model>() {
                @Override
                public void onResponse(Call<Edit_Staff_Model> call, Response<Edit_Staff_Model> response) {
                         Edit_Staff_Model edit_staff_model =response.body();
                         if(edit_staff_model.getStatus().equalsIgnoreCase("success")){
                             pd.dismiss();
                             Toast.makeText(getContext(), "Changes saved.", Toast.LENGTH_SHORT).show();
                             new FragmentSwitcher().replaceFragment(new StaffsFragment(), getActivity());

                         }else
                         {
                             pd.dismiss();
                             Toast.makeText(getContext(), "Something went wrong.Try again later.", Toast.LENGTH_SHORT).show();
                         }

                }

                @Override
                public void onFailure(Call<Edit_Staff_Model> call, Throwable t) {
                    pd.dismiss();
                    Toast.makeText(getContext(), "Edit staff api failure " + t, Toast.LENGTH_SHORT).show();
                }
            });


        }


    }

    private void showDetails(String adminid, String staffid) {
        pd.show();
        new Retro().getApi().singleStaffDetailsCall(adminid, staffid).enqueue(new Callback<Single_Staff_Model>() {
            @Override
            public void onResponse(Call<Single_Staff_Model> call, Response<Single_Staff_Model> response) {
                Single_Staff_Model single_staff_model = response.body();
                if (single_staff_model.getStatus().equalsIgnoreCase("success")) {
                    Glide.with(getContext()).load(single_staff_model.getStaff_Details().get(0).getPhoto()).into(staffdp);
                    staffname.setText(single_staff_model.getStaff_Details().get(0).getName());
                    staffphone.setText(single_staff_model.getStaff_Details().get(0).getPhone());
                    staffpin.setText(single_staff_model.getStaff_Details().get(0).getPin());
                    staffempid.setText(single_staff_model.getStaff_Details().get(0).getEmp_id());

                    pd.dismiss();

                } else {
                    pd.dismiss();
                    Toast.makeText(getContext(), "Staff not found", Toast.LENGTH_SHORT).show();
                    new FragmentSwitcher().replaceFragment(new StaffsFragment(), getActivity());

                }
            }

            @Override
            public void onFailure(Call<Single_Staff_Model> call, Throwable t) {
                pd.dismiss();
                Toast.makeText(getContext(), "Single_Staff_Model api fail " + t, Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initView(View root) {
        staffdp = root.findViewById(R.id.staffdp);
        staffname = root.findViewById(R.id.staffname);
        staffphone = root.findViewById(R.id.staffphone);
        staffpin = root.findViewById(R.id.staffpin);
        staffempid = root.findViewById(R.id.staffempid);
        addstaffbtn = root.findViewById(R.id.addstaffbtn);
    }

    @Override
    public boolean onBackPressed() {
        FragmentTransaction t = getActivity().getSupportFragmentManager().beginTransaction();
        t.replace(R.id.frameLayout, new StaffsFragment());
        t.commit();
        return true;
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
                    isphotochanged=false;
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
                Log.e("img PATH gallery", picturePath + "");
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
