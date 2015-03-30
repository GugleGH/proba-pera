/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nosov.client.panels;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import ru.nosov.client.WelcomeEntryPoint;
import ru.nosov.client.messages.Message;
import ru.nosov.client.messages.MessageNews;
import ru.nosov.client.messages.MessageNewsLine;
import ru.nosov.client.messages.types.TypeMessage;

/**
 * Панель .
 * @author Носов А.В.
 */
public class PanelNewsLine extends SimplePanel implements AsyncCallback<Message> {
    
    // Variables declaration
    private final String CLASS_NAME = "PanelNewsLine";
//    private MessageServiceAsync msgService;
    private WelcomeEntryPoint parent;
    
    private VerticalPanel panel;
    /** Новости. */
    public static final int TYPE_NEWS = 0;
    /** Ощибка авторизации. */
    public static final int TYPE_LOGIN_ERROR = 1;
    // End of variables declaration
    
    /**
     * Создает новую панель.
     * @param parent
     * @param type тип данных в панели
     */
    public PanelNewsLine(WelcomeEntryPoint parent, int type) {
        this.parent = parent;
        
        panel = new VerticalPanel();
//        initMsgService();
        
        switch (type) {
            case TYPE_LOGIN_ERROR:
                panel.add(getLoginError());
                break;
            default:
                getNews();
        }
        
        this.add(panel);
    }
//    
//    /**
//     * Инициализация сервиса сообщений.
//     */
//    private void initMsgService() {
//        msgService = GWT.create(MessageService.class);
//        ServiceDefTarget endpointMsg = (ServiceDefTarget) msgService;
//        String urlMsg = "/WebProbaPera/ru_nosov_server_services/messageServiceImpl";
//        endpointMsg.setServiceEntryPoint(urlMsg);
//    }
    
    private void getNews() {
        MessageNews msgNews = new MessageNews();
        msgNews.setTypeMessage(TypeMessage.RqNewsLine);
        parent.getMsgService().getMessage(msgNews, this);
    }
    
    private HTMLPanel getLoginError() {
        String htmlString = "Не удается войти.\n"
            + "Пожалуйста, проверьте правильность написания логина и пароля.\n"
            + "\n<ul type=\"disc\">"
            + "<li>Возможно, нажата клавиша CAPS-lock?</li>"
            + "<li>Может быть, у Вас включена неправильная раскладка? (русская или английская)</li>"
            + "<li>Попробуйте набрать свой пароль в текстовом редакторе и скопировать в графу «Пароль»</li>"
            + "</ul>\n"
            + "\n"
            + "Если Вы всё внимательно проверили, но войти всё равно не удается, Вы можете нажать сюда. ";
        return new HTMLPanel(htmlString);
    }

    @Override
    public void onFailure(Throwable caught) {
        Log.error(CLASS_NAME, "No response");
    }

    @Override
    public void onSuccess(Message result) {
        if (result == null) return;
        TypeMessage tm = result.getTypeMessage();
        switch (tm) {
            case RxNews:
                if ( !(result instanceof MessageNewsLine)) break;
                
                MessageNewsLine newsLine = (MessageNewsLine) result;
                if (newsLine.getNewsLine() == null) {
                    panel.add(new HTMLPanel("Упс! А новостей то и нет!"));
                    break;
                }
                panel.clear();
                panel.add(new Label("Пользователь " + newsLine.getUserName()));
                for (MessageNews news : newsLine.getNewsLine())
                    panel.add(new HTMLPanel(news.getNews()));
                
                break;
            case Error:
                break;
        }
    }
}
