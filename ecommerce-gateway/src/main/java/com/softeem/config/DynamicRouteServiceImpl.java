package com.softeem.config;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionLocator;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author LJL
 * @Date 2023:08:24:09:35
 * 动态路由实现类
 **/
@Component
@Slf4j
public class DynamicRouteServiceImpl implements ApplicationEventPublisherAware {

    //发布事件指令的对象
    private ApplicationEventPublisher applicationEventPublisher;

    //applicationEventPublisher.publishEvent(new GatewayRefreshApplicationEvent(this))

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
      this.applicationEventPublisher = applicationEventPublisher;
    }

    //重写一个set方法，然后注入一个对象
    //该对象拥有一个save delete方法用于修改路由
    @Resource
    private RouteDefinitionWriter definitionWriter;

    //查询路由定义工具
    @Resource
    private RouteDefinitionLocator routeDefinitionLocator;

    /**
     * 添加路由
     * @param routeDefinition 路由对象
     * @return
     */
    public String addRouteDefinition(RouteDefinition routeDefinition){
        //立即添加
        definitionWriter.save(Mono.just(routeDefinition)).subscribe();
        log.info("gateway add route[{}]", JSON.toJSONString(routeDefinition));
        //发布一个刷新路由的事件
        this.applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
        log.info("add route done");
        return "ok";
    }

    /**
     * 根据id删除路由
     * @param id 路由id
     * @return
     */
    public String deleteById(String id){
        log.info("gateway delete route:[{}]",id);
        definitionWriter.delete(Mono.just(id)).subscribe();
        applicationEventPublisher.publishEvent(new RefreshRoutesEvent(this));
        return "success";
    }

    /**
     * 批量修改路由
     * @param routeDefinitions 路由集合
     * @return
     */
    public String updateList(List<RouteDefinition> routeDefinitions){
        log.info("gateway update routes:[{}]",routeDefinitions);
        //获取当前路由
        List<RouteDefinition> routeDefinitions1 = routeDefinitionLocator.getRouteDefinitions().buffer().blockFirst();
        //删除所有旧的
        routeDefinitions1.forEach(r->{
            deleteById(r.getId());
        });

        //添加所有新的
        routeDefinitions.forEach(d->{
            addRouteDefinition(d);
        });
        return "success";
    }
}
