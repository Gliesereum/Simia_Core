package com.gliesereum.proxy.filter;

import com.gliesereum.share.common.security.properties.SecurityProperties;
import com.gliesereum.proxy.service.keeper.EndpointKeeperService;
import com.gliesereum.share.common.security.jwt.factory.JwtTokenFactory;
import com.gliesereum.share.common.util.SecurityUtil;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * @author yvlasiuk
 * @version 1.0
 */

@Component
public class PreFilter extends ZuulFilter {

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private JwtTokenFactory jwtTokenFactory;

    @Autowired
    private EndpointKeeperService endpointKeeperService;

    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {

        String jwtToken = jwtTokenFactory.getJwtToken(SecurityUtil.getUser());
        RequestContext ctx = RequestContext.getCurrentContext();
        String requestURI = ctx.getRequest().getRequestURI();
        String method = ctx.getRequest().getMethod();
        endpointKeeperService.checkAccess(jwtToken, requestURI, method);
        ctx.addZuulRequestHeader(securityProperties.getJwtHeader(), securityProperties.getJwtPrefix() + " " + jwtToken);

        return null;
    }
}
