package com.zhangf.unnamed.module.main.view;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhangf.unnamed.R;
import com.zhangf.unnamed.adapter.ChatAdapter;
import com.zhangf.unnamed.base.BaseActivity;
import com.zhangf.unnamed.base.BaseResponse2;
import com.zhangf.unnamed.module.main.model.ChatResult;
import com.zhangf.unnamed.module.main.presenter.ChatPresenter;
import com.zhangf.unnamed.module.main.presenter.ChatPresenterImpl;
import com.zhangf.unnamed.utils.SPUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by 75232 on 2018/8/23
 * Email：752323877@qq.com
 */
public class ChatActivity extends BaseActivity<ChatPresenterImpl> implements ChatPresenter.View {
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.rv_chat)
    RecyclerView rvChat;
    private String mNickName;
    private String mUid;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_back)
    ImageView ivBack;

    private ChatAdapter chatAdapter;
    private List<ChatResult.ListBean> mMsgList = new ArrayList<>();

    @Override
    protected void initToolBar(Bundle savedInstanceState) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mNickName = getIntent().getStringExtra("nickname");
        mUid = getIntent().getStringExtra("uid");

        tvTitle.setText(mNickName);
        ivBack.setVisibility(View.VISIBLE);

        rvChat.setLayoutManager(new LinearLayoutManager(mContext));
        chatAdapter = new ChatAdapter(mMsgList);
        rvChat.setAdapter(chatAdapter);


    }

    @Override
    protected void initData() {
        String formhash = (String) SPUtils.get(mContext, "formhash", "");
        mPresenter.fetchChat("1", "mypm", "utf-8", "view", formhash, mUid);

    }

    @Override
    protected ChatPresenterImpl initPresenter() {
        return new ChatPresenterImpl(this);
    }

    @OnClick({R.id.iv_back})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                this.finish();
                break;
        }
    }

    @Override
    public void showChat(BaseResponse2<ChatResult> result) {
        if(result.getRequest_id().equals("0")){
            if(result.getVariables().getList().size() > 0){
                mMsgList.clear();
                mMsgList.addAll(result.getVariables().getList());
                chatAdapter.notifyDataSetChanged();
            }
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
