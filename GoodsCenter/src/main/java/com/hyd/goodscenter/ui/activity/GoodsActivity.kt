package com.hyd.goodscenter.ui.activity

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import com.hyd.base.ext.startLoading
import com.hyd.base.ui.activity.BaseMvpActivity
import com.hyd.goodscenter.R
import com.hyd.goodscenter.data.protocal.Goods
import com.hyd.goodscenter.injection.component.DaggerGoodsComponent
import com.hyd.goodscenter.injection.module.GoodsModule
import com.hyd.goodscenter.presenter.GoodsListPresenter
import com.hyd.goodscenter.presenter.view.GoodsListView
import com.hyd.goodscenter.ui.adapter.GoodsAdapter
import com.kennyc.view.MultiStateView
import kotlinx.android.synthetic.main.activity_goods.*

/**
 * Created by hydCoder on 2019/7/31.
 * 以梦为马，明日天涯。
 */
class GoodsActivity: BaseMvpActivity<GoodsListPresenter>(), GoodsListView {

    private lateinit var mGoodsAdapter: GoodsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goods)
        initView()
        loadData()
    }

    private fun initView() {
        mGoodsRv.layoutManager = GridLayoutManager(this, 2)
        mGoodsAdapter = GoodsAdapter(this)
        mGoodsRv.adapter = mGoodsAdapter
    }

    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getGoodsList(intent.getIntExtra("categoryId", 1), 1)
    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(activityComponent).goodsModule(GoodsModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onGetGoodsListResult(result: MutableList<Goods>?) {
        if (result != null && result.size > 0) {
            mGoodsAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }
}