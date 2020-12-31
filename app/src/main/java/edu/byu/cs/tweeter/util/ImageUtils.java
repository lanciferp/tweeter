package edu.byu.cs.tweeter.util;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.io.IOException;

import edu.byu.cs.tweeter.model.domain.User;
import edu.byu.cs.tweeter.model.net.TweeterImageException;


/**
 * Contains utility methods for working with Android images.
 */
public class ImageUtils {

    /**
     * Creates a drawable from the bytes read from an image file.
     *
     * @param bytes the bytes.
     * @return the drawable.
     */
    public static Drawable drawableFromByteArray(byte [] bytes) {
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        return new BitmapDrawable(Resources.getSystem(), bitmap);
    }


    /**
     *
     * Loads the image from the user's url
     *
     * Pre: non null ImageURL, unloaded imageByte array
     * Post: Loaded image array
     *
     * @param user
     * @throws TweeterImageException
     */
    public static void loadImage(User user) throws TweeterImageException {
        if(user.getImageUrl() == null)
            throw new TweeterImageException(user.getAlias() + " has Null ImageURL", "Image", null);
        if(user.getImageBytes() != null)
            return; // image was already loaded, no need to do it twice

        try {
            byte [] bytes = ByteArrayUtils.bytesFromUrl(user.getImageUrl());
            user.setImageBytes(bytes);
        } catch (IOException e) {
            throw new TweeterImageException(user.getAlias() + " has invalid ImageURL", "Image", null);
        }
    }

    /**
     * Forces a reload of the user's imaage
     *
     * Pre: non null ImageURL
     * Post: Loaded image array
     *
     * @param user
     * @throws TweeterImageException
     */
    public void reloadImage(User user) throws TweeterImageException {
        if(user.getImageUrl() == null)
            throw new TweeterImageException(user.getAlias() + " has Null ImageURL", "Image", null);

        try {
            byte [] bytes = ByteArrayUtils.bytesFromUrl(user.getImageUrl());
            user.setImageBytes(bytes);
        } catch (IOException e) {
            Log.e(this.getClass().getName(), e.toString(), e);
        }
    }

}
