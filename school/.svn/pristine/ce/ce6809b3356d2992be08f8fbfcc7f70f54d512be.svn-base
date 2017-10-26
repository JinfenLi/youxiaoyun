package com.topview.school.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

/**
 * @author zjd
 * @email 452880294@qq.com
 * @date 2015年11月28日
 * @description xml和po生成类，将注释消除后，引入包，用maven，使用mybatis-generator:generate即可产生xml和po
 * @package com.gdut.dongjun.util
 */
public class GeneratorSqlmap {

    public void generator() throws Exception{  
  
  
        List<String> warnings = new ArrayList<String>();  
        boolean overwrite = true;  
        //加载配置文件  
        File configFile = new File("generatorConfig.xml");   
        ConfigurationParser cp = new ConfigurationParser(warnings);  
        Configuration config = cp.parseConfiguration(configFile);  
        DefaultShellCallback callback = new DefaultShellCallback(overwrite);  
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config,  
                
        		callback, warnings);  
        myBatisGenerator.generate(null);  
  
  
    }   
    public static void main(String[] args) throws Exception {  
        try {  
            GeneratorSqlmap generatorSqlmap = new GeneratorSqlmap();  
            generatorSqlmap.generator();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
          
    }  
    
}
