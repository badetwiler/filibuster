package com.filibuster.dao

import java.sql.ResultSet
import scala.reflect.ClassTag
import com.filibuster.common.Logging

abstract class BaseDao(val _template:RichJdbcTemplate) extends Logging
{
  def queryForInt(sql:String): Option[Int] =
  {
    val i = _template.queryForObject[Integer](sql).getOrElse(return None)

    Some(i.intValue())
  }

  def queryForInt(sql:String, args:Any*): Option[Int] =
  {
    val i = _template.queryForObject[Integer](sql, args.map(_.asInstanceOf[AnyRef]) : _*).getOrElse(return None)

    Some(i.intValue())
  }

  def update(sql:String, args:Any*): Unit =
  {
    _template.update(sql,args)
  }

  def insert(sql:String,args:Any*):Unit =
  {
    _template.update(sql,args)
  }


}

class RichJdbcTemplate(val innerTemplate: org.springframework.jdbc.core.JdbcTemplate) extends org.springframework.scala.jdbc.core.JdbcTemplate(innerTemplate)
{
  def queryFor[T: ClassTag](sql: String, args: Any*)(rowMapper: (ResultSet) => T): Option[T] =
  {
    super.queryForObjectAndMap[T](sql, args.map(_.asInstanceOf[AnyRef]) : _*)((rs, _) => rowMapper(rs))
  }

}