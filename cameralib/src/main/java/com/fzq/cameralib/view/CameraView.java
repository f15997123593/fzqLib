package com.fzq.cameralib.view;

import android.graphics.Bitmap;

/**
 * =====================================
 * 版    本：1.1.4
 * 描    述：
 * =====================================
 */
public interface CameraView {
    void resetState(int type);

    void confirmState(int type);

    void showPicture(Bitmap bitmap, boolean isVertical);

    void playVideo(Bitmap firstFrame, String url);

    void stopVideo();

    void setTip(String tip);

    void startPreviewCallback();

    boolean handlerFoucs(float x, float y);
}
