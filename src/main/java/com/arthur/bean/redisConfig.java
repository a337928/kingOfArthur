package com.arthur.bean;

import javax.persistence.criteria.CriteriaBuilder;

/**
 * Created by wangtao on 17/3/17.
 */
public class redisConfig {
    private  String  host;
    private  Integer port;
    private  Integer DBIndex;
    private  Integer timeout;
    private  String  pwd;
    private  Integer maxTotal;
    private  Integer maxIdle;
    private  Integer maxWait;

    public redisConfig(){

        this.host = "127.0.0.1";
        this.port = 6379;
        this.DBIndex = 0;
        this.timeout = 500;
        this.pwd ="123";
        this.maxTotal = 600;
        this.maxIdle = 100;
        this.maxWait = 200;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getDBIndex() {
        return DBIndex;
    }

    public void setDBIndex(Integer DBIndex) {
        this.DBIndex = DBIndex;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getMaxTotal() {
        return maxTotal;
    }

    public void setMaxTotal(Integer maxTotal) {
        this.maxTotal = maxTotal;
    }

    public Integer getMaxIdle() {
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Integer getMaxWait() {
        return maxWait;
    }

    public void setMaxWait(Integer maxWait) {
        this.maxWait = maxWait;
    }
}
