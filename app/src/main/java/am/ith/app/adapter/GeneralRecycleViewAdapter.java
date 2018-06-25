package am.ith.app.adapter;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;


import java.util.Date;
import java.util.LinkedList;

import am.ith.app.R;
import am.ith.app.activity.DetailsActivity;
import am.ith.app.db_engine.Engine_Singleton;
import am.ith.app.db_engine.Model;
import am.ith.app.web_serviece.model.AppResponse;

public class GeneralRecycleViewAdapter extends RecyclerView.Adapter<GeneralRecycleViewAdapter.MyViewHolder> {

    private AppResponse list;
    private Context context;
    Engine_Singleton engine_singleton;
    LinkedList<Model> modelListDB;

    public GeneralRecycleViewAdapter(AppResponse list, LinkedList<Model> modelListDB, Context context) {
        this.list = list;
        this.context = context;
        this.modelListDB = modelListDB;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.general_item, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        engine_singleton = Engine_Singleton.getInstance();
        if (modelListDB.size() > 0) {
            for (int i = 0; i < modelListDB.size(); i++) {
                if (modelListDB.get(i).getPosition() == getItemViewType(position)) {
                    Log.i("modelID","  "+modelListDB.get(i).getPosition()+"   Position  "+position);
                    holder.cardView.setCardBackgroundColor(Color.RED);
                }
            }
        }

        Date date = new Date(list.getMetadata().get(position).getDate());
        holder.category.setText(list.getMetadata().get(position).getCategory());
        holder.title.setText(list.getMetadata().get(position).getTitle());
        holder.date.setText(date.toString());

        Glide
                .with(context)
                .load(list.getMetadata().get(position).getCoverPhotoUrl())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.coverPhotoUrl);
        int id = position;

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                engine_singleton = Engine_Singleton.getInstance();
                long myPosition = position;
                int count=0;
                Model model = new Model(myPosition, "true");
                Toast.makeText(context, ""+count, Toast.LENGTH_SHORT).show();

                    engine_singleton.getServices(context).save(model);

                Intent intent = new Intent(view.getContext(), DetailsActivity.class);
                Engine_Singleton.getInstance().setMetadata(list.getMetadata().get(position));
                view.getContext().startActivity(intent);

            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.getMetadata().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView title;
        TextView category;
        TextView date;
        ImageView coverPhotoUrl;
        ProgressBar progressBar;

        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardViewID);
            title = itemView.findViewById(R.id.titleID);
            category = itemView.findViewById(R.id.categoryID);
            date = itemView.findViewById(R.id.dateID);
            coverPhotoUrl = itemView.findViewById(R.id.coverPhotoUrlID);
            progressBar = itemView.findViewById(R.id.progressBarID);

        }
    }
}
