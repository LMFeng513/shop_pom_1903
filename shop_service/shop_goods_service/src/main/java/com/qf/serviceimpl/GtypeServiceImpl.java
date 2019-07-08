package com.qf.serviceimpl;

  /*
    @author: LMFeng
    @date: 2019-07-08 8:36
    @desc:
  */


import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.dao.GtypeMapper;
import com.qf.entity.Gtype;
import com.qf.service.IGtypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
@Service
public class GtypeServiceImpl implements IGtypeService {

    @Autowired
    private GtypeMapper gtypeMapper;

    @Override
    public List<Gtype> toList() {
        List<Gtype> gtypes = gtypeMapper.toList();
        return gtypes;
    }

    @Override
    public int addGtype(Gtype gtype) {
        int insert = gtypeMapper.insert(gtype);
        return insert;
    }

    @Override
    public boolean deleteGtypeById(Integer id) {
        int i = gtypeMapper.deleteById(id);
        if (i > 0){
            return true;
        }
        return false;
    }

    @Override
    public Gtype listById(Integer id) {
        Gtype gtype = gtypeMapper.queryById(id);
        return gtype;
    }

    @Override
    public boolean update(Gtype gtype) {
        int update = gtypeMapper.updateById(gtype);
        if (update > 0){
            return true;
        }
        return false;
    }

    @Override
    public List<Gtype> getSecondType() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("status",2);
        List list = gtypeMapper.selectList(wrapper);
        return list;
    }

}
