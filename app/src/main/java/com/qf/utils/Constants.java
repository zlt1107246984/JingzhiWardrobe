package com.qf.utils;

/**
 * 接口管理
 * 常量管理
 */
public interface Constants {

    interface URL{
        //欧美URL
        String ouMeiUrl = "http://wq.jd.com/dpsearch/search?datatype=1&scene=1&pagesize=40&key=8988&page=1&callback=sdp8988_1&g_tk=5381&g_ty=ls";
        //首页Listview的请求头
        String HTTP = "http:";
        //展示数据的URL
        String LIST_VIEW_DATA = "http://wq.jd.com/mcoss/focuscpt/qqshow?id=2482&tpl=3&pageindex=1&pagesize=10&category=%s&level=1&ch=8&extid=1";
        String LAST_DATA = "http://wqs.jd.com/data/coss/recovery/focuscpt2/2482/94ab878e825510126f413197b2dea068.shtml?id=2482&tpl=3&pageindex=1&pagesize=20&category=4&level=1&ch=8&extid=1";
    }

    interface BUNDLE_KEY{

    }

}
