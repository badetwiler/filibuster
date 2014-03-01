package com.filibuster.controller


import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestHeader, ResponseBody, RequestMethod, RequestMapping}

@Controller
@RequestMapping(value = Array("/resources"))
class ResourcesController
{
    @ResponseBody
    @RequestMapping(value = Array("/1"), method = Array(RequestMethod.GET))
    def new_user (@RequestHeader(value="Authorization") auth_token:String) =
    {

        "your token: " + auth_token
    }


}

