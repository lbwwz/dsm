package com.dsm.dao;

import com.dsm.model.user.IdentifyInfo;
import org.springframework.stereotype.Repository;

/**
 * Created by Lbwwz on 2016/8/31.
 */
@Repository
public interface IIdentifyInfoDao {
    /**
     * 提交用户的实名验证信息
     * @param info
     */
    Integer addIdentifyInfo(IdentifyInfo info);

    /**
     * 获取某一个用户的实名注册信息
     * @param userId
     * @return
     */
    IdentifyInfo getIdentifyInfo(long userId);


    /**
     * 更新验证信息
     * @param info
     */
    Integer updateIdentifyInfo(IdentifyInfo info);
}
