package com.filibuster.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{ResponseBody, RequestMapping, RequestMethod}


/**
 *
 * Last Updated: 11/25/13
 *
 * @author Ben Detwiler
 *
 */

@Controller
@RequestMapping(value=Array("/filibuster"))
class ChatController {


  @RequestMapping(value = Array("/test"), method = Array(RequestMethod.GET))
  @ResponseBody
  def test = {
    "success"
  }


}
