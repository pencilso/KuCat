package io.javac.blockcat.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.javac.blockcat.annotation.LayoutAnnotation;

/**
 * Created by Pencilso on 2018/6/15/015.
 *
 * @author Pencilso
 */
public class BaseActivity extends AppCompatActivity {

    private Unbinder unbinder;
    private ZLoadingDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LayoutAnnotation annotation = this.getClass().getAnnotation(LayoutAnnotation.class);
        setContentView(annotation.layoutId());
        unbinder = ButterKnife.bind(this);
        initView();
        initData();
        initEvent();
    }

    protected void initView() {
    }

    protected void initData() {
    }

    protected void initEvent() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null)
            unbinder.unbind();
    }

    protected void loading() {
        dialog = new ZLoadingDialog(this);
        dialog.setLoadingBuilder(Z_TYPE.MUSIC_PATH)//设置类型
                .setLoadingColor(Color.BLACK)//颜色
                .setHintText("Loading...")
                .setHintTextSize(16) // 设置字体大小 dp
                .setHintTextColor(Color.BLACK)  // 设置字体颜色
                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                .setDialogBackgroundColor(Color.parseColor("#FFFFFF")) // 设置背景色，默认白色
                .setCancelable(false)
                .setCanceledOnTouchOutside(false)
                .show();
    }

    protected void dismissLoading() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }

    public void toast(Object object) {
        Toast.makeText(this, String.valueOf(object), Toast.LENGTH_SHORT).show();
    }
}
