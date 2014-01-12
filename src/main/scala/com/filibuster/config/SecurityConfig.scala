package com.filibuster.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.configuration._
import com.filibuster.common.Logging
import javax.sql.DataSource


@Configuration
@EnableWebSecurity
@Autowired
class SecurityConfig(val dataSource:DataSource) extends WebSecurityConfigurerAdapter with Logging{


  @Autowired
  def configureGlobal(auth: AuthenticationManagerBuilder)  {
    try {
      auth
        .jdbcAuthentication()
        .dataSource(dataSource)
        .usersByUsernameQuery("SELECT username,password,active from users where username = ?")
    } catch
    {
      case e:Exception => _logger.error("exception in Security Config",e)
    }
  }




}