package veinhorn.com.example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.veinhorn.scrollgalleryview.MediaInfo;
import com.veinhorn.scrollgalleryview.ScrollGalleryView;
import com.veinhorn.scrollgalleryview.ScrollGalleryViewException;
import com.veinhorn.scrollgalleryview.builder.GalleryBuilder;
import com.veinhorn.scrollgalleryview.builder.Settings;
import com.veinhorn.scrollgalleryview.loader.DefaultImageLoader;

public class MainActivity extends AppCompatActivity {

    private ScrollGalleryView galleryView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            galleryView = GalleryBuilder.from(this, R.id.scroll_gallery_view)
                    .withMedia(MediaInfo.mediaLoader(new DefaultImageLoader(R.drawable.wallpaper1)))
                    .withMedia(MediaInfo.mediaLoader(new DefaultImageLoader(R.drawable.wallpaper2)))
                    .settings(Settings
                            .newBuilder()
                            .thumbnailSize(100)
                            .zoom(true)
                            .build())
                    .build();
        } catch (ScrollGalleryViewException e) {
            Log.e(getClass().getName(), "Cannot initialize ScrollGalleryView", e);
            Toast.makeText(this, "Cannot initialize ScrollGalleryView", Toast.LENGTH_SHORT).show();
        }
    }
}
