package com.filibuster.web.util

import com.filibuster.common.Logging
import java.net.{URLDecoder, InetSocketAddress}
import org.eclipse.jetty.server.Server
import org.eclipse.jetty.servlet.{FilterHolder, DefaultServlet, ServletHolder, ServletContextHandler}
import org.springframework.web.servlet.DispatcherServlet
import java.io.File
import org.springframework.core.io.ClassPathResource
import org.eclipse.jetty.util.resource.Resource
import org.springframework.web.filter.DelegatingFilterProxy
import javax.servlet.DispatcherType
import java.util
import org.springframework.web.context.ContextLoaderListener


class JettyServer extends Logging
{

  private var _server: Server = null
  private final val _port = 8080

  def start()
  {


    try
    {
      _server = new Server(new InetSocketAddress("0.0.0.0", _port))

      val indexLoc = new File(getClass.getClassLoader.getResource("webroot/static/index.html").getFile)

      val webrootPath = isRunningFromJar match {
        case true =>
            new ClassPathResource("webroot/static/index.html").getURI.toString
        case false =>
          val indexLoc = new File(getClass.getClassLoader.getResource("webroot/static/index.html").getFile)
          indexLoc.getParentFile.getAbsolutePath
      }

      _logger.info("Webroot: " + webrootPath + ", is running from jar: " + isRunningFromJar)

      val servletContextHandler: ServletContextHandler = new ServletContextHandler(_server, "/", true, false)
      servletContextHandler.setWelcomeFiles(Array("welcome.html"))
      servletContextHandler.addFilter(new FilterHolder(new DelegatingFilterProxy("springSecurityFilterChain")), "/*", util.EnumSet.allOf(classOf[DispatcherType]))
      servletContextHandler.addEventListener(new ContextLoaderListener())
      servletContextHandler.setInitParameter("contextConfigLocation", "classpath:application-context.xml")


      val springServletHolder = new ServletHolder(classOf[DispatcherServlet])
      springServletHolder.setInitParameter("contextConfigLocation", "classpath:web-context.xml")
      springServletHolder.setInitOrder(10) // A positive value here indicates eagerly load; a negative value is lazy


      val staticContentServlet = new ServletHolder(new DefaultServlet {
        private final val resourcesPrefix = "/webroot"

        override def getResource(pathInContext:String):Resource =
        {
          val resourceString = pathInContext.startsWith(resourcesPrefix) match
          {
            case true => pathInContext
            case false => resourcesPrefix + pathInContext
          }

          val url = getClass.getResource(resourceString)

          try {
            Resource.newResource(url)
          } catch {
            case e: Exception => null
          }
        }

      })

      staticContentServlet.setInitOrder(1)

      servletContextHandler.addServlet(staticContentServlet, "/static/*")
      servletContextHandler.addServlet(springServletHolder, "/*")


      _server.start()
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


  private def getRunningJarPath: Option[String] =
  {
    val path = getClass.getProtectionDomain.getCodeSource.getLocation.getPath

    try
    {
      Option(URLDecoder.decode(path, "UTF-8"))
    }
    catch
      {
        case e: Exception =>
          _logger.warn("Unable to locate jar path", e)
          return None
      }
  }

  private def isRunningFromJar: Boolean = getRunningJarPath.getOrElse(return false).endsWith(".jar")


}
