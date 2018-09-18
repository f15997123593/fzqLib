package org.devio.takephoto.uitl;

import android.net.Uri;
import android.os.Environment;

import org.devio.takephoto.app.TakePhoto;
import org.devio.takephoto.compress.CompressConfig;
import org.devio.takephoto.model.CropOptions;
import org.devio.takephoto.model.TakePhotoOptions;

import java.io.File;

/**
 * 基础的拍照工具类
 * 作者: Created by fzq on 2018/9/18 19:23
 * 邮箱: 15997123593@163.com
 */
public class TakePicUtil {

    public static void sampleTakePic(File file, TakePhoto takePhoto) {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Uri imageUri = Uri.fromFile(file);
        CompressConfig config=new CompressConfig.Builder()
                .setMaxSize(102400)
                .setMaxPixel(800)
                .create();
        //压缩大小
        takePhoto.onEnableCompress(config,false);
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
        builder.setWithOwnGallery(false);
        takePhoto.setTakePhotoOptions(builder.create());
        CropOptions.Builder builders = new CropOptions.Builder();
        builders.setOutputX(800).setOutputY(800); //拍照处理的照片大小
        builders.setWithOwnCrop(false);//使用相机自带的
        takePhoto.onPickFromCaptureWithCrop(imageUri, builders.create());
    }

    public static void sampleChoosePic(File file, TakePhoto takePhoto) {
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        Uri imageUri = Uri.fromFile(file);
        CompressConfig config=new CompressConfig.Builder()
                .setMaxSize(102400)
                .setMaxPixel(800)
                .create();
        //压缩大小
        takePhoto.onEnableCompress(config,false);
        TakePhotoOptions.Builder builder = new TakePhotoOptions.Builder();
        builder.setWithOwnGallery(true);
        takePhoto.setTakePhotoOptions(builder.create());
        CropOptions.Builder builders = new CropOptions.Builder();
        builders.setOutputX(800).setOutputY(800); //拍照处理的照片大小
        builders.setWithOwnCrop(true);//使用相机自带的
        takePhoto.onPickFromGalleryWithCrop(imageUri, builders.create());
    }

}
