package com.yzcx.postcharge.charge;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.alibaba.fastjson.JSONObject;
import com.yzcx.postcharge.R;
import com.yzcx.postcharge.constants.SpConstants;
import com.yzcx.postcharge.entity.product.Product;
import com.yzcx.postcharge.until.SPUtils;
import com.yzcx.postcharge.until.Sys;


/**
 * @author 闫鹏飞
 *         create at 2018-3-12 0012
 *         description: 资费查询
 */
public class PostageQueryActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView iv_back;
    private TextView tv_title;
    private TextView tv_calculation;
    private Activity mActivity;
    private RadioButton rb_type_one;//类型一
    private RadioButton rb_type_two;//类型二
    private EditText ed_real_weight;//实际重量
    private EditText ed_len;//长
    private EditText ed_width;//宽
    private  EditText ed_height;//高
    private  Product product;
    /**
     * @author 闫鹏飞
     * @time 2018-3-12 0012  11:34
     * @describe 页面创建时执行此方法
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postage_query);
        initView();
        initData();


    }

    private void initData() {
       String jsonProduct = SPUtils.getInstance().getString(SpConstants.PRODUCT_LIST);
        if(Sys.isNotNull(jsonProduct)){
            try {
                product = JSONObject.parseObject(jsonProduct,Product.class);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(product != null && product.getData() != null){
                rb_type_one.setText( product.getData().get(0).getName());
                rb_type_two.setText( product.getData().get(1).getName());
            }
        }
    }

    private void initView() {
        iv_back = (ImageView) findViewById(R.id.iv_back);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_calculation = (TextView) findViewById(R.id.tv_calculation);
        rb_type_one = (RadioButton) findViewById(R.id.rb_type_one);
        rb_type_two = (RadioButton) findViewById(R.id.rb_type_two);
        ed_real_weight = (EditText) findViewById(R.id.ed_real_weight);
        ed_len = (EditText) findViewById(R.id.ed_len);
        ed_width = (EditText) findViewById(R.id.ed_width);
        ed_height = (EditText) findViewById(R.id.ed_height);
        tv_title.setText(getText(R.string.insured_query));
        iv_back.setOnClickListener(this);
        tv_calculation.setOnClickListener(this);
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
            case R.id.tv_calculation:
                prepareData();
                Intent intent = new Intent(mActivity, PostageResultActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void prepareData() {

    }
}
