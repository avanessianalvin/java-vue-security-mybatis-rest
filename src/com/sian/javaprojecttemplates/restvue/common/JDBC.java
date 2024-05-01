package com.sian.javaprojecttemplates.restvue.common;


import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.datasource.DataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class JDBC implements DataSourceFactory {
    private final static BasicDataSource basicDataSource = new BasicDataSource();


    static {
        basicDataSource.setDriverClassName("org.postgresql.Driver");
        basicDataSource.setUrl("jdbc:postgresql://localhost:5432/postgres");
        basicDataSource.setUsername("db_user");
        basicDataSource.setPassword("myjava123");
        basicDataSource.setMaxTotal(50);
        basicDataSource.setMaxIdle(5);
        basicDataSource.setMinIdle(3);
    }

    public static Connection getConnection() throws SQLException {
        return basicDataSource.getConnection();
    }

    @Override
    public void setProperties(Properties properties) {

    }

    public DataSource getDataSource(){
        return basicDataSource;
    }

}
