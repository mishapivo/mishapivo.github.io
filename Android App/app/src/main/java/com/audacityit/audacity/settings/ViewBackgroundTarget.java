package com.audacityit.audacity.settings;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.ViewTarget;

/**
 * Created by sourav_aits on 10/24/16.
 */

public abstract class ViewBackgroundTarget<Z> extends ViewTarget<View, Z> implements GlideAnimation.ViewAdapter {
    public ViewBackgroundTarget(View view) { super(view); }
    @Override public void onLoadCleared(Drawable placeholder) { setBackground(placeholder); }
    @Override public void onLoadStarted(Drawable placeholder) { setBackground(placeholder); }
    @Override public void onLoadFailed(Exception e, Drawable errorDrawable) { setBackground(errorDrawable); }
    @Override public void onResourceReady(Z resource, GlideAnimation<? super Z> glideAnimation) {
        if (glideAnimation == null || !glideAnimation.animate(resource, this)) {
            setResource(resource);
        }
    }
    @Override public void setDrawable(Drawable drawable) { setBackground(drawable); }
    @Override public Drawable getCurrentDrawable() { return view.getBackground(); }

    @SuppressWarnings("deprecation")
    protected void setBackground(Drawable drawable) {
        if (Build.VERSION.SDK_INT > 16) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    protected abstract void setResource(Z resource);



    public class BitmapViewBackgroundTarget extends ViewBackgroundTarget<Bitmap> {
        public BitmapViewBackgroundTarget(View view) { super(view); }
        @Override protected void setResource(Bitmap resource) {
            setBackground(new BitmapDrawable(view.getResources(), resource));
        }
    }


    public class DrawableViewBackgroundTarget extends ViewBackgroundTarget<Drawable> {
        public DrawableViewBackgroundTarget(View view) { super(view); }
        @Override protected void setResource(Drawable resource) { setBackground(resource); }
    }


    public static class GlideDrawableViewBackgroundTarget extends ViewBackgroundTarget<GlideDrawable> {
        private int maxLoopCount;
        private GlideDrawable resource;
        public GlideDrawableViewBackgroundTarget(View view) { this(view, GlideDrawable.LOOP_FOREVER); }
        public GlideDrawableViewBackgroundTarget(ImageView view) { this(view, GlideDrawable.LOOP_FOREVER); }
        public GlideDrawableViewBackgroundTarget(ImageView view, int maxLoopCount) {
            super(view);
            this.maxLoopCount = maxLoopCount;
        }

        public GlideDrawableViewBackgroundTarget(View view, int maxLoopCount) {
            super(view);
            this.maxLoopCount = maxLoopCount;
        }

        @Override public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
            super.onResourceReady(resource, animation);
            this.resource = resource;
            resource.setLoopCount(maxLoopCount);
            resource.start();
        }

        @Override protected void setResource(GlideDrawable resource) { setBackground(resource); }
        @Override public void onStart() { if (resource != null) { resource.start(); } }
        @Override public void onStop() { if (resource != null) { resource.stop(); } }
    }

}