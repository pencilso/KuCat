package io.javac.blockcat.base;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.javac.blockcat.R;
import io.javac.blockcat.annotation.LayoutAnnotation;

/**
 * Created by Pencilso on 2018/6/15/015.
 *
 * @author Pencilso
 */
public class BaseFragment extends Fragment{

    private Unbinder unbinder;
    private View rootView;
    private ZLoadingDialog dialog;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView==null){
            LayoutAnnotation annotation = this.getClass().getAnnotation(LayoutAnnotation.class);
            rootView = inflater.inflate(annotation.layoutId(), null);
            unbinder = ButterKnife.bind(this, rootView);
        }
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
    public void toast(Object object){
        Toast.makeText(getContext(),String.valueOf(object),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        initData();
        initEvent();
    }

    protected void initView(){}
    protected void initData(){}
    protected void initEvent(){}


    protected void loading() {
        dialog = new ZLoadingDialog(getActivity());
        dialog.setLoadingBuilder(Z_TYPE.MUSIC_PATH)//设置类型
                .setLoadingColor(Color.BLACK)//颜色
                .setHintText("Loading...")
                .setHintTextSize(16) // 设置字体大小 dp
                .setHintTextColor(Color.BLACK)  // 设置字体颜色
                .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                .setDialogBackgroundColor(Color.parseColor("#FFFFFF")) // 设置背景色，默认白色
                .setCancelable(false)
                .show();
    }

    protected void dismissLoading() {
        if (dialog != null) {
            dialog.dismiss();
            dialog = null;
        }
    }
}
