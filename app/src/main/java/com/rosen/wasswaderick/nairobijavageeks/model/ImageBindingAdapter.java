package com.rosen.wasswaderick.nairobijavageeks.model;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.rosen.wasswaderick.nairobijavageeks.R;
import com.rosen.wasswaderick.nairobijavageeks.adapter.GlideApp;

/**
 * Created by Derick W on 13,February,2019
 * Github: @wasswa-derick
 * Andela (Kampala, Uganda)
 */
public class ImageBindingAdapter {

    @BindingAdapter({"bind:imageUrl"})
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        GlideApp
                .with(context)
                .asBitmap()
                .load(url)
                .placeholder(R.drawable.cat)
                .centerCrop()
                .into(imageView);
    }

}
