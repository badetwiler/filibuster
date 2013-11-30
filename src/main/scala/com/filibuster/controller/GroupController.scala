package com.filibuster.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestParam, ResponseBody, RequestMapping, RequestMethod}
import play.api.libs.json.Json.toJson;
import play.api.libs.json.Json.stringify;
import play.api.libs.json.Json;


/**
 * Created with IntelliJ.
 * by Ben Detwiler
 * Date: 11/30/13
 *
 * work harder, not smarter
 */
@RequestMapping(value=Array("/group"))
class GroupController {

  @RequestMapping(value = Array("/create_group"), method = Array(RequestMethod.GET))
  @ResponseBody
  def create_group(@RequestParam(value="name") words : String) = {
    //TODO: connect to service to create group
  }


  /**
   * return a list of groups the user is in
   * @param name the user requesting the list
   */
  @RequestMapping(value = Array("/groups"), method = Array(RequestMethod.GET))
  @ResponseBody
  def get_groups(@RequestParam(value="name") name : String) = {

    //TODO: connect to service and return results

    //demo return value
    stringify(Json.arr(Seq("family, mIRC, taylor-gang")))
  }

}