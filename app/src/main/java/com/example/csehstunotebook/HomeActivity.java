package com.example.csehstunotebook;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.csehstunotebook.Prevalent.Prevalent;
import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    NavigationView navigationView;
    CardView l1s1,l1s2,l2s1,l2s2,l3s1,l3s2,l4s1,l4s2,extra,coment;
    CardView student,teacher,classes,noticeboard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        l1s1=(CardView)findViewById(R.id.cv1);
        l1s2=(CardView)findViewById(R.id.cv2);
        l2s1=(CardView)findViewById(R.id.cv3);
        l2s2=(CardView)findViewById(R.id.cv4);
        l3s1=(CardView)findViewById(R.id.cv5);
        l3s2=(CardView)findViewById(R.id.cv6);
        l4s1=(CardView)findViewById(R.id.cv7);
        l4s2=(CardView)findViewById(R.id.cv8);
        extra=(CardView)findViewById(R.id.cv9);
        coment=(CardView)findViewById(R.id.cv10);

     /*   l1s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,LevelCourses.class);
                intent.putExtra("ls","l1s1");
                startActivity(intent);

            }
        });

*/

        l1s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,Level1Sem1.class);
                startActivity(intent);

            }
        });

        l1s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,Level1Sem2.class);
                startActivity(intent);

            }
        });

        l2s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,Level2Sem1.class);
                startActivity(intent);

            }
        });

        l2s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,Level2Sem2.class);
                startActivity(intent);

            }
        });


        l3s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,Level3Sem1.class);
                startActivity(intent);

            }
        });


        l3s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,Level3Sem2.class);
                startActivity(intent);

            }
        });

        l4s1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,Level4Sem1.class);
                startActivity(intent);

            }
        });

        l4s2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,Level4Sem2.class);

                startActivity(intent);

            }
        });

        extra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,VisitingSite.class);
                intent.putExtra("ls","l3s2");
                intent.putExtra("course","Extra");
                startActivity(intent);

            }
        });

        coment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,CuriculumActivity.class);
                intent.putExtra("url","https://drive.google.com/drive/folders/17BtNytu1VWjMk-g92R1qQxTxYV4l6F3l?usp=sharing");
                startActivity(intent);

            }
        });


        //  student=(CardView)findViewById(R.id.cv1);
     //   teacher=(CardView)findViewById(R.id.cv2);
        // classes=(CardView)findViewById(R.id.cv3);
     //   noticeboard=(CardView)findViewById(R.id.cv4);

/*
        noticeboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, AddNote.class);
                startActivity(intent);

            }
        });


        teacher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, AddNote.class);
                startActivity(intent);

            }
        });

        student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, AddNote.class);
                startActivity(intent);

            }
        });


*/



        drawerLayout=(DrawerLayout)findViewById(R.id.nav_view_ki_jani);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        navigationView=(NavigationView)findViewById(R.id.navigation_view);
        //  FloatingActionButton fab=(FloatingActionButton)findViewById(R.id.fab);

        toolbar.setTitle("Home");

        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "This is main activity", Snackbar.LENGTH_LONG)
                        .setAction("CLOSE",null)
                        .show();
            }
        });*/

        navigationView.setNavigationItemSelectedListener(this);

        View headerView=navigationView.getHeaderView(0);
        TextView userNameTextView=headerView.findViewById(R.id.user_profile_name);
        CircleImageView profileImageView=headerView.findViewById(R.id.user_profile_image);

        userNameTextView.setText(Prevalent.currentOnlineUser.getName());

        Picasso.get().load(Prevalent.currentOnlineUser.getImage()).placeholder(R.drawable.profile).into(profileImageView);

        ActionBarDrawerToggle toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();



    }


    @Override
    public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {

        int menuId=item.getItemId();


        if(menuId==R.id.nav_logout)
        {
            //  Toast.makeText(HomeActivity.this, "Logout pressed", Toast.LENGTH_SHORT).show();
            // Paper.book().destroy();
            Intent intent=new Intent(HomeActivity.this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            finish();
        }
        else if(menuId==R.id.nav_setting)
        {
             Toast.makeText(HomeActivity.this, "It will add in future", Toast.LENGTH_SHORT).show();

//            Intent intent=new Intent(HomeActivity.this,AddNote.class);
//            startActivity(intent);

        }
        else if(menuId==R.id.nav_all)
        {
             Toast.makeText(HomeActivity.this, "All note activity will add later", Toast.LENGTH_SHORT).show();
           // Intent intent=new Intent(HomeActivity.this, AddNote.class);
           // startActivity(intent);
        }
        else if(menuId==R.id.nav_questions)
        {
             Toast.makeText(HomeActivity.this, "Nav all teacher Pressed", Toast.LENGTH_SHORT).show();

          //  Intent intent=new Intent(HomeActivity.this, AddNote.class);
          //  startActivity(intent);


        }
        else if(menuId==R.id.catagories)
        {
             Toast.makeText(HomeActivity.this, "Notice will add later.", Toast.LENGTH_SHORT).show();
           // Intent intent=new Intent(HomeActivity.this, AddNote.class);
           // startActivity(intent);
        }
        else if(menuId==R.id.nav_about_us)
        {
            // Toast.makeText(HomeActivity.this, "about us pressed, our info will show", Toast.LENGTH_SHORT).show();

            Intent intent=new Intent(HomeActivity.this,AboutUs.class);
            startActivity(intent);
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {


        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
            super.onBackPressed();
    }

}