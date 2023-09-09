package com.asadbek.firebaserealtimedatabase

class Kontaktlar {
    var uid:String? = null
    var name:String? = null
    var number:String? = null

    constructor()
    constructor(uid: String?, name: String?, number: String?) {
        this.uid = uid
        this.name = name
        this.number = number
    }

}