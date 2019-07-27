package com.qf.datasource;

  /*
    @author: LMFeng
    @date: 2019-07-27 10:22
    @desc:
  */

import com.zaxxer.hikari.HikariDataSource;
import lombok.Data;

import javax.sql.DataSource;

@Data
public class BaseDataSource {
    protected String url;
    protected String username;
    protected String password;
    protected String driverClassName;
    protected String keyword;

    public DataSource getDataSource(){
        HikariDataSource hikariDataSource =new HikariDataSource();
        hikariDataSource.setJdbcUrl(url);
        hikariDataSource.setUsername(username);
        hikariDataSource.setPassword(password);
        hikariDataSource.setDriverClassName(driverClassName);
        return hikariDataSource;
    }

}
