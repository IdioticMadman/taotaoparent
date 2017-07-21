package com.taotao.controller;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("item/cat/")
public class ItemCatController {

    private final ItemCatService itemCatService;

    @Autowired
    public ItemCatController(ItemCatService itemCatService) {
        this.itemCatService = itemCatService;
    }


    @RequestMapping("list")
    @ResponseBody
    public List<EUTreeNode> getItemCat(@RequestParam(value = "id", defaultValue = "0") Long id) {
        return itemCatService.getItemCatList(id);
    }


}
