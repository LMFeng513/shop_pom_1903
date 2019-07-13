package com.qf.controller;

  /*
    @author: LMFeng
    @date: 2019-07-13 8:44
    @desc:
  */

import com.qf.entity.Goods;
import com.qf.service.ISearchService;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.xml.ws.RequestWrapper;
import java.util.List;

@Controller
@RequestMapping("/search")
public class SearchController {

@Reference
    private ISearchService searchService;

@RequestMapping("/searchByKey")
    public String searchByKey(String keyword, Model model){
    System.out.println("获得搜索框输入的搜索关键字： "+keyword);

    List<Goods>goodsList =searchService.searchByKey(keyword);
    model.addAttribute("goodsList",goodsList);
    return "searchlist";
}
public static void main(String[]args){

}
}
