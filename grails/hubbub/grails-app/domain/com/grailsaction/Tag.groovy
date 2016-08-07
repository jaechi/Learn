package com.grailsaction

import org.apache.catalina.User

class Tag {
    String name
    UserAccount userAccount

    static constraints = {
        name(blank: false)
    }
    static hasMany = [ posts: Post]
    static belongsTo = [ UserAccount, Post]
}
