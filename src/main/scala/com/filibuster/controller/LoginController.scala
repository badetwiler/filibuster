package com.filibuster.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestParam, ResponseBody, RequestMapping, RequestMethod}
import org.springframework.beans.factory.annotation.{Qualifier, Autowired}
import com.filibuster.data.FilibusterDataManager
import com.filibuster.model.User
import com.filibuster.data.model.LoginForm
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.authentication.{UsernamePasswordAuthenticationToken, AuthenticationManager}
import javax.servlet.http.{HttpServletRequest, HttpServletResponse}
import org.springframework.validation.BindingResult
import javax.validation.Valid
import org.springframework.security.core.{AuthenticationException, Authentication}
import org.springframework.security.core.context.SecurityContextHolder

@Controller
class LoginController @Autowired() (dataManager:FilibusterDataManager)
{

  private val LOGIN_VIEW:String = "login"

  //implement this for auto redirect after login
  //@Autowired()
  //var successHandler:AuthenticationSuccessHandler = _

  @Autowired()
  @Qualifier("authenticationManager")
  var authenticationManager: AuthenticationManager =  _

  @RequestMapping(value = Array("/login.do"))
  def showLogin(loginForm: LoginForm) :String =
  {
    LOGIN_VIEW
  }

  @RequestMapping(value = Array("/login"), method = Array(RequestMethod.POST))
  def login(request:HttpServletRequest,
                  response: HttpServletResponse,
                  @Valid loginForm: LoginForm,
                  result: BindingResult) :String =
  {

    val j_username = loginForm.getJ_username
    val j_password = loginForm.getJ_password

    if(!j_username.equals(j_password)) {
      result.reject("login.fail","Bad username/password")
    }
    if(result.hasErrors) {
      return LOGIN_VIEW
    }

    val token:Authentication = new UsernamePasswordAuthenticationToken(j_username, j_password)

    try
    {
      val authentication:Authentication = authenticationManager.authenticate(token)
      SecurityContextHolder.getContext.setAuthentication(authentication)
      //successHandler.onAuthenticationSuccess(request, response, authentication)
      null
    }
    catch
    {
      case error:AuthenticationException =>
        result.reject("login.fail", error.getMessage);
        LOGIN_VIEW
    }

  }


}
