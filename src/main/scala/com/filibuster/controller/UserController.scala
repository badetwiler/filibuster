package com.filibuster.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestParam, ResponseBody, RequestMethod, RequestMapping}
import org.springframework.beans.factory.annotation.Autowired
import com.filibuster.data.model.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import com.filibuster.data.service.FilibusterUserDetailsService
import com.filibuster.controller.response.model.FilibusterResponse

@Controller
class UserController
{

  @Autowired
  var userService:FilibusterUserDetailsService = _

  @RequestMapping(value = Array("/user"), method = Array(RequestMethod.GET))
  @ResponseBody
  def new_user () =
  {
    FilibusterResponse(success=true,status="success")
  }

  @RequestMapping(value = Array("/user"), method = Array(RequestMethod.POST))
  @ResponseBody
  def create_user (@RequestParam(value="email_address") email_address : String,
                   @RequestParam(value="username")      username : String,
                   @RequestParam(value="password")      password : String) =
  {

     userService.usernameExists(username) match
     {
       case true => FilibusterResponse(success=false,status="username exists")

       case false =>
         userService.createNewUser(new User(username,password,email_address))
         FilibusterResponse(success=true,status="success")

     }

  }

}
