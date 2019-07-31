package com.hyd.goodscenter.data.protocal

/**
 * Created by hydCoder on 2019/7/31.
 * 以梦为马，明日天涯。
 */
/*
    商品SKU数据类
 */
data class GoodsSku(
    val id: Int,
    val skuTitle: String,//SKU标题
    val skuContent: List<String>//SKU内容
)