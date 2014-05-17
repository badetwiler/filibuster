package com.filibuster.controller;

import org.atmosphere.cpr.AtmosphereRequest;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.cpr.AtmosphereResourceEventListenerAdapter;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;
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

        System.out.println(atmosphereRequest.getHeader("negotiating"));
        if(atmosphereRequest.getHeader("negotiating") == null) {
            atmosphereResource.resumeOnBroadcast(atmosphereResource.transport() == AtmosphereResource.TRANSPORT.LONG_POLLING).suspend();
        } else {
           atmosphereResource.getResponse().getWriter().write("OK");
        }

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
        JSONParser jsonParser = new JSONParser();

        JSONObject obj;
        try{
            obj = (JSONObject)jsonParser.parse(body);
            String name = (String)obj.get("author");
            String message = (String)obj.get("message");
            atmosphereResource.getBroadcaster().broadcast(new ChatMessage(name, message).toString());

        }catch(ParseException e) {
            e.printStackTrace();
        }
    }

    private final class ChatMessage {
        private String message;
        private String author;
        private long time;

        public ChatMessage() {
            this("", "");
        }

        public ChatMessage(String author, String message){
            this.author = author;
            this.message = message;
            this.time = new Date().getTime();
        }

        public String getMessage(){
            return message;
        }

        public String getAuthor(){
            return author;
        }

        public void setAuthor(String author){
            this.author = author;
        }

        public void setMessage(String message){
            this.message = message;
        }

        public long getTime(){
            return time;
        }

        public void setTime(long time){
            this.time = time;
        }
        public String toString() {
            return "{ \"author\" : \"" + author + "\", \"message\" : \"" + message + "\" , \"time\" : " + Long.toString(time) + "}";
        }


    }

}