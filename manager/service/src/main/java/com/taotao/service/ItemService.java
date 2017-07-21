package com.taotao.service;


import com.taotao.common.pojo.EUDataGirdResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItem;

public interface ItemService {
    TbItem getItem(Long id);

    EUDataGirdResult getItemList(int page, int rows);

    TaotaoResult saveItem(TbItem item, String desc, String params);
}
