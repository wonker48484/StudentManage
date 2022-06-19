package com.atguigu.mybatisplus.req;

import com.atguigu.mybatisplus.constants.Constants;
import lombok.Data;

@Data
public class CommonSearchReq {
    private Integer pageNow= Constants.DEFAULT_PAGE_NOW;
    private Integer pageSize= Constants.DEFAULT_PAGE_SIZE;
    //通用的搜索词
    private  String searchWord;
    public CommonSearchReq(String searchWord){
        this.searchWord=searchWord;
    }

    public CommonSearchReq() {
        super();
        this.searchWord=null;
    }
}
