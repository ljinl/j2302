package com.softeem.config;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;
import com.softeem.constant.GatewayConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @Author LJL
 * @Date 2023:08:24:10:57
 **/
@Component
@Slf4j
public class DynamicRouteServiceImplByNacos {

    //Nacos的配置中心客户端对象
    private ConfigService configService;
    @Resource
    private DynamicRouteServiceImpl routeService;

    @PostConstruct
    public void init(){
        //初始化配置中心客户端对象
        log.info("gateway route init ...");
        configService=initConfigService();
        try {
            String config = configService.getConfig(GatewayConstant.NACOS_ROUTE_DATA_ID, GatewayConstant.NACOS_ROUTE_GROUP, GatewayConstant.DEFAULT_TIMEOUT);
            log.info("get Config:[{}]",config);
            //转换读取到的配置为RouteDefinition对象
            List<RouteDefinition> routeDefinitions = JSON.parseArray(config, RouteDefinition.class);
            if (CollectionUtil.isNotEmpty(routeDefinitions)){
                log.info("init route config...");
                routeDefinitions.forEach(r->{
                    routeService.addRouteDefinition(r);
                });
            }
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
        //添加监听
        try {
            configService.addListener(GatewayConstant.NACOS_ROUTE_DATA_ID, GatewayConstant.NACOS_ROUTE_GROUP, new Listener() {
                //是否使用自定义线程池
                @Override
                public Executor getExecutor() {
                    return null;
                }

                /**
                 *
                 * @param s 当监听到文件的修改，则将新文件内容发送回来
                 */
                @Override
                public void receiveConfigInfo(String s) {
                    log.info("start to update routes:[{}]",s);
                    List<RouteDefinition> routeDefinitions = JSON.parseArray(s, RouteDefinition.class);
                    routeService.updateList(routeDefinitions);
                    log.info("update done...");
                }
            });
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }

    }

    private ConfigService initConfigService() {
        Properties properties = new Properties();
        properties.setProperty("serverAddr", GatewayConstant.NACOS_SERVER_ADDR);
        properties.setProperty("namespace",GatewayConstant.NACOS_NAMESPACE);
        try {
            return NacosFactory.createConfigService(properties);
        } catch (NacosException e) {
            throw new RuntimeException(e);
        }
    }

}
