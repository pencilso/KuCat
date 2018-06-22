package io.javac.blockcat.ui.qr;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;

import butterknife.BindView;
import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;
import io.javac.blockcat.R;
import io.javac.blockcat.annotation.LayoutAnnotation;
import io.javac.blockcat.base.BaseActivity;

@LayoutAnnotation(layoutId = R.layout.activity_transfer)
public class QrCodeScannerActivity extends BaseActivity implements QRCodeView.Delegate {
    @BindView(R.id.act_transfer_zxing)
    ZXingView actTransferZxing;

    public static void startActivity(Activity activity,int requestCode) {
        Intent intent = new Intent(activity, QrCodeScannerActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        actTransferZxing.startCamera();
        actTransferZxing.startSpot();
        actTransferZxing.setDelegate(this);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        Intent intent = new Intent();
        intent.putExtra("result", result);
        setResult(1, intent);
        finish();
    }

    @Override
    public void onScanQRCodeOpenCameraError() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (actTransferZxing != null) {
            actTransferZxing.stopSpot();
            actTransferZxing.stopCamera();
        }

    }
}
