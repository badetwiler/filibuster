package com.filibuster.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestParam, ResponseBody, RequestMapping, RequestMethod}
import org.springframework.beans.factory.annotation.Autowired
import com.filibuster.data.FilibusterDataManager
import com.filibuster.model.User

@Controller
class LoginController @Autowired() (dataManager:FilibusterDataManager)
{

  @RequestMapping(value = Array("/login"), method = Array(RequestMethod.GET))
  def showLogin():String = {
    "login"
  }

  @RequestMapping(value = Array("/login"), method = Array(RequestMethod.POST))
  @ResponseBody
  def login(@RequestParam("username") username:String,
            @RequestParam("password") password:String):String = {
    "tried to login"
  }

  @RequestMapping(value = Array("/createUser"), method = Array(RequestMethod.GET))
  def createUser(@RequestParam("username") username:String,
                 @RequestParam("password") password:String,
                 @RequestParam("email")    email:String     ) =
  {
    val salt = ""

    val user = User(username,1,email,salt,password) //need to hash pw first
    dataManager.insertUser(user)

  }



}
