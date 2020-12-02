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
public class PostTiempoTranscurridoFilter extends ZuulFilter{
	
	private static Logger log = LoggerFactory.getLogger(PostTiempoTranscurridoFilter.class);

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() throws ZuulException {

		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		
		log.info(ConstantsApp.LOG_FILTER_END_TIME);
				
		Long tiempoInicio = (Long) request.getAttribute(ConstantsApp.INIT_TIME);
		Long tiempoFinal = System.currentTimeMillis();
		Long tiempoTranscurrido = tiempoFinal - tiempoInicio;
		
		log.info(String.format(ConstantsApp.LOG_FILTER_TOTAL_TIME_SECONDS, tiempoTranscurrido.doubleValue()/1000.00));
		log.info(String.format(ConstantsApp.LOG_FILTER_TOTAL_TIME_MILLISECONDS, tiempoTranscurrido));
		return null;
	}

	@Override
	public String filterType() {
		return ConstantsTypes.FILTER_TYPE_POST;
	}

	@Override
	public int filterOrder() {
		return ConstantsTypes.ENT_1;
	}

}
