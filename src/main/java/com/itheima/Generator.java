package com.itheima;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;

public class Generator {
    public static void main(String[] args) {
        AutoGenerator autoGenerator = new AutoGenerator();

        DataSourceConfig dataSource = new DataSourceConfig();
        dataSource.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/reggie?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("323020");
        autoGenerator.setDataSource(dataSource);

        autoGenerator.execute();
    }
}
