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
class ChatController {


  @RequestMapping(value = Array("/test"), method = Array(RequestMethod.GET))
  @ResponseBody
  def test = {
    "success"
  }

  @RequestMapping(value = Array("/say_something"), method = Array(RequestMethod.GET))
  @ResponseBody
  def say_something(@RequestParam(value="words",required=false) words : String) = {
     if (words == null) "" else words
  }




}
