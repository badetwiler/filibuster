package com.filibuster.controller;

import org.atmosphere.cpr.AtmosphereRequest;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.cpr.AtmosphereResourceEventListenerAdapter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

@RequestMapping(value="atmosphere")
@Controller
public class AtmosphereController {

    ArrayList<HttpSession> subscribers = new ArrayList<>();


    @RequestMapping(value = "/chat", method = RequestMethod.GET)
    @ResponseBody public void onRequest(AtmosphereResource atmosphereResource, HttpSession session) throws IOException {

        atmosphereResource.addEventListener(new AtmosphereResourceEventListenerAdapter() {
            /**
             * {@inheritDoc}
             */
            @Override
            public void onDisconnect(AtmosphereResourceEvent event) {

                if(event.isCancelled()) {
                    System.out.println("is Cancelled");
                    // Unexpected closing. The client didn't send the close message when request.enableProtocol
                } else if(event.isClosedByClient()) {
                    System.out.println("is closed by client");
                    // atmosphere.js has send the close message.
                    // This API is only with 1.1 and up
                }
            }

            @Override
            public void onSuspend(AtmosphereResourceEvent event) {
                System.out.println("is suspended");
            }






        });

        AtmosphereRequest atmosphereRequest = atmosphereResource.getRequest();
        atmosphereResource.suspend();

//        System.out.println(atmosphereRequest.getHeader("negotiating"));
//        if(atmosphereRequest.getHeader("negotiating") == null) {
//            atmosphereResource.resumeOnBroadcast(atmosphereResource.transport() == AtmosphereResource.TRANSPORT.LONG_POLLING).suspend();
//        } else {
//            atmosphereResource.getResponse().getWriter().write("OK");
//        }

        subscribers.add(session);

        System.out.println("Subscribers: " + subscribers.size());

        for(HttpSession httpSession : subscribers) {
            System.out.println(httpSession);
        }

    }

    @RequestMapping(value = "/chat", method = RequestMethod.POST)
    @ResponseBody public void onPost(AtmosphereResource atmosphereResource) throws IOException{

        AtmosphereRequest atmosphereRequest = atmosphereResource.getRequest();

        String body = atmosphereRequest.getReader().readLine().trim();

        String author = body.substring(body.indexOf(":") + 2, body.indexOf(",") - 1);
        String message = body.substring(body.lastIndexOf(":") + 2, body.length() - 2);

        atmosphereResource.getBroadcaster().broadcast(new Data(author, message).toString());

    }

    private final static class Data {

        private final String text;
        private final String author;

        public Data(String author, String text) {
            this.author = author;
            this.text = text;
        }

        public String toString() {
            return "{ \"text\" : \"" + text + "\", \"author\" : \"" + author + "\" , \"time\" : " + new Date().getTime() + "}";
        }

    }

}