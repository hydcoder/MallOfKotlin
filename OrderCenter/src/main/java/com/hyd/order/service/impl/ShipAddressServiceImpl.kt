package com.hyd.order.service.impl

import com.hyd.base.ext.convert
import com.hyd.base.ext.convertBoolean
import com.hyd.order.data.protocol.ShipAddress
import com.hyd.order.data.repository.ShipAddressRepository
import com.hyd.order.service.ShipAddressService
import rx.Observable
import javax.inject.Inject

/**
 * Created by hydCoder on 2019/8/7.
 * 以梦为马，明日天涯。
 */
/*
    收货人信息 业务实现类
 */
class ShipAddressServiceImpl @Inject constructor(): ShipAddressService {

    @Inject
    lateinit var repository: ShipAddressRepository

    /*
        添加收货人信息
     */
    override fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String): Observable<Boolean> {
        return repository.addShipAddress(shipUserName,shipUserMobile,shipAddress).convertBoolean()

    }

    /*
        获取收货人信息列表
     */
    override fun getShipAddressList(): Observable<MutableList<ShipAddress>?> {
        return repository.getShipAddressList().convert()
    }

    /*
        修改收货人信息
     */
    override fun editShipAddress(address: ShipAddress): Observable<Boolean> {
        return  repository.editShipAddress(address).convertBoolean()
    }

    /*
        删除收货人信息
     */
    override fun deleteShipAddress(id: Int): Observable<Boolean> {
        return repository.deleteShipAddress(id).convertBoolean()
    }
}