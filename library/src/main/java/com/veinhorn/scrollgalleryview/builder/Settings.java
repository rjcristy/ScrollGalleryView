package com.veinhorn.scrollgalleryview.builder;

import com.veinhorn.scrollgalleryview.ScrollGalleryViewException;

/**
 * Created by veinhorn on 1/27/18.
 */

public class Settings implements Cloneable {
    private Integer thumbnailSize;
    private boolean zoom;

    private Settings() {

    }

    public int getThumbnailSize() {
        return thumbnailSize;
    }

    public boolean isZoom() {
        return zoom;
    }

    public static Builder newBuilder() {
        return new Settings().new Builder();
    }

    public class Builder {
        private Builder() {

        }

        public Builder thumbnailSize(int thumbnailSize) {
            Settings.this.thumbnailSize = thumbnailSize;
            return this;
        }

        public Builder zoom(boolean isZoom) {
            Settings.this.zoom = zoom;
            return this;
        }

        public Settings build() throws ScrollGalleryViewException {
            if (thumbnailSize == null) {
                throw new ScrollGalleryViewException("You should specify thumbnailSize");
            }

            try {
                return (Settings) Settings.this.clone();
            } catch (CloneNotSupportedException e) {
                throw new ScrollGalleryViewException("Could not build a Settings object");
            }
        }
    }
}
