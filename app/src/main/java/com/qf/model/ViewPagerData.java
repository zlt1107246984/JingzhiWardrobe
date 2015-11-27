package com.qf.model;

/**
 * getXianShiGouCB({
 errCode: "0",
 msg: "",
 serverTime: "1448539593",
 data: {
 m_dwActId: "60529",
 list: [
 {
 "dwAreaId": "69947",
 "sAreaName": "",
 "dwBeginTime": "1437494400",
 "dwEndTime": "1472659199",
 "sSkuId": "1139410",
 "sItemId": "1139410",
 "sItemName": "娥佩兰（OPERA） 薏苡仁化妆水 500ml",
 "sFullName": "娥佩兰（OPERA） 薏苡仁化妆水 500ml",
 "sImg": "",
 "sImg120x120": "//img11.360buyimg.com/n1/s120x120_g16/M00/0B/1C/rBEbRVODEDcIAAAAAADzq1rs8ngAACUWgG524QAAPPD379.jpg",
 "sImg160x160": "//img11.360buyimg.com/n1/s160x160_g16/M00/0B/1C/rBEbRVODEDcIAAAAAADzq1rs8ngAACUWgG524QAAPPD379.jpg",
 "sImg200x200": "//img11.360buyimg.com/n1/s200x200_g16/M00/0B/1C/rBEbRVODEDcIAAAAAADzq1rs8ngAACUWgG524QAAPPD379.jpg",
 "dwMarketPrice": "68.00",
 "dwActMinPrice": "49.00",
 "dwWeChatPrice": "49.00",
 "dwMQQPrice": "49.00",
 "dwDiscount": "72.05",
 "dwState": "",
 "dwClassIdL1": "1316",
 "dwShopId": "0",
 "dwNewPrice": "49.00",
 "dwSales": "0",
 "dwSkuState": "0",
 "sUrl": "//wq.jd.com/item/view?sku=1139410&pps=mart.0007FFF5020001%3A00000000001162D2049A3A2F0001113B0000EC71%3Arec%3D905011%24expid%3D0&DAP=0:844710708678060863:524277:1139410",
 "dwProperty": "16385",
 "ext1": "",
 "ext2": "",
 "ext3": "",
 "ext4": "",
 "dwCommCnt": "0",
 "dwEvalCnt": "49299",
 "commPinMask": "0",
 "sUploadPicUrl2": "",
 "sUploadPicUrl3": "",
 "sUploadPicUrl4": "",
 "className1": "个护化妆",
 "className2": "面部护肤",
 "className3": "护肤",
 "className4": "",
 "classId1": "1316",
 "classId2": "1381",
 "classId3": "1391",
 "classId4": "0",
 "colorMark": ""
 },
 */
public class ViewPagerData  {

    private String sItemName;
    private String sImg;
    private String dwActMinPrice;


    public String getsItemName() {
        return sItemName;
    }

    public void setsItemName(String sItemName) {
        this.sItemName = sItemName;
    }

    public String getsImg() {
        return sImg;
    }

    public void setsImg(String sImg) {
        this.sImg = sImg;
    }

    public String getDwActMinPrice() {
        return dwActMinPrice;
    }

    public void setDwActMinPrice(String dwActMinPrice) {
        this.dwActMinPrice = dwActMinPrice;
    }
}
