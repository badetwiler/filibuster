package com.filibuster.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestParam, ResponseBody, RequestMapping, RequestMethod}
import org.springframework.beans.factory.annotation.{Qualifier, Autowired}
import com.filibuster.data.FilibusterDataManager
import org.springframework.security.authentication.{UsernamePasswordAuthenticationToken, AuthenticationManager}
import javax.servlet.http.{HttpServletRequest, HttpServletResponse}

import org.springframework.security.core.{AuthenticationException, Authentication}
import org.springframework.security.core.context.SecurityContextHolder

@Controller
class LoginController
{

  private val LOGIN_VIEW:String = "login"

}
