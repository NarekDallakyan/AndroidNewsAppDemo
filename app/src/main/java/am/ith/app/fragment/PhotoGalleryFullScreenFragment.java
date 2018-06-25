package am.ith.app.fragment;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import am.ith.app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PhotoGalleryFullScreenFragment extends Fragment {

    private View view;
    private Context context;
    private ImageView fullScreenImage;
    public String fullScreenImageUrl="";

    public void setFullScreenImageUrl(String fullScreenImageUrl) {
        this.fullScreenImageUrl = fullScreenImageUrl;
    }

    public PhotoGalleryFullScreenFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_gallery_full_screen, container, false);
        context=getContext();
        findView();
        fullScreenImageAnimation(fullScreenImage);

        if (fullScreenImageUrl.length()>0) {
            setImageWithGilde(fullScreenImageUrl);
        }else {
            Toast.makeText(context, "Sorry!!! Image Not Found", Toast.LENGTH_SHORT).show();
        }
        return view;
    }
    private void findView(){
        fullScreenImage=view.findViewById(R.id.fullScreenImageID);
    }
    private void setImageWithGilde(String model_Url) {
        Glide
                .with(context)
                .load(model_Url)
                .into(fullScreenImage);
    }
    private void fullScreenImageAnimation(View view){
        Animation xmlAnimationSample;
        xmlAnimationSample = AnimationUtils.loadAnimation(context,R.anim.slide_out_down);
        view.startAnimation(xmlAnimationSample);
    }

}
