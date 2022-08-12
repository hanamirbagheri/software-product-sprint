package com.google.sps.servlets;

import java.io.IOException;
import com.google.gson.Gson;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/** Handles requests sent to the /hello URL. Try running a server and navigating to /hello! */
@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet {
    



  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String[] words = {"Hello my name is Hana", "I am in second year computer engineering", "I like to make pottery", "I love to paint"};
    String json = convertToJsonUsingGson(words);
    response.setContentType("application/json;");
    response.getWriter().println(json);
    
  }

  private String convertToJsonUsingGson(String[] words) {
    Gson gson = new Gson();
    String json = gson.toJson(words);
    return json;
  }
}
 