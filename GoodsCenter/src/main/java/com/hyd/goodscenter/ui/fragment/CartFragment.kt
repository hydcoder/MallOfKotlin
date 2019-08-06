package com.hyd.goodscenter.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.hyd.base.ext.onClick
import com.hyd.base.ext.setVisible
import com.hyd.base.ext.startLoading
import com.hyd.base.ui.fragment.BaseMvpFragment
import com.hyd.base.utils.AppPrefsUtils
import com.hyd.goodscenter.R
import com.hyd.goodscenter.common.GoodsConstant
import com.hyd.goodscenter.data.protocal.CartGoods
import com.hyd.goodscenter.event.CartAllCheckedEvent
import com.hyd.goodscenter.event.UpdateCartSizeEvent
import com.hyd.goodscenter.event.UpdateTotalPriceEvent
import com.hyd.goodscenter.injection.component.DaggerCartComponent
import com.hyd.goodscenter.injection.module.CartModule
import com.hyd.goodscenter.presenter.CartListPresenter
import com.hyd.goodscenter.presenter.view.CartListView
import com.hyd.goodscenter.ui.adapter.CartGoodsAdapter
import com.kennyc.view.MultiStateView
import com.kotlin.base.utils.YuanFenConverter
import kotlinx.android.synthetic.main.fragment_cart.*
import org.jetbrains.anko.support.v4.toast

/**
 * Created by hydCoder on 2019/7/31.
 * 以梦为马，明日天涯。
 */
/*
    商品分类 Fragment
 */
class CartFragment : BaseMvpFragment<CartListPresenter>(), CartListView {

    private lateinit var mAdapter: CartGoodsAdapter

    private var mTotalPrice: Long = 0

    override fun injectComponent() {
        DaggerCartComponent.builder().activityComponent(activityComponent).cartModule(CartModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserve()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    private fun initView() {
        mCartGoodsRv.layoutManager = LinearLayoutManager(context)
        mAdapter = CartGoodsAdapter(activity!!)
        mCartGoodsRv.adapter = mAdapter

        mAllCheckedCb.onClick {
            for (item in mAdapter.dataList) {
                item.isSelected = mAllCheckedCb.isChecked
            }
            mAdapter.notifyDataSetChanged()
            updateTotalPrice()
        }

        mHeaderBar.getRightView().onClick {
            refreshEditStatus()
        }

        mDeleteBtn.onClick {
            val goodsIdList = mutableListOf<Int>()
            mAdapter.dataList.filter { it.isSelected }
                .mapTo(goodsIdList) {
                    it.id
                }
            if (goodsIdList.size == 0) {
                toast("请选择要删除的商品")
            } else {
                mPresenter.deleteCartList(goodsIdList)
            }
        }

        mSettleAccountsBtn.onClick {
            val goodsList = mutableListOf<CartGoods>()
            mAdapter.dataList.filter { it.isSelected }
                .mapTo(goodsList) { it }
            if (goodsList.size == 0) {
                toast("请选择要结算的商品")
            } else {
                mPresenter.submitCart(goodsList, mTotalPrice)
            }
        }

        updateTotalPrice()
    }

    private fun refreshEditStatus() {
        val isEditStatus = getString(R.string.common_edit) == mHeaderBar.getRightText()
        mTotalPriceTv.setVisible(isEditStatus.not())
        mSettleAccountsBtn.setVisible(isEditStatus.not())
        mDeleteBtn.setVisible(isEditStatus)

        mHeaderBar.getRightView().text =
            if (isEditStatus) getString(R.string.common_complete) else getString(R.string.common_edit)
    }

    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getCartList()
    }

    override fun onGetCartListResult(result: MutableList<CartGoods>?) {
        if (result != null && result.size > 0) {
            mAdapter.setData(result)
            mHeaderBar.getRightView().setVisible(true)
            mAllCheckedCb.isChecked = false
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mHeaderBar.getRightView().setVisible(false)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
        AppPrefsUtils.putInt(GoodsConstant.SP_CART_SIZE, result?.size ?: 0)
        Bus.send(UpdateCartSizeEvent())
    }

    private fun initObserve() {
        Bus.observe<CartAllCheckedEvent>()
            .subscribe {
                mAllCheckedCb.isChecked = it.isAllChecked
                updateTotalPrice()
            }.registerInBus(this)
        Bus.observe<UpdateTotalPriceEvent>()
            .subscribe {
                updateTotalPrice()
            }.registerInBus(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

    private fun updateTotalPrice() {
        mTotalPrice = mAdapter.dataList.filter {
            it.isSelected
        }.map {
            it.goodsCount * it.goodsPrice
        }.sum()
        mTotalPriceTv.text = "合计：${YuanFenConverter.changeF2YWithUnit(mTotalPrice)}"
    }

    override fun onDeleteCartListResult(result: Boolean) {
        if (result) {
            toast("删除成功")
            refreshEditStatus()
            loadData()
        } else {
            toast("删除失败")
        }
    }

    override fun onSubmitCartListResult(result: Int) {
        toast("$result")
    }

    fun setLeftIvVisible(isVisible: Boolean) {
        mHeaderBar.getLeftView().setVisible(isVisible)
    }
}