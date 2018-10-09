package com.shreerai.digitalcard.SignUp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.oob.SignUp;
import com.google.android.gms.signin.SignIn;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.shreerai.digitalcard.R;
import com.shreerai.digitalcard.SignInActivity;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import static com.shreerai.digitalcard.SignUp.CardSelectionActivity.cardValue_V;
import static com.shreerai.digitalcard.SignUp.CardSelectionActivity.designer_V;
import static com.shreerai.digitalcard.SignUp.DetailActivity.Company_V;
import static com.shreerai.digitalcard.SignUp.DetailActivity.Position_V;
import static com.shreerai.digitalcard.SignUp.EmailActivity.Email_V;
import static com.shreerai.digitalcard.SignUp.UsernameActivity.FirstName_V;
import static com.shreerai.digitalcard.SignUp.UsernameActivity.LastName_V;

public class PasswordActivity extends AppCompatActivity {

    Button save_v;
    EditText password_v;
    String password_V;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    ProgressBar progressBar_v;
    private StorageReference mImageStorageReference;
    public static String uid_v;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        mAuth = FirebaseAuth.getInstance();
        mImageStorageReference = FirebaseStorage.getInstance().getReference();
        init();
        save_v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar_v.setVisibility(View.VISIBLE);
                password_V = password_v.getText().toString();
                if (password_V.isEmpty()) {
                    password_v.setError("Password required");
                    password_v.requestFocus();
                    return;
                }
                mAuth.createUserWithEmailAndPassword(Email_V, password_V)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUser current_user_v = FirebaseAuth.getInstance().getCurrentUser();
                                    uid_v = current_user_v.getUid();
                                    mDatabase = FirebaseDatabase.getInstance().getReference().child("Users").child(uid_v);
                                    HashMap<String, String> userMap = new HashMap<>();
                                    userMap.put("image", String.valueOf(cardValue_V));
                                    userMap.put("firstname", FirstName_V);
                                    userMap.put("lastname", LastName_V);
                                    userMap.put("position", Position_V);
                                    userMap.put("company", Company_V);
                                    userMap.put("id", uid_v);
                                    mDatabase.setValue(userMap);
                                    progressBar_v.setVisibility(View.GONE);
                                    Toast.makeText(getApplicationContext(), "Registration Sucessful", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(PasswordActivity.this, SignInActivity.class));
                                } else {
                                    progressBar_v.setVisibility(View.GONE);
                                    Toast.makeText(getApplicationContext(), "Registration Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });
//        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//        designer_V.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
//        data = byteArrayOutputStream.toByteArray();


    }

    void init() {
        save_v = findViewById(R.id.save_Signup);
        password_v = findViewById(R.id.input_password);
        progressBar_v = findViewById(R.id.signUp_progress);
        progressBar_v.setVisibility(View.GONE);
    }

//    boolean uploadImage(String imageId) {
//        filePath = mImageStorageReference.child("DigitalCard").child(imageId + ".jpg");
//        filePath.putBytes(data).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
//                if (task.isSuccessful()) {
//                    Toast.makeText(getApplicationContext(), "Upload Image Sucessfull", Toast.LENGTH_SHORT).show();
//                    filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//                        @Override
//                        public void onSuccess(Uri uri) {
//                            ImageUrl_V = uri.toString();
//                        }
//                    });
//                } else {
//                    Toast.makeText(getApplicationContext(), "Upload Image Unsucessful", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//        return true;
//
//    }


}
