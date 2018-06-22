package io.javac.blockcat.ui.home;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import org.web3j.protocol.core.methods.response.TransactionReceipt;

import butterknife.OnClick;
import io.javac.blockcat.R;
import io.javac.blockcat.annotation.LayoutAnnotation;
import io.javac.blockcat.base.BaseActivity;
import io.javac.blockcat.base.BaseSubscriber;
import io.javac.blockcat.ui.my.MyCatActivity;
import io.javac.blockcat.ui.qr.QrInfoActivity;
import io.javac.blockcat.web3.EthApi;

/**
 * @author Pencilso
 */
@LayoutAnnotation(layoutId = R.layout.activity_home)
public class HomeActivity extends BaseActivity {

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, HomeActivity.class));
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @OnClick({R.id.act_home_my_cat, R.id.act_home_my_Adopt, R.id.act_home_my_account, R.id.act_home_my_export})
    public void onClick(View view) {
        loading();
        switch (view.getId()) {
            //领养猫咪
            case R.id.act_home_my_Adopt:
                EthApi.instance().adoptCat(new BaseSubscriber() {
                    @Override
                    public void onCompleted(boolean state, TransactionReceipt transactionReceipt, Throwable throwable) {
                        dismissLoading();
                        if (throwable != null) {
                            throwable.printStackTrace();
                        }
                        android.util.Log.e("transactionReceipt", String.valueOf(transactionReceipt));
                        if (state) toast("领养成功");
                        else toast("领养失败");
                    }
                });
                break;
            case R.id.act_home_my_cat:
                dismissLoading();
                MyCatActivity.startActivity(this);
                break;
            case R.id.act_home_my_account:
                dismissLoading();
                QrInfoActivity.startActivity(this, EthApi.instance().getCredentials().getAddress());
                break;
            case R.id.act_home_my_export:
                dismissLoading();
                String privateKey = EthApi.instance().getCredentials().getEcKeyPair().getPrivateKey().toString(16);
                //获取剪贴板管理器：
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 创建普通字符型ClipData
                ClipData mClipData = ClipData.newPlainText("Label", "0x" + privateKey);
                // 将ClipData内容放到系统剪贴板里。
                cm.setPrimaryClip(mClipData);
                toast("已复制到粘贴板");
                break;
            default:
                break;
        }
    }
}
