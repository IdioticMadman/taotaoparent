package com.taotao.controller;


import com.taotao.common.pojo.EUDataGirdResult;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item/param/")
public class ItemParamController {

    private final ItemParamService itemParamService;

    @Autowired
    public ItemParamController(ItemParamService itemParamService) {
        this.itemParamService = itemParamService;
    }

    @RequestMapping("/query/itemcatid/{itemCatId}")
    @ResponseBody
    public TaotaoResult getItemParamByCid(@PathVariable Long itemCatId) {
        return itemParamService.getItemParamByCid(itemCatId);
    }

    @RequestMapping("/save/{cid}")
    @ResponseBody
    public TaotaoResult insertItemParam(@PathVariable Long cid, String paramData) {
        //创建pojo对象
        TbItemParam itemParam = new TbItemParam();
        itemParam.setItemCatId(cid);
        itemParam.setParamData(paramData);
        return itemParamService.insertItemParam(itemParam);
    }

    @RequestMapping("list")
    @ResponseBody
    public EUDataGirdResult getItemParamList(Integer page, Integer rows) {
        return itemParamService.getItemParamList(page,rows);
    }
}
