package com.example.application.views.home;

import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import ai.peoplecode.OpenAIConversation;

import java.util.ArrayList;


@PageTitle("Home")
@Menu(icon = "line-awesome/svg/pencil-ruler-solid.svg", order = 0)
@Route(value = "")
@RouteAlias(value = "")
public class HomeView extends Composite<VerticalLayout> {

    private TextField userInput;
    private City city1;
    private City city2;

    class City {
        private String cityName;
        private String context;
        private Div responseContainer;
        private final OpenAIConversation conversation = new OpenAIConversation("demo", "gpt-4o-mini");

        public City(Div responseContainer, String cityName) {
            this.responseContainer = responseContainer;
            this.cityName = cityName;
            this.context = "Use up to date information. You are arguing on in favor of visiting " + cityName;
        }

        public void askQuestion(String question) {
            String answer = this.conversation.askQuestion(this.context, question);
            Paragraph response = new Paragraph(answer);
            this.responseContainer.add(response);
        }
    }

//    class MyClickListener
//            implements ComponentEventListener<ClickEvent<Button>> {
//        int count = 0;
//
//        @Override
//        public void onComponentEvent(ClickEvent<Button> event) {
//            //event.getSource()
//            //        .setText("You have clicked me " + (++count) + " times");
//            String reply= conversation.askQuestion("You are Plato", askText.getValue());
//            replyText.setText(reply);
//        }
//    }

    private String getUserInput() {
        String userMessage = userInput.getValue();
        userInput.setValue("");
        return userMessage;
    }

    public HomeView() {
        VerticalLayout appContainer = new VerticalLayout();
        appContainer.getStyle().set("width", "100%");
        appContainer.getStyle().set("height", "100%");

        HorizontalLayout citiesContainer = new HorizontalLayout();
        citiesContainer.getStyle().set("width", "100%");
        citiesContainer.getStyle().set("height", "70%");
        appContainer.add(citiesContainer);

        VerticalLayout city1Container = new VerticalLayout();
        city1Container.getStyle().set("width", "50%");
        city1Container.getStyle().set("height", "100%");
        citiesContainer.add(city1Container);

        String city1ImagePath = "images/Hanoi.jpg";
        Image city1Image = new Image(city1ImagePath, "");
        city1Image.getStyle().set("width", "50%");
        city1Container.add(city1Image);

        Div city1Response = new Div();
        city1Response.setHeight("80%");
        city1Container.add(city1Response);

        Paragraph city1Greeting = new Paragraph("Hello, I am Madrid.");
        city1Greeting.getStyle().set("width", "100%");
        city1Container.add(city1Greeting);

        VerticalLayout city2Container = new VerticalLayout();
        city2Container.getStyle().set("width", "50%");
        city2Container.getStyle().set("height", "100%");
        citiesContainer.add(city2Container);

        String city2ImagePath = "images/Madrid.jpg";
        Image city2Image = new Image(city2ImagePath, "");
        city2Image.getStyle().set("width", "50%");
        city2Container.add(city2Image);

        Div city2Response = new Div();
        city2Response.getStyle().set("height", "80%");
        city2Container.add(city2Response);

        Paragraph city2Greeting = new Paragraph("Hello, I am Madrid.");
        city2Greeting.getStyle().set("width", "100%");
        city2Container.add(city2Greeting);


        HorizontalLayout buttonContainer = new HorizontalLayout();
        buttonContainer.getStyle().set("width", "100%");
        buttonContainer.getStyle().set("height", "10%");
        appContainer.add(buttonContainer);

        Button askCity1 = new Button("Ask city 1");
        buttonContainer.add(askCity1);

        Button askBothCities = new Button("Ask both cities");
        buttonContainer.add(askBothCities);

        Button askCity2 = new Button("Ask city 2");
        buttonContainer.add(askCity2);

        userInput = new TextField();
        userInput.getStyle().set("height", "20%");
        userInput.getStyle().set("width", "100%");
        appContainer.add(userInput);
        getContent().add(appContainer);

    }
}
