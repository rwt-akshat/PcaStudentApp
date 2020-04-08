package com.akrwt.pcastudentapp

data class ModelClass(
    var title: String,
    var message: String,
    var date:String
) {
    constructor() : this("", "","")
}