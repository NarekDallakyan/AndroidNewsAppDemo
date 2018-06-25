package am.ith.app.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.Random;

import am.ith.app.AppAplication;
import am.ith.app.db_engine.Engine_Singleton;
import am.ith.app.db_engine.Model;
import am.ith.app.adapter.GeneralRecycleViewAdapter;
import am.ith.app.R;
import am.ith.app.web_serviece.model.AppResponse;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {
    private AppResponse list;
    private RecyclerView recyclerView;
    private GeneralRecycleViewAdapter adapter;
    private GridLayoutManager gridLayoutManager;
    private Engine_Singleton engine_singleton;
    private RelativeLayout recycleLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        changeRandomColor(recycleLayout);
        AppAplication.appAplication.getNetworkService().getData().enqueue(new Callback<AppResponse>() {
            @Override
            public void onResponse(Call<AppResponse> call, retrofit2.Response<AppResponse> response) {
                if (response.body() != null) {
                    list= response.body();

                    Toast.makeText(MainActivity.this, "" + list.getMetadata().size(), Toast.LENGTH_SHORT).show();
                    gridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
                    LayoutAnimationController animationController= AnimationUtils.loadLayoutAnimation(MainActivity.this,R.anim.layout_animation_fall_down);
                    recyclerView.setLayoutAnimation(animationController);
                    recyclerView.setLayoutManager(gridLayoutManager);
                    adapter = new GeneralRecycleViewAdapter(list,getListFromDB(), MainActivity.this);
                    recyclerView.setAdapter(adapter);
                }
            }
            @Override
            public void onFailure(Call<AppResponse> call, Throwable t) {

            }

        });
    }

    private void findViews() {
        recyclerView = findViewById(R.id.generalRecycleViewID);
        recycleLayout=findViewById(R.id.recycleLayoutID);
    }
    private LinkedList<Model> getListFromDB(){
        engine_singleton=Engine_Singleton.getInstance();
        LinkedList<Model> modelListDB;
        modelListDB=new LinkedList<>();
        if (engine_singleton.getServices(this).getAllinform("").size()>0) {
            modelListDB.addAll(engine_singleton.getServices(this).getAllinform(""));
        }
        return modelListDB;
    }
    private void changeRandomColor(View view){
        int[] androidColors = getResources().getIntArray(R.array.androidcolors);
        int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
        view.setBackgroundColor(randomAndroidColor);
    }
}
