package com.veinhorn.scrollgalleryview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.github.chrisbanes.photoview.PhotoView;
import com.veinhorn.scrollgalleryview.loader.MediaLoader;

/**
 * Created by veinhorn on 29.8.15.
 */
public class ImageFragment extends Fragment {

    private MediaInfo mMediaInfo;

    private HackyViewPager viewPager;
    private PhotoView photoView;
    private ScrollGalleryView.OnImageClickListener onImageClickListener;
    private ImageView playButtonImageView;
    private ImageButton downloadImageButton;

    public void setOnImageClickListener(ScrollGalleryView.OnImageClickListener onImageClickListener) {
        this.onImageClickListener = onImageClickListener;
    }

    public void setMediaInfo(MediaInfo mediaInfo) {
        mMediaInfo = mediaInfo;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = inflater.inflate(R.layout.image_fragment, container, false);
        photoView = rootView.findViewById(R.id.photoView);
        playButtonImageView = rootView.findViewById(R.id.play_button);
        downloadImageButton = rootView.findViewById(R.id.download_button);

        if (onImageClickListener != null) {
            photoView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onImageClickListener.onClick();
                }
            });
        }
        viewPager = (HackyViewPager) getActivity().findViewById(R.id.viewPager);

        if (savedInstanceState != null) {
            boolean isLocked = savedInstanceState.getBoolean(Constants.IS_LOCKED, false);
            viewPager.setLocked(isLocked);
        }

        loadImageToView();

        return rootView;
    }

    private void loadImageToView() {
        if (mMediaInfo != null) {
            mMediaInfo.getLoader().loadMedia(getActivity(), photoView, new MediaLoader.SuccessCallback() {
                @Override
                public void onSuccess() {}
            });

            downloadImageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getContext(), "adasds", Toast.LENGTH_LONG).show();
                    mMediaInfo.getLoader().onDownload();
                }
            });

            playButtonImageView.setVisibility( mMediaInfo.getLoader().isImage() ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        if (isViewPagerActive()) {
            outState.putBoolean(Constants.IS_LOCKED, viewPager.isLocked());
        }
        super.onSaveInstanceState(outState);
    }

    private boolean isViewPagerActive() {
        return viewPager != null;
    }

    private boolean isBackgroundImageActive() {
        return photoView != null && photoView.getDrawable() != null;
    }
}
