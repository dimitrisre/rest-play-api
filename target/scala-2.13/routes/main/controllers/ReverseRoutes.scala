// @GENERATOR:play-routes-compiler
// @SOURCE:conf/routes

import play.api.mvc.Call


import _root_.controllers.Assets.Asset

// @LINE:1
package controllers {

  // @LINE:1
  class ReverseHomeController(_prefix: => String) {
    def _defaultPrefix: String = {
      if (_prefix.endsWith("/")) "" else "/"
    }

  
    // @LINE:6
    def delete(id:Long): Call = {
      
      Call("DELETE", _prefix + { _defaultPrefix } + "car/delete/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
    // @LINE:3
    def getAll(): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "cars")
    }
  
    // @LINE:2
    def get(id:Long): Call = {
      
      Call("GET", _prefix + { _defaultPrefix } + "car/" + play.core.routing.dynamicString(implicitly[play.api.mvc.PathBindable[Long]].unbind("id", id)))
    }
  
    // @LINE:10
    def save(): Call = {
      
      Call("POST", _prefix + { _defaultPrefix } + "car/save")
    }
  
    // @LINE:8
    def update(): Call = {
      
      Call("PUT", _prefix + { _defaultPrefix } + "car/update")
    }
  
    // @LINE:1
    def index(): Call = {
      
      Call("GET", _prefix)
    }
  
  }


}
