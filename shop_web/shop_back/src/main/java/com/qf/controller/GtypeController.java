package com.qf.controller;

  /*
    @author: LMFeng
    @date: 2019-07-08 8:31
    @desc:
  */

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Gtype;
import com.qf.service.IGtypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("gtype")
public class GtypeController {
    @Reference
    private IGtypeService gtypeService;

    @RequestMapping("toList")
    public String toList(Model model){
        List<Gtype> gtypes = gtypeService.toList();
        System.out.println(gtypes);
        model.addAttribute("gtypes",gtypes);
        return "/gtypelist";
    }

    @RequestMapping("addGtype")
    public String addGtype(Gtype gtype){
        int i = gtypeService.addGtype(gtype);
        return "redirect:/gtype/toList";
    }

    @RequestMapping("listajax")
    @ResponseBody
    public List<Gtype> listajax(){
        List<Gtype> gtypes = gtypeService.toList();
        return gtypes;
    }

    @RequestMapping("deleteGtypeById/{id}")
    public String deleteGtypeById(@PathVariable Integer id){
        boolean b = gtypeService.deleteGtypeById(id);
        return "redirect:/gtype/toList";
    }

    @RequestMapping("listById/{id}")
    @ResponseBody
    public Gtype listById(@PathVariable Integer id){
        Gtype gtype = gtypeService.listById(id);
        System.err.println("listById----"+gtype);
        return gtype;
    }

    @RequestMapping("update")
    public String update(Gtype gtype){
        System.err.println(gtype);
        boolean update = gtypeService.update(gtype);
        return "redirect:/gtype/toList";
    }


    @RequestMapping("gtypeAjax")
    @ResponseBody
    public List<Gtype> goodsAjax(){
        List<Gtype> list = gtypeService.getSecondType();
        System.err.println(list);
        return list;
    }
}
