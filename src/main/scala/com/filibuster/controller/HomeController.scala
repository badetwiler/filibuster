package com.filibuster.controller


import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestParam, ResponseBody, RequestMapping, RequestMethod}
import org.springframework.web.servlet.ModelAndView


@Controller
class HomeController {

  @RequestMapping(value = Array("/"), method = Array(RequestMethod.GET))
  def index():ModelAndView =
  {
      new ModelAndView("index")
  }


    @RequestMapping(value = Array("/home"), method = Array(RequestMethod.GET))
  def welcome():ModelAndView =
  {
      new ModelAndView("home")
  }






}
