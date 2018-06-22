package io.javac.blockcat.ui;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.widget.Button;
import android.widget.EditText;

import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import io.javac.blockcat.R;
import io.javac.blockcat.annotation.LayoutAnnotation;
import io.javac.blockcat.base.BaseActivity;
import io.javac.blockcat.ui.home.HomeActivity;
import io.javac.blockcat.web3.EthApi;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.RuntimePermissions;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

@RuntimePermissions
@LayoutAnnotation(layoutId = R.layout.activity_main)
public class MainActivity extends BaseActivity {
    @BindView(R.id.fragment_create_wallet_pwd)
    EditText fragmentCreateWalletPwd;
    @BindView(R.id.afragment_create_wallet_load)
    Button confim;
    private boolean create;

    @OnClick(R.id.afragment_create_wallet_load)
    public void onViewClicked() {
        final String password = fragmentCreateWalletPwd.getText().toString().trim();
        if (password.length() == 0) {
            fragmentCreateWalletPwd.setError("输入密码");
            fragmentCreateWalletPwd.requestFocus();
            return;
        }

        loading();
        Observable.create(new Observable.OnSubscribe<Credentials>() {
            @Override
            public void call(Subscriber<? super Credentials> subscriber) {
                if (create) {
                    Credentials wallet = EthApi.instance().createWallet(password);
                    EthApi.instance().setCredentials(wallet);
                } else {
                    File[] walletFiles = EthApi.instance().getWalletFiles();
                    File walletFile = walletFiles[0];
                    Credentials credentials = EthApi.instance().loadCredentials(password, walletFile);
                    if (credentials != null) {
                        EthApi.instance().setCredentials(credentials);
                    }
                }
                String address = EthApi.instance().getCredentials().getAddress();
                android.util.Log.e("address", address);

                subscriber.onNext(EthApi.instance().getCredentials());
                subscriber.onCompleted();
            }
        })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Credentials>() {
                    @Override
                    public void call(Credentials credentials) {
                        dismissLoading();
                        HomeActivity.startActivity(MainActivity.this);
                        finish();
                    }
                });
    }

    @Override
    protected void initData() {
        super.initData();
        MainActivityPermissionsDispatcher.needsPermissionWithPermissionCheck(this);
    }


    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void needsPermission() {
        File[] walletFiles = EthApi.instance().getWalletFiles();
        if (walletFiles == null || walletFiles.length == 0) {
            create = true;
        } else {
            confim.setText("验证密码");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }
}
