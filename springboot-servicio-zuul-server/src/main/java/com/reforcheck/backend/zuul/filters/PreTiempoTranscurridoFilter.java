package com.reforcheck.backend.zuul.filters;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.reforcheck.backend.commons.constants.ConstantsApp;
import com.reforcheck.backend.commons.constants.ConstantsTypes;

@Component
public class PreTiempoTranscurridoFilter extends ZuulFilter {

	private static Logger log = LoggerFactory.getLogger(PreTiempoTranscurridoFilter.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {

		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();

		log.info(String.format(ConstantsApp.LOG_FILTER_ZUUL_ROUTE, request.getMethod(),
				request.getRequestURL().toString()));

		Long tiempoInicio = System.currentTimeMillis();
		request.setAttribute(ConstantsApp.INIT_TIME, tiempoInicio);

		return null;
	}

	@Override
	public String filterType() {
		return ConstantsTypes.FILTER_TYPE_PRE;
	}

	@Override
	public int filterOrder() {
		return ConstantsTypes.ENT_1;
	}

}
