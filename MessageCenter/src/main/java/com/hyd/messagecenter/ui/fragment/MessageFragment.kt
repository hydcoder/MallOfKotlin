package com.hyd.messagecenter.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.hyd.base.ext.startLoading
import com.hyd.base.ui.fragment.BaseMvpFragment
import com.hyd.messagecenter.R
import com.hyd.messagecenter.data.protocal.Message
import com.hyd.messagecenter.injection.component.DaggerMessageComponent
import com.hyd.messagecenter.injection.module.MessageModule
import com.hyd.messagecenter.presenter.MessagePresenter
import com.hyd.messagecenter.presenter.view.MessageView
import com.hyd.messagecenter.ui.adapter.MessageAdapter
import com.hyd.provider.event.MessageBadgeEvent
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.fragment_message.*

/**
 * Created by hydCoder on 2019/10/11.
 * 以梦为马，明日天涯。
 */
/*
    消息列表Fragment
 */
class MessageFragment: BaseMvpFragment<MessagePresenter>(), MessageView {

    private lateinit var mAdapter: MessageAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_message,container,false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()

    }

    /*
        初始化视图
     */
    private fun initView() {
        mMessageRv.layoutManager = LinearLayoutManager(context)
        mAdapter = MessageAdapter(context!!)
        mMessageRv.adapter = mAdapter
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    /*
        加载数据
     */
    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getMessageList()
    }

    /*
        Dagger注册
     */
    override fun injectComponent() {
        DaggerMessageComponent.builder().activityComponent(activityComponent).messageModule(
            MessageModule()
        ).build().inject(this)
        mPresenter.mView = this
    }

    /*
        获取消息后回调
     */
    override fun onGetMessageResult(result: MutableList<Message>?) {
        if (result != null && result.size > 0){
            mAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        }else{
            //数据为空
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    /*
        监听Fragment隐藏或显示
     */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden){
            Bus.send(MessageBadgeEvent(false))
        }
    }
}