package com.neusoft.config;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * filterType 为过滤类型，可选值有 pre（路由之前）、routing（路由之时）、post（路由之后）、error（发生错误时调用）。
 * filterOrdery 为过滤的顺序，如果有多个过滤器，则数字越小越先执行
 * shouldFilter 表示是否过滤，这里可以做逻辑判断，true 为过滤，false 不过滤
 * run 为过滤器执行的具体逻辑，在这里可以做很多事情，比如：权限判断、合法性校验等。
 */
@Component
public class TokenFilter extends ZuulFilter {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public boolean shouldFilter() {
        return true;//表示是否过滤，这里可以做逻辑判断，true 为过滤，false 不过滤
    }

    @Override
    public Object run() throws ZuulException {
        //获取上下文
        RequestContext currentContext = RequestContext.getCurrentContext();
        //获取request对象
        HttpServletRequest request = currentContext.getRequest();
        String token = request.getParameter("token");
        String userCode = redisUtils.get(token+"_id");
        System.out.println("=========="+userCode);
        String servletUrl = request.getServletPath();
        if (StringUtils.isEmpty(userCode)) {
            if(!servletUrl.contains("/backend/user/getUserByLogin")){
                //返回错误提示
                currentContext.setSendZuulResponse(false);  //false  不会继续往下执行 不会调用服务接口了 网关直接响应给客户了
                currentContext.setResponseBody("Login invalid, please login again!");
                currentContext.getResponse().setContentType("text/html;charset=UTF-8");
                currentContext.setResponseStatusCode(401);
                return null;
            }
            //否则正常执行 调用服务接口...
            return null;
        }
        //否则正常执行 调用服务接口...
        return null;
    }

    /**
     * pre 表示请求在被路由之前执行
     * routing 在路由请求时调用
     * post在routing和error过滤器之后调用
     * error处理请求时发生错误调用
     */
    @Override
    public String filterType() {
        return "pre";//过滤器的类型
    }

    @Override
    public int filterOrder() {
        return 0; //过滤器执行的顺序 一个请求在同一个阶段存在多个过滤器时候，多个过滤器执行顺序问题
    }

}
