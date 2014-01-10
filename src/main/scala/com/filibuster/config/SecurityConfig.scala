package com.filibuster.config

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration._
import com.filibuster.common.Logging
import javax.sql.DataSource


@Configuration
@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter with Logging{

  var dataSource:DataSource = null

  @Autowired
  def configureGlobal(auth: AuthenticationManagerBuilder)  {
    try {
      auth.inMemoryAuthentication().withUser("user").password("password").roles("USER")
      auth.jdbcAuthentication().dataSource(dataSource)
    } catch
    {
      case e:Exception => _logger.error("exception in Security Config",e)
    }
  }

  @Autowired
  def setDataSource(dataSource:DataSource) {
    this.dataSource = dataSource
  }


}