package com.filibuster.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation._
import com.gusto.filibuster.client._
import java.security.Principal
import org.atmosphere.cpr.{Meteor, AtmosphereResourceEvent, AtmosphereResourceEventListenerAdapter, AtmosphereResource}
import java.util.concurrent.{CountDownLatch, TimeUnit, Callable}
import javax.servlet.http.{HttpServletResponse, HttpServletRequest}


/**
 *
 * Last Updated: 11/25/13
 *
 * @author Ben Detwiler
 *
 */

@Controller
@RequestMapping(value=Array("/chat"))
class ChatController {


    private def suspend(resource:AtmosphereResource) {

        val countDownLatch = new CountDownLatch(1)

        resource.addEventListener(new AtmosphereResourceEventListenerAdapter() {

            override def onSuspend(event:AtmosphereResourceEvent) {
                countDownLatch.countDown()
                resource.removeEventListener(this)
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

        resource.suspend()
        try
        {
            countDownLatch.await()
        }
        catch
        {
            case e: Exception => e.printStackTrace()
        }

    }


  @RequestMapping(value = Array("/say_something"), method = Array(RequestMethod.GET))
  @ResponseBody
  def say_something(@RequestParam(value="name") name : String,
                    @RequestParam(value="group") group : String,
                    @RequestParam(value="words") words : String) = {

    //TODO: connect to service and pass what was just said
    val client = new FilibusterServiceClient()
    val users = client.queryUsers()

    "say something"

  }

  @RequestMapping(value = Array("/listen"), method = Array(RequestMethod.GET))
  @ResponseBody
  def get_conversation(@RequestParam(value="name") name : String,
                       @RequestParam(value="group") group : String) = {

    //TODO: connect to service and listen for what's being said

  }


    @RequestMapping(value = Array("/test"), method = Array(RequestMethod.GET))
    @ResponseBody
    def test(req:HttpServletRequest) = {
        val paramNames = req.getParameterNames
        "chat/test"
    }

    @RequestMapping(value = Array("/atmosphere"), method = Array(RequestMethod.GET))
    @ResponseBody
    def test_atmosphere(atmosphereResource:AtmosphereResource) = {

        this.suspend(atmosphereResource)
        val bc = atmosphereResource.getBroadcaster

        bc.scheduleFixedBroadcast( new Callable[String]() {

            override def call:String = "broadcasted hello"

        }, 1, TimeUnit.SECONDS)

    }


    @RequestMapping(value = Array("/meteor"), method = Array(RequestMethod.GET))
    @ResponseBody
    def test_meteor(httpServletRequest:HttpServletRequest, httpServletResponse:HttpServletResponse) = {
        val meteor = Meteor.build(httpServletRequest)
        meteor.suspend(-1)

    }



}
