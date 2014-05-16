package com.filibuster.atmosphere

import javax.servlet.http.HttpServletRequest

import org.atmosphere.cpr.{AtmosphereResourceEvent, AtmosphereResourceEventListenerAdapter, AtmosphereResource, Meteor}
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
        val m = Meteor.build(httpServletRequest)

        m.addListener(new AtmosphereResourceEventListenerAdapter() {

            override def onSuspend(event:AtmosphereResourceEvent) {
                println("event is suspended: " + event.getResource.uuid())

            }

            override def onDisconnect(event: AtmosphereResourceEvent) {
                if (event.isCancelled) {
                    println("event is cancelled")
                    // Unexpected closing. The client didn't send the close message when request.enableProtocol
                } else if (event.isClosedByClient) {
                    println("event is closed by client")
                    // atmosphere.js has send the close message.
                    // This API is only with 1.1 and up
                }
            }

        })

        m.getAtmosphereResource

    }
}
