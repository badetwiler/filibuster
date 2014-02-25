package com.filibuster.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestParam, ResponseBody, RequestMethod, RequestMapping}
import org.springframework.beans.factory.annotation.Autowired
import com.filibuster.data.model.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import com.filibuster.data.service.FilibusterUserDetailsService

@Controller
class UserController
{

  @Autowired
  var userService:FilibusterUserDetailsService = _


  @RequestMapping(value = Array("/new-user"), method = Array(RequestMethod.GET))
  def new_user () =
  {
      "newuser"
  }


  @RequestMapping(value = Array("/create-user"), method = Array(RequestMethod.POST))
  @ResponseBody
  def create_user (@RequestParam(value="email_address") email_address : String,
                   @RequestParam(value="username")      username : String,
                   @RequestParam(value="password")      password : String) =
  {

     userService.createNewUser(new User(username,password,email_address))
    ""
  }

}
