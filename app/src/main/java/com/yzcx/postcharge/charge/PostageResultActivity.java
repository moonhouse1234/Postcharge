package com.yzcx.postcharge.charge;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.yzcx.postcharge.R;


/**
 * @author 闫鹏飞
 *         create at 2018-3-12 0012
 *         description: 资费结果
 */
public class PostageResultActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView iv_back;
    private TextView tv_title;

    private Activity mActivity;

    /**
     * @author 闫鹏飞
     * @time 2018-3-12 0012  11:34
     * @describe 页面创建时执行此方法
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postage_result);

        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);

        tv_title.setText(getText(R.string.insured_result));
        iv_back.setOnClickListener(this);

        mActivity = this;
    }

    /**
     * @author 闫鹏飞
     * @time 2018-3-12 0012  11:33
     * @describe 页面点击事件
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }
}
