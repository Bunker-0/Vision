package examples.vision;


import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CaptureRequest;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceView;

import java.lang.reflect.Array;
import java.util.Arrays;

public class CameraState extends CameraDevice.StateCallback {

    private SurfaceView targetView;


    public CameraState(SurfaceView targetView) {
        Log.d("msg","Previewing");
        this.targetView = targetView;
    }

    @Override
    public void onOpened(@NonNull CameraDevice cameraDevice) {
        Log.d("msg","onOpened");
        try {
            CaptureRequest.Builder captureRequestBuilder = cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW);
            captureRequestBuilder.addTarget(this.targetView.getHolder().getSurface());
            captureRequestBuilder.set(CaptureRequest.CONTROL_AE_EXPOSURE_COMPENSATION,12);
            //captureRequestBuilder.set(CaptureRequest.CONTROL_AE_MODE,CaptureRequest.CONTROL_AE_MODE_ON);
            cameraDevice.createCaptureSession(Arrays.asList(this.targetView.getHolder().getSurface()),
                    null,null);

        } catch (CameraAccessException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDisconnected(@NonNull CameraDevice cameraDevice) {
        Log.d("msg","onDisconnected");
    }

    @Override
    public void onError(@NonNull CameraDevice cameraDevice, int i) {
        Log.d("msg","onError");
    }
}
