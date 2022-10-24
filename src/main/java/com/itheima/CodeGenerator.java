package com.itheima;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;

public class CodeGenerator {
    public static void main(String[] args) {
        AutoGenerator autoGenerator = new AutoGenerator();

        DataSourceConfig dataSource = new DataSourceConfig();
        dataSource.setDriverName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/reggie?serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("323020");
        autoGenerator.setDataSource(dataSource);


        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(System.getProperty("user.dir")+"/src/main/java/");
        globalConfig.setOpen(false);
        globalConfig.setFileOverride(true);
        globalConfig.setMapperName("%sDao");
        autoGenerator.setGlobalConfig(globalConfig);


        PackageConfig packageInfo = new PackageConfig();
        packageInfo.setParent("com.itheima.reggie");
        autoGenerator.setPackageInfo(packageInfo);


        autoGenerator.execute();
    }
}
