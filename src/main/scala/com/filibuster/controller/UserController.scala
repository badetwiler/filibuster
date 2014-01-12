package com.filibuster.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestParam, ResponseBody, RequestMethod, RequestMapping}


@Controller
@RequestMapping(value=Array("/new-user"))
class UserController {


  @RequestMapping(value = Array("/create"), method = Array(RequestMethod.POST))
  @ResponseBody
  def say_something(@RequestParam(value="email-address") email_address : String,
                    @RequestParam(value="username")      username : String,
                    @RequestParam(value="password")      password : String) = {

    "say something"

  }

}
