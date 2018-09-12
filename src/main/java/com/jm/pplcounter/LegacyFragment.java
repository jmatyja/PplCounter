package com.jm.pplcounter;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jm.mushroomsfinder.R;

public class LegacyFragment extends Fragment {
    private LegacyCameraConnection connection;

    public LegacyFragment( LegacyCameraConnection connection) {
        this.connection = connection;
    }
    @Override
    public View onCreateView(
            final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        return inflater.inflate(connection.layout, container, false);
    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        connection.textureView = (AutoFitTextureView) view.findViewById(R.id.texture);
    }

    @Override
    public void onActivityCreated(final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        connection.startBackgroundThread();
        // When the screen is turned off and turned back on, the SurfaceTexture is already
        // available, and "onSurfaceTextureAvailable" will not be called. In that case, we can open
        // a camera and start preview from here (otherwise, we wait until the surface is ready in
        // the SurfaceTextureListener).

        if (connection.textureView.isAvailable()) {
            connection.camera.startPreview();
        } else {
            connection.textureView.setSurfaceTextureListener(connection.surfaceTextureListener);
        }
    }

    @Override
    public void onPause() {
        connection.stopCamera();
        connection.stopBackgroundThread();
        super.onPause();
    }
}
