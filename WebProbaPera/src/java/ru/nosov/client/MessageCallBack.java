/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.client;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author nosov
 */
public class MessageCallBack implements AsyncCallback<String> {
      
    @Override
    public void onFailure(Throwable caught) {
        /* server side error occured */
        //Window.alert("Unable to obtain server response: " + caught.getMessage());	
        Window.alert("MessageCallBack no response");	
    }
    @Override
    public void onSuccess(String result) {
        /* server returned result, show user the message */
        Window.alert("MessageCallBack\n" + result);
    }	   
}
