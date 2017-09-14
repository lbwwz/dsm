package com.dsm.service.interfaces;

import com.dsm.model.BackMsg;

/**
 * Created with IntelliJ IDEA.
 * Project: dsm
 * Date: 2017/9/15
 *
 * @author : Lbwwz
 */
public interface IOrderService {

    BackMsg<String> checkOrder();
}
