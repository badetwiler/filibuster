package com.filibuster.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestParam, ResponseBody, RequestMapping, RequestMethod}



@Controller
class LoginController {

  @RequestMapping(value = Array("/login"), method = Array(RequestMethod.GET))
  def login():String = {
    "login"

  }



}
