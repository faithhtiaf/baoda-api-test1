package com.baoda.test

import org.testng.annotations.Test

/**
 * Created by admin on 2016/8/10.
 */
class NoteTest {
    @Test
    public void writeNote(){
        def load=[
                "articleId": "报告id",
                "articleName": "报告标题",
                "chapterId": "章id",
                "chapterName": "章名称",
                "sectionId": "节id",
                "sectionName": "节名称",
                "pageNumber": 12,
                "position": 20,
                "original": "选中的原始文字",
                "content": "笔记内容",
                "isPublic": true
        ]
    }

}
