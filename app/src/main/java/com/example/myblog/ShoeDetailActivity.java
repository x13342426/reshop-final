package com.example.myblog;


import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myblog.Adapters.CommentAdapter;
import com.example.myblog.Models.Comment;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class
ShoeDetailActivity extends AppCompatActivity {

    ImageView imgshoe,imgUsershoe,imgCurrentUser;
    TextView txtshoeDesc,txtshoeDateName,txtshoeTitle;
    EditText editTextComment;
    Button btnAddComment;
    String shoeKey;
    FirebaseAuth firebaseAuth;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    CommentAdapter commentAdapter;
    RecyclerView RvComment;
    List<Comment> listComment;
    static String COMMENT_KEY = "Comment" ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoe_detail);


        // let's set the statue bar to transparent
        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getSupportActionBar().hide();

        // ini Views
        RvComment = findViewById(R.id.rv_comment);
        imgshoe =findViewById(R.id.shoe_detail_img);
        imgUsershoe = findViewById(R.id.shoe_detail_user_img);
        imgCurrentUser = findViewById(R.id.shoe_detail_currentuser_img);

        txtshoeTitle = findViewById(R.id.shoe_detail_title);
        txtshoeDesc = findViewById(R.id.shoe_detail_desc);
        txtshoeDateName = findViewById(R.id.shoe_detail_date_name);

        editTextComment = findViewById(R.id.shoe_detail_comment);
        btnAddComment = findViewById(R.id.shoe_detail_add_comment_btn);


        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        firebaseDatabase = FirebaseDatabase.getInstance();


        // add Comment button click listner

        btnAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                btnAddComment.setVisibility(View.INVISIBLE);
                DatabaseReference commentReference = firebaseDatabase.getReference(COMMENT_KEY).child(shoeKey).push();
                String comment_content = editTextComment.getText().toString();
                String uid = firebaseUser.getUid();
                String uname = firebaseUser.getDisplayName();
                String uimg = firebaseUser.getPhotoUrl().toString();
                Comment comment = new Comment(comment_content,uid,uimg,uname);

                commentReference.setValue(comment).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        showMessage("comment added");
                        editTextComment.setText("");
                        btnAddComment.setVisibility(View.VISIBLE);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        showMessage("fail to add comment : "+e.getMessage());
                    }
                });



            }
        });


        // now we need to bind all data into those views
        // firt we need to get shoe data
        // we need to send shoe detail data to this activity first ...
        // now we can get shoe data

        String shoeImage = getIntent().getExtras().getString("shoeImage") ;
        Glide.with(this).load(shoeImage).into(imgshoe);

        String shoeTitle = getIntent().getExtras().getString("title");
        txtshoeTitle.setText(shoeTitle);

        String usershoeImage = getIntent().getExtras().getString("userPhoto");
        Glide.with(this).load(usershoeImage).into(imgUsershoe);

        String shoeDescription = getIntent().getExtras().getString("description");
        txtshoeDesc.setText(shoeDescription);

        // setcomment user image

        Glide.with(this).load(firebaseUser.getPhotoUrl()).into(imgCurrentUser);
        // get shoe id
        shoeKey = getIntent().getExtras().getString("shoeKey");

        String date = timestampToString(getIntent().getExtras().getLong("shoeDate"));
        txtshoeDateName.setText(date);


        // ini Recyclerview Comment
        iniRvComment();


    }

    private void iniRvComment() {

        RvComment.setLayoutManager(new LinearLayoutManager(this));

        DatabaseReference commentRef = firebaseDatabase.getReference(COMMENT_KEY).child(shoeKey);
        commentRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listComment = new ArrayList<>();
                for (DataSnapshot snap:dataSnapshot.getChildren()) {

                    Comment comment = snap.getValue(Comment.class);
                    listComment.add(comment) ;

                }

                commentAdapter = new CommentAdapter(getApplicationContext(),listComment);
                RvComment.setAdapter(commentAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }

    private void showMessage(String message) {

        Toast.makeText(this,message,Toast.LENGTH_LONG).show();

    }


    private String timestampToString(long time) {

        Calendar calendar = Calendar.getInstance(Locale.ENGLISH);
        calendar.setTimeInMillis(time);
        String date = DateFormat.format("dd-MM-yyyy",calendar).toString();
        return date;


    }


}
