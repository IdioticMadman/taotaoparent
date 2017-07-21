package com.taotao.service;

import com.taotao.common.pojo.EUDataGirdResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;

public interface ItemParamService {

    EUDataGirdResult getItemParamList(int page, int rows);

    TaotaoResult getItemParamByCid(long cid);

    TaotaoResult insertItemParam(TbItemParam itemParam);
}
