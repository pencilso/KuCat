package io.javac.blockcat.ui.qr;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;
import io.javac.blockcat.R;
import io.javac.blockcat.annotation.LayoutAnnotation;
import io.javac.blockcat.base.BaseActivity;
import io.javac.blockcat.web3.EthApi;

@LayoutAnnotation(layoutId = R.layout.activity_user_info)
public class QrInfoActivity extends BaseActivity {
    @BindView(R.id.act_user_info_address)
    TextView actUserInfoAddress;
    @BindView(R.id.act_user_info_qrCode)
    ImageView actUserInfoQrCode;
    private String str;

    public static void startActivity(Context context, String str) {
        Intent intent = new Intent(context, QrInfoActivity.class);
        intent.putExtra("str", str);
        context.startActivity(intent);
    }

    @Override
    protected void initData() {
        super.initData();
        str = getIntent().getStringExtra("str");
        actUserInfoAddress.setText(str);
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        Bitmap bitmap = QRCodeEncoder.syncEncodeQRCode(str, 500);
        actUserInfoQrCode.setImageBitmap(bitmap);
    }

}
