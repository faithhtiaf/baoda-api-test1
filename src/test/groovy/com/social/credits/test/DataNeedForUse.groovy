package com.social.credits.test

/**
 * Created by admin on 2016/8/10.
 */
class DataNeedForUse {
    //A股
    static def searchDataForAShare(){
        def data1=[

                "keyword":["股东大会"],
                "from":1262304000,
                "to":1470817490.228,
                "stockCodes":[],
                "titleInclude":[],
                "titleCanInclude":[],
                "titleExclude":[],
                "contentInclude":[],
                "contentExclude":[],
                "contentCanInclude":[],
                "stockType":"aShare",
                "subSector":[],
                "industry":[],
                "province":[],
                "announcementType":[]
        ]
        return data1

    }


    static def searchDataForNewOTC(){
        def  data2=[

                "keyword":["江苏中天科技股份有限公司"],
                "from":0,
                "to":0,
                "stockCodes":[
                        "600522"
                ],
                "titleInclude":[],
                "titleCanInclude":[],
                "titleExclude":["and", "2015"],
                "contentInclude":[],
                "contentExclude":[],
                "contentCanInclude":[],
                "stockType":"newOTC",
                "subSector":["配股"],
                "industry":[],
                "province":[],
                "announcementType":[]
        ]
        return data2
    }
    static def searchDataForPreAnnouncement(){
        def  data3=[

                "keyword":["江苏中天科技股份有限公司"],
                "from":0,
                "to":0,
                "stockCodes":[
                        "600522"
                ],
                "titleInclude":[],
                "titleCanInclude":[],
                "titleExclude":["and", "2015"],
                "contentInclude":[],
                "contentExclude":[],
                "contentCanInclude":[],
                "stockType":"preAnnouncement",
                "subSector":["配股"],
                "industry":[],
                "province":[],
                "announcementType":[]
        ]
        return data3
    }
}
