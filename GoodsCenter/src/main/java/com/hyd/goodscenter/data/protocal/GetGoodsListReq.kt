package com.hyd.goodscenter.data.protocal

/**
 * Created by hydCoder on 2019/7/31.
 * 以梦为马，明日天涯。
 */
/*
    按分类搜索商品
 */
data class GetGoodsListReq(val categoryId: Int,val pageNo: Int)