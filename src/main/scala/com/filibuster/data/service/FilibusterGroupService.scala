package com.filibuster.data.service

trait FilibusterGroupService
{

    def usernameExists(username:String): Boolean

    def addGroupOwner(groupname:String, owner:String): Boolean

    def createGroup(groupname:String): Boolean
}
