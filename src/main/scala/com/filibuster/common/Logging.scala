package com.filibuster.common

import org.slf4j.LoggerFactory


trait Logging {
  protected val _logger = LoggerFactory.getLogger(getClass)
}