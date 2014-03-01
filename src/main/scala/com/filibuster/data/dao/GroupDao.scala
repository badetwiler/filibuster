package com.filibuster.data.dao

import com.filibuster.data.model.Group

trait GroupDao
{

  def save(group:Group): Unit

  def find(id: Int): Option[Group]

  def getAll: List[Group]

  def getByName(name:String) : Option[Group]
  
}
