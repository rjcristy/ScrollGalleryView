package com.veinhorn.scrollgalleryview.builder;

import android.support.v4.app.FragmentActivity;

import com.veinhorn.scrollgalleryview.MediaInfo;
import com.veinhorn.scrollgalleryview.ScrollGalleryView;
import com.veinhorn.scrollgalleryview.ScrollGalleryViewException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by veinhorn on 1/24/18.
 * Used as separate layout just to help build. Mostly helps to init gallery for the first time.
 * Mechanism:
 * TODO: Pass data -> Validate data -> Build gallery
 * TODO: Provide image loaders as separate dependencies
 */

public class GalleryBuilder {

    public static GalleryBuilderImpl from(FragmentActivity activity, int viewId) {
        return new GalleryBuilderImpl(activity, viewId);
    }

    public static class GalleryBuilderImpl {
        private FragmentActivity activity;
        private int viewId;

        private List<MediaInfo> mediaInfos;
        private Settings settings;

        public GalleryBuilderImpl(FragmentActivity activity, int viewId) {
            this.activity = activity;
            this.viewId = viewId;

            mediaInfos = new ArrayList<>();
        }

        public GalleryBuilderImpl withMedia(MediaInfo mediaInfo) {
            if (mediaInfo != null) mediaInfos.add(mediaInfo);
            return this;
        }

        public GalleryBuilderImpl settings(Settings settings) {
            this.settings = settings;
            return this;
        }

        public ScrollGalleryView build() throws ScrollGalleryViewException {
            ScrollGalleryView gallery = (ScrollGalleryView) activity.findViewById(viewId);

            if (settings == null) {
                throw new ScrollGalleryViewException("You should specify settings for your gallery");
            }

            // Applying settings
            gallery.setThumbnailSize(settings.getThumbnailSize());
            gallery.setZoom(settings.isZoom());
            gallery.setFragmentManager(activity.getSupportFragmentManager());

            // Add medias after all
            for (MediaInfo mediaInfo : mediaInfos) {
                gallery.addMedia(mediaInfo);
            }

            return gallery;
        }
    }
}
