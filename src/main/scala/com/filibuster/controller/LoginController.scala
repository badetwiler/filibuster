package com.filibuster.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestParam, ResponseBody, RequestMapping, RequestMethod}
import org.springframework.beans.factory.annotation.{Qualifier, Autowired}
import com.filibuster.data.FilibusterDataManager
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.authentication.{UsernamePasswordAuthenticationToken, AuthenticationManager}
import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

import org.springframework.security.core.{AuthenticationException, Authentication}
import org.springframework.security.core.context.SecurityContextHolder

@Controller
class LoginController @Autowired() (dataManager:FilibusterDataManager)
{

  private val LOGIN_VIEW:String = "login"

  //implement this for auto redirect after login
  @Autowired()
  var successHandler:AuthenticationSuccessHandler = _

  @Autowired()
  @Qualifier("authenticationManager")
  var authenticationManager: AuthenticationManager =  _

  @RequestMapping(value = Array("/login"))
  def showLogin(@RequestParam(value="error", required = false) error: String) :String =
  {
    LOGIN_VIEW
  }


  @RequestMapping(value = Array("/login"), method = Array(RequestMethod.POST))
  def login(request:HttpServletRequest,
            response: HttpServletResponse,
            @RequestParam(value="username") username : String,
            @RequestParam(value="password") password : String): String =
  {

    login_successful(username,password) match
    {
        case true => response.sendRedirect("/home")

        case false => response.sendRedirect("/login")

    }

    null

  }

  private def login_successful(username:String, password:String):Boolean =
  {
    val token:Authentication = new UsernamePasswordAuthenticationToken(username, password)

    try
    {

        val authentication:Authentication = authenticationManager.authenticate(token)
        SecurityContextHolder.getContext.setAuthentication(authentication)
        true
    }
    catch
      {
          case e:Exception => false
      }

  }



}
