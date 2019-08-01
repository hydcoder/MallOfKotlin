package com.hyd.goodscenter.ui.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.hyd.base.ext.onClick
import com.hyd.base.ui.activity.BaseActivity
import com.hyd.base.ui.fragment.BaseMvpFragment
import com.hyd.base.widgets.BannerImageLoader
import com.hyd.goodscenter.R
import com.hyd.goodscenter.common.GoodsConstant
import com.hyd.goodscenter.data.protocal.Goods
import com.hyd.goodscenter.event.GoodsDetailImageEvent
import com.hyd.goodscenter.injection.component.DaggerGoodsComponent
import com.hyd.goodscenter.injection.module.GoodsModule
import com.hyd.goodscenter.presenter.GoodsDetailPresenter
import com.hyd.goodscenter.presenter.view.GoodsDetailView
import com.hyd.goodscenter.widgets.GoodsSkuPopView
import com.kotlin.base.utils.YuanFenConverter
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*

/**
 * Created by hydCoder on 2019/8/1.
 * 以梦为马，明日天涯。
 */
class GoodsDetailTabOneFragment : BaseMvpFragment<GoodsDetailPresenter>(), GoodsDetailView {

    private var mCurGoods: Goods? = null

    private lateinit var mSkuPop: GoodsSkuPopView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_goods_detail_tab_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData()
        initSkuPop()
    }

    private fun initView() {
        mGoodsDetailBanner.setImageLoader(BannerImageLoader())
        mGoodsDetailBanner.setBannerAnimation(Transformer.Accordion)
        mGoodsDetailBanner.setDelayTime(2000)
        //设置指示器位置（当banner模式中有指示器时）
        mGoodsDetailBanner.setIndicatorGravity(BannerConfig.RIGHT)

        mSkuView.onClick {
            mSkuPop.showAtLocation(
                (activity as BaseActivity).contentView, Gravity.BOTTOM and Gravity.CENTER_HORIZONTAL, 0, 0
            )
        }
    }

    private fun loadData() {
        mPresenter.getGoodsDetailList(activity!!.intent.getIntExtra(GoodsConstant.KEY_GOODS_ID, -1))
    }

    private fun initSkuPop() {
        mSkuPop = GoodsSkuPopView(activity!!)
    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(activityComponent).goodsModule(GoodsModule()).build()
            .inject(this)
        mPresenter.mView = this
    }

    override fun onGetGoodsDetailResult(result: Goods) {
        mCurGoods = result

        mGoodsDetailBanner.setImages(result.goodsBanner.split(","))
        mGoodsDetailBanner.start()

        mGoodsDescTv.text = result.goodsDesc
        mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(result.goodsDefaultPrice)
        mSkuSelectedTv.text = result.goodsDefaultSku

        Bus.send(GoodsDetailImageEvent(result.goodsDetailOne, result.goodsDetailTwo))

        loadPopData(result)
    }

    override fun onAddCartResult(result: Int) {
    }

    /*
       加载SKU数据
    */
    private fun loadPopData(result: Goods) {
        mSkuPop.setGoodsIcon(result.goodsDefaultIcon)
        mSkuPop.setGoodsCode(result.goodsCode)
        mSkuPop.setGoodsPrice(result.goodsDefaultPrice)

        mSkuPop.setSkuData(result.goodsSku)

    }
}