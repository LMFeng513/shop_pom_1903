package com.qf.dataconfig;

  /*
    @author: LMFeng
    @date: 2019-07-27 10:20
    @desc:
  */


import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        System.out.println("获取动态数据的关键字："+threadLocal.get());
        return threadLocal.get();
    }
    private static ThreadLocal<String>threadLocal =new ThreadLocal<>();

    public static void set(String datasourceKeywork){
        threadLocal.set(datasourceKeywork);
    }
}
