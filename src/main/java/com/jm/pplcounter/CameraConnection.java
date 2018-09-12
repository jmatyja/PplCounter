package com.jm.pplcounter;

import android.app.Fragment;
import android.hardware.Camera;

interface CameraConnection {
    Fragment getFragment();

    void takePicture(Camera.PictureCallback callback);
}
