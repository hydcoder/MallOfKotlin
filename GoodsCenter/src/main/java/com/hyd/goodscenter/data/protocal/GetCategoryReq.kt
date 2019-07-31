package com.hyd.goodscenter.data.protocal

/**
 * Created by hydCoder on 2019/7/31.
 * 以梦为马，明日天涯。
 */
/*
    获取分类列表请求，parentId为0是一级分类
 */
data class GetCategoryReq (val parentId: Int)