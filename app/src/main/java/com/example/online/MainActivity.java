package com.example.online;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.online.Adapter.ItemGridViewAdapter;
import com.example.online.Adapter.SliderAdapter;
import com.example.online.Model.ItemGridViewModel;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.smarteist.autoimageslider.SliderViewAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private int[] images;
    private SliderAdapter adapter;
    private SliderView sliderView;

    //grid view for images
    private RecyclerView recyclerView;
    private ItemGridViewAdapter itemGridViewAdapter;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference reference;
    private ArrayList<ItemGridViewModel> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.gridview_recyclerview);
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("data");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();


        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // list.clear();
                for (DataSnapshot dataSnapshot :snapshot.getChildren()){
                    ItemGridViewModel m=dataSnapshot.getValue(ItemGridViewModel.class);
            //        m.getImg();

                    list.add(m);
                }
                itemGridViewAdapter=new ItemGridViewAdapter(list,MainActivity.this);
                recyclerView.setAdapter(itemGridViewAdapter);


            }
//        reference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                for(DataSnapshot data:snapshot.getChildren()){
//                    ItemGridViewModel item=data.getValue(ItemGridViewModel.class);
//
//                }
//            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(MainActivity.this, "Error"+error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        //   FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });


        images = new int[]{R.drawable.banner1, R.drawable.banner2, R.drawable.banner3, R.drawable.banner4};
        sliderView = findViewById(R.id.imageSlider);

        adapter = new SliderAdapter(images);

        sliderView.setSliderAdapter(adapter);
        sliderView.setSliderTransformAnimation(SliderAnimations.ZOOMOUTTRANSFORMATION);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.SLIDE);
        sliderView.startAutoCycle();


        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);


        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(R.id.nav_home)
                .setDrawerLayout(drawer)
                .build();
        navigationView.bringToFront();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);


        //-----------------


    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}