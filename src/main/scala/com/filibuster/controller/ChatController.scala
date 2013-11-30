package com.filibuster.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestParam, ResponseBody, RequestMapping, RequestMethod}


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


  @RequestMapping(value = Array("/say_something"), method = Array(RequestMethod.GET))
  @ResponseBody
  def say_something(@RequestParam(value="name") name : String,
                    @RequestParam(value="group") group : String,
                    @RequestParam(value="words") words : String) = {
    //TODO: connect to service and pass what was just said

  }

  @RequestMapping(value = Array("/listen"), method = Array(RequestMethod.GET))
  @ResponseBody
  def get_conversation(@RequestParam(value="name") name : String,
                       @RequestParam(value="group") group : String) = {

    //TODO: connect to service and listen for what's being said

  }




}
