package com.xhy.common.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xhy.common.constant.CmConst;
import com.xhy.common.pojo.params.PageParams;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xuehy
 * @since 2021/4/26
 */
@Slf4j
public class PageUtil extends cn.hutool.core.util.PageUtil {

    public static Page getPage(PageParams pageParams) {
        long pageIndex = PageParams.DEFAULT_PAGE_INDEX;
        long pageSize = PageParams.DEFAULT_PAGE_SIZE;
        if (pageParams.getPageIndex() != null) {
            pageIndex = pageParams.getPageIndex();
        }
        if (pageParams.getPageSize() != null) {
            pageSize = pageParams.getPageSize();
        }
        return new Page<>(pageIndex, pageSize);
    }

    //对列表进行分页
    public static <T> Page<T> getPage(List<T> dataList, PageParams pageParams) {
        Page page = getPage(pageParams);
        int[] startEnd = PageUtil.transToStartEnd((int) page.getCurrent() - 1, (int) page.getSize());
        int start = startEnd[0];
        int end = startEnd[1];
        Page<T> resPage = new Page<>(page.getCurrent(), page.getSize());
        if (end <= dataList.size()) {
            resPage.setRecords(dataList.subList(start, end));
        } else if (dataList.size() <= start) {
            resPage.setRecords(new ArrayList<>());
        } else {
            resPage.setRecords(dataList.subList(start, dataList.size()));
        }
        //总页数
        resPage.setPages(PageUtil.totalPage(dataList.size(), (int) page.getSize()));
        //数据总条数
        resPage.setTotal(dataList.size());
        return resPage;
    }

    /**
     * 用新的数据和老的Page生成新的Page <br>
     * eg.通过Entity List生成View Page
     * @param dataList  数据
     * @param oldPage   分页
     * @return  新的分页数据
     */
    public static <T> Page<T> getNewRecordsPage(List<T> dataList, Page oldPage) {
        Page<T> newPage = new Page<>();
        newPage.setCurrent(oldPage.getCurrent());
        newPage.setPages(oldPage.getPages());
        newPage.setSize(oldPage.getSize());
        newPage.setTotal(oldPage.getTotal());
        newPage.setRecords(dataList);
        return newPage;
    }

    /**
     * 将PageParams设置为第一页的最大值
     * @param params    {@link PageParams}
     */
    public static void setMaxSize(PageParams params) {
        params.setPageIndex(CmConst.INTEGER_ZERO);
        params.setPageSize(CmConst.INTEGER_MAX_VALUE);
    }

}
