package com.mycompany.weather.model

class BuilderURL {
    private var url: String = ""
    private val params = ArrayList<String>()

    fun setURL(url: String): BuilderURL {
        this.url = url
        return this
    }

    fun addParam(key: String, value: String): BuilderURL {
        params.add("$key=$value")
        return this
    }

    fun build(): String {
        var uri = "${this.url}?"
        for (param in params) {
            uri += "$param&"
        }
        return uri.substring(0, uri.length - 1)
    }
}