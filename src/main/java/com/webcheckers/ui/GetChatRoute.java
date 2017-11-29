package com.webcheckers.ui;

import com.google.gson.Gson;
import spark.*;
import java.util.logging.Logger;


import static com.webcheckers.ui.PostChatRoute.msgLog;

public class GetChatRoute implements Route {

    private final Gson gson=new Gson();
    private static final Logger LOG = Logger.getLogger(GetChatRoute.class.getName());

    /**
     * {@inheritDoc}
     */
    @Override
    public Object handle(Request request, Response response) {
      String log="";
      for(int i=0; i<msgLog.size(); i++){
          if(i==msgLog.size()-1){
              log+=msgLog.get(i);
          }else{
              log+=msgLog.get(i)+",";
          }
      }
        return gson.toJson(log);
    }
}
