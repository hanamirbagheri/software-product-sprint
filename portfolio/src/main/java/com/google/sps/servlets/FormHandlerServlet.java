package com.google.sps.servlets;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import com.google.cloud.datastore.Entity;
import com.google.cloud.datastore.FullEntity;
import com.google.cloud.datastore.KeyFactory;

@WebServlet("/form-handler")
public class FormHandlerServlet extends HttpServlet {

  @Override
  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
    String nameValue = Jsoup.clean(request.getParameter("name-input"), Safelist.none());
    String numberValue = Jsoup.clean(request.getParameter("number-input"), Safelist.none());
    String emailValue = Jsoup.clean(request.getParameter("email-input"), Safelist.none());
    long timestamp = System.currentTimeMillis();

    Datastore datastore = DatastoreOptions.getDefaultInstance().getService();
    KeyFactory keyFactory = datastore.newKeyFactory().setKind("Contact");
    FullEntity taskEntity =
        Entity.newBuilder(keyFactory.newKey())
            .set("name", nameValue)
            .set("number", numberValue)
            .set("email", emailValue)
            .set("timestamp", timestamp)
            .build();
    datastore.put(taskEntity);
    response.sendRedirect("/index.html");
    
  }
}
