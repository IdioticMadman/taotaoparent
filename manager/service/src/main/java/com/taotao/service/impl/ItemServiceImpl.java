package com.taotao.service.impl;

import java.util.Date;
import java.util.List;

import com.taotao.common.pojo.EUDataGirdResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.common.utils.IDUtils;
import com.taotao.mapper.TbItemDescMapper;
import com.taotao.mapper.TbItemMapper;
import com.taotao.mapper.TbItemParamItemMapper;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.*;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

//@Service("itemService")
@Service
public class ItemServiceImpl implements ItemService {

    private final TbItemMapper itemMapper;
    private final TbItemDescMapper itemDescMapper;
    private final TbItemParamItemMapper itemParamItem;

    @Autowired
    public ItemServiceImpl(TbItemMapper itemMapper,
                           TbItemDescMapper itemDescMapper,
                           TbItemParamItemMapper itemParamItem) {
        this.itemMapper = itemMapper;
        this.itemDescMapper = itemDescMapper;
        this.itemParamItem = itemParamItem;
    }

    @Override
    public TbItem getItem(Long id) {
        itemMapper.selectByPrimaryKey(id);
        TbItemExample example = new TbItemExample();
        TbItemExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        List<TbItem> list = itemMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            return list.get(0);
        }
        return null;
    }

    @Override
    public EUDataGirdResult getItemList(int page, int rows) {
        TbItemExample example = new TbItemExample();
        PageHelper.startPage(page, rows);
        List<TbItem> list = itemMapper.selectByExample(example);
        PageInfo<TbItem> pageInfo = new PageInfo<>(list);
        EUDataGirdResult euDataGirdResult = new EUDataGirdResult();
        euDataGirdResult.setRows(list);
        euDataGirdResult.setTotal(pageInfo.getTotal());
        return euDataGirdResult;
    }

    @Override
    public TaotaoResult saveItem(TbItem item, String desc, String params) {
        long itemId = IDUtils.genItemId();
        item.setId(itemId);
        item.setStatus((byte) 1);
        item.setUpdated(new Date());
        item.setCreated(new Date());
        itemMapper.insert(item);
        insertItemDesc(itemId, desc);
        insertItemParams(itemId, params);
        return TaotaoResult.ok();
    }

    private TaotaoResult insertItemParams(long itemId, String params) {
        TbItemParamItem tbItemParamItem = new TbItemParamItem();
        tbItemParamItem.setItemId(itemId);
        tbItemParamItem.setParamData(params);
        tbItemParamItem.setCreated(new Date());
        tbItemParamItem.setUpdated(new Date());
        itemParamItem.insert(tbItemParamItem);
        return TaotaoResult.ok();
    }

    private TaotaoResult insertItemDesc(long itemId, String desc) {
        TbItemDesc tbItemDesc = new TbItemDesc();
        tbItemDesc.setItemDesc(desc);
        tbItemDesc.setItemId(itemId);
        tbItemDesc.setCreated(new Date());
        tbItemDesc.setUpdated(new Date());
        itemDescMapper.insert(tbItemDesc);
        return TaotaoResult.ok();
    }


}
