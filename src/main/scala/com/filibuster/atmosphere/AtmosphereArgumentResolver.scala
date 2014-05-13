package com.filibuster.atmosphere

import javax.servlet.http.HttpServletRequest

import org.atmosphere.cpr.AtmosphereResource
import org.atmosphere.cpr.Meteor
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import org.springframework.web.context.request.NativeWebRequest

class AtmosphereArgumentResolver extends HandlerMethodArgumentResolver
{

    override def supportsParameter(parameter:MethodParameter):Boolean =
    {
       classOf[AtmosphereResource].isAssignableFrom(parameter.getParameterType)
    }

    override def resolveArgument(parameter:MethodParameter,
                                 mavContainer: ModelAndViewContainer,
                                 webRequest:NativeWebRequest,
                                 binderFactory:WebDataBinderFactory):Object =
    {
        val httpServletRequest= webRequest.getNativeRequest(classOf[HttpServletRequest])
        Meteor.build(httpServletRequest).getAtmosphereResource
    }
}
