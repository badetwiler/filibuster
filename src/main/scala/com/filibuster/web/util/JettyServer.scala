package com.filibuster.web.util

import com.filibuster.common.Logging
import java.net.InetSocketAddress
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.{DefaultServlet, ServletHolder, ServletContextHandler}
import org.springframework.web.servlet.DispatcherServlet
import java.io.File


class JettyServer extends Logging
{

  private var _server: Server = null
  private final val _port = 8080

  def start()
  {


    try
    {
      _server = new Server(new InetSocketAddress("127.0.0.1", _port))
      _server.setSendServerVersion(false)

      val indexLoc = new File(getClass.getClassLoader.getResource("webroot/index.html").getFile)
      val webrootPath = indexLoc.getParentFile.getAbsolutePath

      _logger.info("Webroot: " + webrootPath)

      val servletContextHandler: ServletContextHandler = new ServletContextHandler(_server, "/", true, false)
      servletContextHandler.setWelcomeFiles(Array("index.html"))
      servletContextHandler.setResourceBase(webrootPath)

      val springServletHolder = new ServletHolder(classOf[DispatcherServlet])

      springServletHolder.setInitParameter("contextConfigLocation", "classpath:webroot/beans.xml")

      springServletHolder.setInitOrder(10) // A positive value here indicates eagerly load; a negative value is lazy

      servletContextHandler.addServlet(springServletHolder, "/api/*")

      var staticContentServlet = new ServletHolder(classOf[DefaultServlet])
      servletContextHandler.addServlet(staticContentServlet, "/*")

      _server.start()

      println

      _logger.info("Jetty Server running on " + _port)
    }
    catch
      {
        case e: Exception => _logger.error("Unable to start JettyServer", e)
      }
  }

  def stop()
  {
    try
    {
      if (_server != null)
      {
        if (_server.isRunning)
        {
          _server.stop()
        }
      }
    }
    catch
      {
        case e: Exception => _logger.warn("Exception shutting down Jetty Server", e)
      }
  }



}
