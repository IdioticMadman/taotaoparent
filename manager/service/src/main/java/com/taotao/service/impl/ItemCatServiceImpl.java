package com.taotao.service.impl;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.mapper.TbItemCatMapper;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemCatExample;
import com.taotao.pojo.TbItemExample;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {

    private final TbItemCatMapper itemCatMapper;

    @Autowired
    public ItemCatServiceImpl(TbItemCatMapper itemCatMapper) {
        this.itemCatMapper = itemCatMapper;
    }


    @Override
    public List<EUTreeNode> getItemCatList(Long parentId) {

        TbItemCatExample example = new TbItemCatExample();
        TbItemCatExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        List<TbItemCat> itemCats = itemCatMapper.selectByExample(example);
        List<EUTreeNode> treeNodes = new ArrayList<>();
        for (TbItemCat itemCat : itemCats) {
            EUTreeNode euTreeNode = new EUTreeNode();
            euTreeNode.setId(itemCat.getId());
            euTreeNode.setText(itemCat.getName());
            euTreeNode.setState(itemCat.getIsParent() ? "closed" : "open");
            treeNodes.add(euTreeNode);
        }
        return treeNodes;
    }
}
