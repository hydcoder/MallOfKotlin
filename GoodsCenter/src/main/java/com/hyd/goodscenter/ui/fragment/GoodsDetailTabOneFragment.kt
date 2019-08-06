package com.hyd.goodscenter.ui.fragment

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.hyd.base.ext.onClick
import com.hyd.base.ui.activity.BaseActivity
import com.hyd.base.ui.fragment.BaseMvpFragment
import com.hyd.base.widgets.BannerImageLoader
import com.hyd.goodscenter.R
import com.hyd.goodscenter.common.GoodsConstant
import com.hyd.goodscenter.data.protocal.Goods
import com.hyd.goodscenter.event.AddCartEvent
import com.hyd.goodscenter.event.GoodsDetailImageEvent
import com.hyd.goodscenter.event.SkuChangedEvent
import com.hyd.goodscenter.event.UpdateCartSizeEvent
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

    //SKU弹层出场动画
    private lateinit var mAnimationStart: Animation
    //SKU弹层退场动画
    private lateinit var mAnimationEnd: Animation

    private lateinit var mSkuPop: GoodsSkuPopView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_goods_detail_tab_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAnim()
        initSkuPop()
        loadData()
        initObserve()
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
            (activity as BaseActivity).contentView.startAnimation(mAnimationStart)
        }
    }

    private fun loadData() {
        mPresenter.getGoodsDetailList(activity!!.intent.getIntExtra(GoodsConstant.KEY_GOODS_ID, -1))
    }

    private fun initSkuPop() {
        mSkuPop = GoodsSkuPopView(activity!!)

        mSkuPop.setOnDismissListener {
            (activity as BaseActivity).contentView.startAnimation(mAnimationEnd)
        }
    }

    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(activityComponent).goodsModule(GoodsModule()).build()
            .inject(this)
        mPresenter.mView = this
    }

    /*
      初始化缩放动画
   */
    private fun initAnim() {
        mAnimationStart = ScaleAnimation(
            1f, 0.95f, 1f, 0.95f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
        )
        mAnimationStart.duration = 300
        mAnimationStart.fillAfter = true

        mAnimationEnd = ScaleAnimation(
            0.95f, 1f, 0.95f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f
        )
        mAnimationEnd.duration = 300
        mAnimationEnd.fillAfter = true
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
        Bus.send(UpdateCartSizeEvent())
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

    private fun initObserve() {
        Bus.observe<SkuChangedEvent>()
            .subscribe {
                mSkuSelectedTv.text =
                    mSkuPop.getSelectSku() + GoodsConstant.SKU_SEPARATOR + mSkuPop.getSelectCount() + "件"
            }.registerInBus(this)

        Bus.observe<AddCartEvent>()
            .subscribe {
                addCart()
            }.registerInBus(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

    private fun addCart() {
        mCurGoods?.let {
            mPresenter.addCart(
                it.id,
                it.goodsDesc,
                it.goodsDefaultIcon,
                it.goodsDefaultPrice,
                mSkuPop.getSelectCount(),
                mSkuPop.getSelectSku()
            )
        }
    }
}