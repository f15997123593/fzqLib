package com.fzq.cameralib.listener;

import android.graphics.Bitmap;

/**
 * =====================================
 * 版    本：1.1.4
 * 描    述：
 * =====================================
 */
public interface JCameraListener {

    void captureSuccess(Bitmap bitmap);

    void recordSuccess(String url, Bitmap firstFrame);

}
