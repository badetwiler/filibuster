package com.filibuster.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestParam, ResponseBody, RequestMethod, RequestMapping}
import com.filibuster.data.FilibusterDataManager
import org.springframework.beans.factory.annotation.Autowired
import com.filibuster.model.User

@Controller
class UserController @Autowired() (dataManager:FilibusterDataManager)
{

  @RequestMapping(value = Array("/new-user"), method = Array(RequestMethod.GET))
  def new_user () = {
    "newuser"
  }


  @RequestMapping(value = Array("/create-user"), method = Array(RequestMethod.POST))
  def create_user (@RequestParam(value="email_address") email_address : String,
                    @RequestParam(value="username")      username : String,
                    @RequestParam(value="password")      password : String) = {

      dataManager.insertUser(User(username, 1, email_address, "no_salt", password))


  }

}
