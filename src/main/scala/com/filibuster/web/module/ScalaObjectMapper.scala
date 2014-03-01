package com.filibuster.web.module

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule

// register's the DefaultScalaModule to convert scala objects to json
// when @ResponseBody is used
class ScalaObjectMapper extends ObjectMapper
{
   registerModule(DefaultScalaModule)
}