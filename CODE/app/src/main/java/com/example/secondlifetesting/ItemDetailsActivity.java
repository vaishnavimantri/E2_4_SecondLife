package com.example.secondlifetesting;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.ByteArrayOutputStream;


public class ItemDetailsActivity extends AppCompatActivity {

        private StorageReference storageReference = null;
        private DatabaseReference databaseReference = null;
        private Uri filePath = null;
        ImageView viewImage;
        Button b;
       TextView address;
        TextView city;
        EditText Price;
        FirebaseFirestore firebaseFirestore;
        private final int PICK_IMAGE_GALLERY_CODE = 78;
        private final int CAMERA_PERMISSION_REQUEST_CODE = 12345;
        private final int CAMERA_PICTURE_REQUEST_CODE = 56789;
        private String curent_user_id;
@Override
protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
        databaseReference  =  database.getReference().child("user_images");
        storageReference = firebaseStorage.getReference();
        firebaseFirestore = FirebaseFirestore.getInstance();
          setContentView(R.layout.activity_item_details);

        address=findViewById(R.id.displaylocation);
        city=findViewById(R.id.displaycity);
        Price=(EditText)findViewById(R.id.price);



        b=(Button)findViewById(R.id.btnSelectPhoto);
        viewImage=(ImageView)findViewById(R.id.viewImage);

        b.setOnClickListener(new View.OnClickListener(){
@Override
public void onClick(View view){

        selectImage();
        }
        });



        }


        private void selectImage(){
                androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(this);
                builder.setTitle("Select Image");
                builder.setMessage("Please select an option");
                builder.setPositiveButton("Camera", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                                checkCameraPermission();
                                dialog.dismiss();
                        }
                });

                builder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                        }
                });
                builder.setNegativeButton("Gallery", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                                selectFromGallery();
                                dialog.dismiss();
                        }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
        }

        private void checkCameraPermission() {
                if(ContextCompat.checkSelfPermission(ItemDetailsActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED
                        || ContextCompat.checkSelfPermission(ItemDetailsActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED ) {
                        ActivityCompat.requestPermissions(ItemDetailsActivity.this, new String[] {
                                Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE
                        }, CAMERA_PERMISSION_REQUEST_CODE);
                } else {
                        openCamera();
                }

        }

        @Override
        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                if(requestCode == CAMERA_PERMISSION_REQUEST_CODE && grantResults[1] ==PackageManager.PERMISSION_GRANTED){
                        openCamera();
                }
        }

        private void openCamera() {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if(intent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(intent, CAMERA_PICTURE_REQUEST_CODE);
                }
        }

        private void selectFromGallery() {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE_GALLERY_CODE);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
                super.onActivityResult(requestCode, resultCode, data);

                if(requestCode  ==  PICK_IMAGE_GALLERY_CODE && resultCode == Activity.RESULT_OK) {
                        if(data == null || data.getData() == null)
                                return;

                        try {
                                filePath = data.getData();
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                                viewImage.setImageBitmap(bitmap);
                        }catch (Exception e) {

                        }
                } else if(requestCode == CAMERA_PICTURE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
                        Bundle extras = data.getExtras();
                        Bitmap bitmap  = (Bitmap)extras.get("data");
                        viewImage.setImageBitmap(bitmap);
                        filePath =getImageUri(getApplicationContext(), bitmap);
                }

        }


        private Uri getImageUri(Context context, Bitmap bitmap) {
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                String path = MediaStore.Images.Media.insertImage(getContentResolver(), bitmap, "title", null);
                return Uri.parse(path);
        }




        public void itemsadded(View view) {
                firebaseFirestore.collection("Users");

                                      firebaseFirestore.collection("Users").document().get().
                                addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                                        @Override
                                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                                                String price= task.getResult().getString("Price");
                                                String add = task.getResult().getString("address");
                                                String cname = task.getResult().getString("city");

                                                city.setText(cname);
                                                Price.setText(price);
                                                address.setText(add);
                                                Toast.makeText(ItemDetailsActivity.this, "Sucessfully uploaded successfully", Toast.LENGTH_SHORT).show();

                                        }

                                });
                }










        public void openmap(View view){
        Intent maps=new Intent(ItemDetailsActivity.this,MapsActivity.class);
        startActivity(maps);
        }

        public void gotorecycle(View view){

        Intent recycle=new Intent(ItemDetailsActivity.this,RecylerActivity.class);
        startActivity(recycle);
        }
        }
