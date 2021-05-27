package com.post.utils


class SingletonClass : Methods {
    constructor() : super()

    companion object {
        private var sSoleInstance: SingletonClass? = null

        fun getSingletonInstance():SingletonClass{
            if (sSoleInstance == null) {
                sSoleInstance = SingletonClass()
            }

            return sSoleInstance as SingletonClass
        }
    }
}