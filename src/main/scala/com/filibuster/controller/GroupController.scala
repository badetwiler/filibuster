package com.filibuster.controller


import org.springframework.web.bind.annotation.{RequestParam, ResponseBody, RequestMapping, RequestMethod}
import play.api.libs.json.Json.stringify
import play.api.libs.json.Json
import org.springframework.stereotype.Controller
import org.springframework.beans.factory.annotation.Autowired
import com.filibuster.data.service.FilibusterGroupService
import com.filibuster.controller.response.model.FilibusterResponse

@Controller
class GroupController
{


  @Autowired
  var groupService:FilibusterGroupService = _


  @RequestMapping(value = Array("/group"), method = Array(RequestMethod.POST))
  @ResponseBody
  def create_group(@RequestParam(value="name") groupname : String,
                   @RequestParam(value="owner") owner : String) =
  {

    groupService.usernameExists(owner) match
    {
      case false => FilibusterResponse(success = false, status = "owner does not exist")

      case true =>
          groupService.createGroup(groupname)
          groupService.addGroupOwner(groupname,owner)
          match
          {
            case false => FilibusterResponse(success = false, status = "failed to create group")

            case true =>  FilibusterResponse(success = true, status = "")

          }


    }



  }



}
