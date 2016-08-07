package com.grailsaction

class Post {

    String content
    Date dateCreated

    static constraints = {
        content(blank: false)
    }

    static belongsTo = [ user: UserAccount]

    static mapping = {
        sort dateCreated: "desc"
    }
}
