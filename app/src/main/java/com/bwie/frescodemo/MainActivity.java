package com.bwie.frescodemo;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.AbstractDraweeController;
import com.facebook.drawee.generic.RoundingParams;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.icon_radius)
    Button iconRadius;
    @BindView(R.id.icon_circle)
    Button iconCircle;
    @BindView(R.id.icon_bili)
    Button iconBili;
    @BindView(R.id.icon_jianjin)
    Button iconJianjin;
    @BindView(R.id.icon_cipan)
    Button iconCipan;
    @BindView(R.id.icon_gif)
    Button iconGif;
    @BindView(R.id.icon_listener)
    Button iconListener;
    @BindView(R.id.icon_okhttp)
    Button iconOkhttp;
    @BindView(R.id.simple_view)
    SimpleDraweeView simpleView;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        uri = Uri.parse("http://g.hiphotos.baidu.com/image/pic/item/6d81800a19d8bc3e770bd00d868ba61ea9d345f2.jpg");
        simpleView.setImageURI(uri);

    }

    @OnClick({R.id.icon_radius, R.id.icon_circle, R.id.icon_bili, R.id.icon_jianjin, R.id.icon_cipan, R.id.icon_gif, R.id.icon_listener, R.id.icon_okhttp})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.icon_radius:
                RoundingParams radiusRoundingParams = RoundingParams.fromCornersRadius(5f);
                radiusRoundingParams.setRoundAsCircle(false);
                radiusRoundingParams.setCornersRadius(20f);
                simpleView.getHierarchy().setRoundingParams(radiusRoundingParams);
                break;
            case R.id.icon_circle:
                RoundingParams circleRoundingParams = RoundingParams.fromCornersRadius(5f);
                circleRoundingParams.setRoundAsCircle(true);
                simpleView.getHierarchy().setRoundingParams(circleRoundingParams);
                break;
            case R.id.icon_bili:
                simpleView.setAspectRatio(1.2f);
                break;
            case R.id.icon_jianjin:
                ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                        .setProgressiveRenderingEnabled(true)
                        .build();
                AbstractDraweeController draweeController = Fresco.newDraweeControllerBuilder()
                        .setImageRequest(request)
                        .setOldController(simpleView.getController())
                        .build();
                simpleView.setController(draweeController);
                break;
            case R.id.icon_cipan:
                DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this)
                        .setBaseDirectoryName("img_icon")
                        .setBaseDirectoryPath(Environment.getDataDirectory())
                        .build();
                ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                        .setMainDiskCacheConfig(diskCacheConfig)
                        .build();
                Fresco.initialize(this,config);

                break;
            case R.id.icon_gif:
                AbstractDraweeController build = Fresco.newDraweeControllerBuilder()
                        .setAutoPlayAnimations(true)
                        .setUri(Uri.parse("http://img.soogif.com/wKPS4WyAImkZ692DUvTsodBXfuKJs6er.gif"))
                        .build();
                simpleView.setController(build);
                break;
            case R.id.icon_listener:
                break;
            case R.id.icon_okhttp:
                break;
        }
    }
}
