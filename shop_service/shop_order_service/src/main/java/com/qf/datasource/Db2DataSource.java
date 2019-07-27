package com.qf.datasource;

  /*
    @author: LMFeng
    @date: 2019-07-27 10:22
    @desc:
  */

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.orderdb2.datasource")
public class Db2DataSource extends BaseDataSource{
}
