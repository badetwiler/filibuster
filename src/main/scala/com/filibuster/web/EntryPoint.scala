package com.filibuster.web

import com.filibuster.web.util.JettyServer
import com.filibuster.common.Logging

/**
 * Created by ben on 12/14/13.
 */
object EntryPoint extends App with Logging{

  val jettyServer = new JettyServer()

  Runtime.getRuntime.addShutdownHook(new Thread
  {
    override def run() =
    {
      _logger.info("Shutting down..")

      scala.util.control.Exception.ignoring(classOf[Throwable])
      {
        jettyServer.stop()
      }

      _logger.info("Shutdown.")
    }
  })

  jettyServer.start()

  _logger.info("Started.")

  // TODO: Probably don't need to keep this around assuming we have at least one other non-daemon thread running.
  while (true)
  {
    Thread.sleep(1000)
  }
}
