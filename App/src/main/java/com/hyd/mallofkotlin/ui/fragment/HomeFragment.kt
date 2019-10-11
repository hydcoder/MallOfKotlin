package com.hyd.mallofkotlin.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hyd.base.ext.onClick
import com.hyd.base.ui.fragment.BaseFragment
import com.hyd.base.widgets.BannerImageLoader
import com.hyd.goodscenter.ui.activity.SearchGoodsActivity
import com.hyd.mallofkotlin.R
import com.hyd.mallofkotlin.common.*
import com.hyd.mallofkotlin.ui.adapter.HomeDiscountAdapter
import com.hyd.mallofkotlin.ui.adapter.TopicAdapter
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_home.*
import me.crosswall.lib.coverflow.CoverFlow
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

/**
 * Created by hydCoder on 2019/7/26.
 * 以梦为马，明日天涯。
 */
class HomeFragment : BaseFragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBanner()
        initNews()
        initDiscount()
        initTopic()
    }


    private fun initBanner() {
        mHomeBanner.setImageLoader(BannerImageLoader())
            .isAutoPlay(true)
            .setDelayTime(2000)
            .setBannerStyle(BannerConfig.CIRCLE_INDICATOR)
            .setIndicatorGravity(BannerConfig.RIGHT)
            .setBannerAnimation(Transformer.Tablet)
            .setImages(listOf(HOME_BANNER_ONE, HOME_BANNER_TWO, HOME_BANNER_THREE, HOME_BANNER_FOUR))
            .start()
    }

    private fun initNews() {
        mNewsFlipperView.setData(arrayOf("夏日炎炎，第一波福利还有30秒到达战场", "新用户立领1000元优惠券", "大牌上新，诚意满满"))
        mSearchEt.onClick {
            startActivity<SearchGoodsActivity>()
        }
        mScanIv.onClick {
            toast(R.string.coming_soon_tip)
        }
    }

    private fun initDiscount() {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.HORIZONTAL

        mHomeDiscountRv.layoutManager = layoutManager

        val homeDiscountAdapter = HomeDiscountAdapter(context!!)
        homeDiscountAdapter.setData(
            mutableListOf(
                HOME_DISCOUNT_ONE,
                HOME_DISCOUNT_TWO,
                HOME_DISCOUNT_THREE,
                HOME_DISCOUNT_FOUR,
                HOME_DISCOUNT_FIVE
            )
        )
        mHomeDiscountRv.adapter = homeDiscountAdapter
    }

    private fun initTopic() {
        val topicAdapter = TopicAdapter(
            context!!,
            listOf(HOME_TOPIC_ONE, HOME_TOPIC_TWO, HOME_TOPIC_THREE, HOME_TOPIC_FOUR, HOME_TOPIC_FIVE)
        )
        mTopicPager.adapter = topicAdapter
        mTopicPager.currentItem = 1
        mTopicPager.offscreenPageLimit = 5

        CoverFlow.Builder().with(mTopicPager).scale(0.3f).pagerMargin(-30.0f).spaceSize(0.0f).build()
    }
}