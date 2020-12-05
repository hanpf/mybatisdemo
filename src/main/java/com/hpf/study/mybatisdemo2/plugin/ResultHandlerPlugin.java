package com.hpf.study.mybatisdemo2.plugin;

import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import java.sql.Statement;
import java.util.List;
import java.util.Properties;

@Intercepts({
        @Signature(type = ResultSetHandler.class,method = "handleResultSets",args = {Statement.class})
})
public class ResultHandlerPlugin implements Interceptor {

    private String info;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object result = invocation.proceed();
        System.out.println(info+":"+((List)result).size());
        return result;
    }


    @Override
    public void setProperties(Properties properties) {
        info = properties.get("info").toString();
    }
}
