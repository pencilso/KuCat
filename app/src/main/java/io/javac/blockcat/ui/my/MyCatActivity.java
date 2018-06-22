package io.javac.blockcat.ui.my;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.clans.fab.FloatingActionMenu;
import com.jaredrummler.android.widget.AnimatedSvgView;

import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.javac.blockcat.R;
import io.javac.blockcat.annotation.LayoutAnnotation;
import io.javac.blockcat.base.BaseActivity;
import io.javac.blockcat.base.BaseSubscriber;
import io.javac.blockcat.entity.CatEntity;
import io.javac.blockcat.svg.entity.SvgEntity;
import io.javac.blockcat.svg.svgEnum.SvgConfig;
import io.javac.blockcat.svg.utils.SvgUtils;
import io.javac.blockcat.ui.qr.QrCodeScannerActivity;
import io.javac.blockcat.ui.qr.QrInfoActivity;
import io.javac.blockcat.web3.EthApi;
import rx.Subscriber;

@LayoutAnnotation(layoutId = R.layout.activity_my_cat)
public class MyCatActivity extends BaseActivity {
    @BindView(R.id.act_my_cat_svgView)
    AnimatedSvgView actMyCatSvgView;
    @BindView(R.id.act_my_cat_CatId)
    TextView actMyCatCatId;
    @BindView(R.id.act_my_cat_birthTime)
    TextView actMyCatBirthTime;
    @BindView(R.id.act_my_cat_sireId)
    TextView actMyCatSireId;
    @BindView(R.id.act_my_cat_matronId)
    TextView actMyCatMatronId;
    @BindView(R.id.act_my_cat_generation)
    TextView actMyCatGeneration;
    @BindView(R.id.act_my_cat_menu)
    FloatingActionMenu actMyCatMenu;

    private int catId;

    public static void startActivity(Context context) {
        context.startActivity(new Intent(context, MyCatActivity.class));

    }

    @Override
    protected void initData() {
        super.initData();
        renderCat(null);
    }


    @Override
    protected void initEvent() {
        super.initEvent();
        loadCat();
    }

    private void loadCat() {
        loading();
        EthApi.instance().findUserCat(catId, new Subscriber<CatEntity>() {
            @Override
            public void onCompleted() {
                dismissLoading();
            }

            @Override
            public void onError(Throwable e) {
                dismissLoading();
                toast("加载失败，请尝试重试！");
                e.printStackTrace();
                catId --;
            }

            @SuppressLint("SetTextI18n")
            @Override
            public void onNext(CatEntity catEntity) {
                catId = catEntity.getId().intValue();

                if (actMyCatSvgView.getVisibility() != View.VISIBLE)
                    actMyCatSvgView.setVisibility(View.VISIBLE);
                renderCat(catEntity.getGenes());

                actMyCatCatId.setText("它的Id:" + String.valueOf(catEntity.getId()));
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date(catEntity.getBirthTime().longValue() * 1000));
                StringBuffer sf = new StringBuffer();
                sf.append(calendar.get(Calendar.YEAR)).append("-").append(calendar.get(Calendar.MONTH)+1).append("-").append(calendar.get(Calendar.DAY_OF_MONTH)).append(" ").append(calendar.get(Calendar.HOUR)).append(":").append(calendar.get(Calendar.MINUTE)).append(":").append(calendar.get(Calendar.SECOND));
                actMyCatBirthTime.setText("它的出生日期:" + sf.toString());

                actMyCatSireId.setText("它的爸爸Id:" + catEntity.getSireId().longValue());
                actMyCatMatronId.setText("它的妈妈Id:" + catEntity.getMatronId().longValue());
                actMyCatGeneration.setText("它是第:" + catEntity.getGeneration().longValue() + " 代");

            }
        });
    }

    private void renderCat(String genes) {
        try {
            SvgEntity config = SvgUtils.config(getAssets().open("cat.svg"), SvgConfig.GLYPHS_DEFAULT);
            if (genes != null) {
                int[] ints = SvgUtils.genesColor(genes, config.getColors().length);
                config.setColors(ints);
            }
            actMyCatSvgView.setGlyphStrings(config.getGlyphs());
            actMyCatSvgView.setFillColors(config.getColors());
            actMyCatSvgView.setViewportSize(config.getWidth(), config.getWidth());
            actMyCatSvgView.setTraceResidueColor(0x32000000);
            actMyCatSvgView.setTraceColors(config.getColors());
            actMyCatSvgView.rebuildGlyphData();
            actMyCatSvgView.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null) return;
        String result = data.getStringExtra("result");
        if (requestCode == 1) {
            if (!WalletUtils.isValidAddress(result)) {
                toast("无效的地址");
                return;
            }
            loading();
            EthApi.instance().transferCat(result, Long.valueOf(catId), new BaseSubscriber() {
                @Override
                public void onCompleted(boolean state, TransactionReceipt transactionReceipt, Throwable throwable) {
                    dismissLoading();
                    toast(state ? "转让成功" : "转让失败");
                }
            });
        } else if (requestCode == 2) {
            loading();
            EthApi.instance().catMating(result, String.valueOf(catId), new BaseSubscriber() {
                @Override
                public void onCompleted(boolean state, TransactionReceipt transactionReceipt, Throwable throwable) {
                    dismissLoading();
                    toast(state ? "操作成功，新猫已在您的账户下!" : "操作失败，请尝试稍后重试！");
                }
            });
        }
    }



    @OnClick({R.id.act_my_cat_menu_nextCat, R.id.act_my_cat_menu_transfer, R.id.act_my_cat_menu_qrCode,R.id.act_my_cat_menu_mating})
    public void onViewClicked(View view) {
        actMyCatMenu.close(true);
        switch (view.getId()) {
            case R.id.act_my_cat_menu_nextCat:
                catId++;
                loadCat();
                break;
            case R.id.act_my_cat_menu_transfer:
                QrCodeScannerActivity.startActivity(this, 1);
                break;
            case R.id.act_my_cat_menu_qrCode:
                QrInfoActivity.startActivity(this, String.valueOf(catId));
                break;
            case R.id.act_my_cat_menu_mating:
                QrCodeScannerActivity.startActivity(this, 2);
                break;
            default:
                break;
        }
    }
}