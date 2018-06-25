package am.ith.app.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import am.ith.app.R;
import am.ith.app.fragment.PhotoGalleryFullScreenFragment;
import am.ith.app.web_serviece.model.AppResponse;

public class GalleryRecycleViewAdapter extends RecyclerView.Adapter<GalleryRecycleViewAdapter.GalleryViewHolder>{
    private AppResponse.Metadata metadata;
    private Context context;

    public GalleryRecycleViewAdapter(AppResponse.Metadata metadata, Context context) {
        this.metadata = metadata;
        this.context = context;
    }

    @NonNull
    @Override
    public GalleryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.gallery_item,parent,false);
        return new GalleryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GalleryViewHolder holder, final int position) {
        Glide
                .with(context)
                .load(metadata.getGallery().get(position).getThumbnailUrl())
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.galleryProgressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.galleryProgressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.galleryThumbnail);
         holder.titleThumbnail.setText(metadata.getGallery().get(position).getTitle());
         holder.galleryThumbnail.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 //send image Content URL   on PhotoGalleryFullScreenFragment
                 PhotoGalleryFullScreenFragment photoGalleryFullScreenFragment=new PhotoGalleryFullScreenFragment();
                 photoGalleryFullScreenFragment.setFullScreenImageUrl(metadata.getGallery().get(position).getContentUrl());

                 // go to Full Screen Fragment
                 AppCompatActivity activity=(AppCompatActivity)view.getContext();
                 activity
                         .getSupportFragmentManager()
                         .beginTransaction()
                         .addToBackStack("")
                         .replace(R.id.main_Container,photoGalleryFullScreenFragment)
                         .commit();

             }
         });
    }

    @Override
    public int getItemCount() {
        return metadata.getGallery().size();
    }

    public class GalleryViewHolder extends RecyclerView.ViewHolder{
        ImageView galleryThumbnail;
        TextView  titleThumbnail;
        ProgressBar galleryProgressBar;
        public GalleryViewHolder(View itemView) {
            super(itemView);
            galleryThumbnail=itemView.findViewById(R.id.galleryItemID);
            galleryProgressBar=itemView.findViewById(R.id.galleryProgressBarID);
            titleThumbnail=itemView.findViewById(R.id.galleryItemTitleID);
        }
    }
}
