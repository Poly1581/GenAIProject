package com.example.application.views.home;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.Composite;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import ai.peoplecode.OpenAIConversation;


@PageTitle("Home")
@Menu(icon = "line-awesome/svg/pencil-ruler-solid.svg", order = 0)
@Route(value = "")
@RouteAlias(value = "")
public class HomeView extends Composite<VerticalLayout> {

    private TextField userInput;
    private CityDiv city1;
    private CityDiv city2;

    public class CityDiv extends VerticalLayout {
        private String cityName;
        private String context;
        private Div responseContainer;
        private final OpenAIConversation conversation = new OpenAIConversation("demo", "gpt-4o-mini");

        public CityDiv(String cityName) {
            this.cityName = cityName;
            this.context = "Use up to date information. You are the city " + cityName + ". Respond in the first person from the perspective of " + cityName + ".";

            this.getStyle().set("width", "50%");
            this.getStyle().set("height", "100%");
            this.setAlignItems(FlexComponent.Alignment.CENTER);

            String imagePath = "images/" + this.cityName + ".jpg";
            Image image = new Image(imagePath, "");
            image.getStyle().set("width", "50%");
            this.add(image);

            Div responseContainer = new Div();
            responseContainer.getStyle().set("height", "80%");
            this.add(responseContainer);
            this.responseContainer = responseContainer;

            addMessageDiv(cityName + ": Hello, I am " + cityName + ". It's nice to meet you!");
        }

        public void askQuestion(String question) {
            this.addMessageDiv("You: " + question);
            String answer = this.conversation.askQuestion(this.context, question);
            this.addMessageDiv(cityName + ": " + answer);
        }

        public void addMessageDiv(String contents) {
            Paragraph message = new Paragraph(contents);
            this.responseContainer.add(message);
        }
    }

    private String getUserInput() {
        String userMessage = userInput.getValue();
        userInput.setValue("");
        return userMessage;
    }

    public HomeView() {
        //Make vertical layout section for whole app
        VerticalLayout appContainer = new VerticalLayout();
        appContainer.getStyle().set("width", "100%");
        appContainer.getStyle().set("height", "100%");
        appContainer.setAlignItems(FlexComponent.Alignment.CENTER);
        getContent().add(appContainer);

        //Make horizontal layout section for cities (two for now)
        HorizontalLayout citiesContainer = new HorizontalLayout();
        citiesContainer.getStyle().set("width", "100%");
        citiesContainer.getStyle().set("height", "70%");
        appContainer.add(citiesContainer);

        //Make cities
        city1 = new CityDiv("Hanoi");
        citiesContainer.add(city1);
        city2 = new CityDiv("Madrid");
        citiesContainer.add(city2);

        //Make horizontal layout for buttons
        HorizontalLayout buttonContainer = new HorizontalLayout();
        buttonContainer.getStyle().set("width", "75%");
        buttonContainer.getStyle().set("height", "10%");
        buttonContainer.setJustifyContentMode(FlexComponent.JustifyContentMode.AROUND);
        appContainer.add(buttonContainer);

        //Make buttons
        Button askCity1 = new Button("Ask " + city1.cityName, event -> city1.askQuestion(getUserInput()));
        buttonContainer.add(askCity1);
        Button askBoth = new Button("Ask " + city1.cityName + " and " + city2.cityName, event -> {
            String question = getUserInput();
            city1.askQuestion(question);
            city2.askQuestion(question);
        });
        buttonContainer.add(askBoth);
        Button askCity2 = new Button("Ask " + city2.cityName, event -> city2.askQuestion(getUserInput()));
        buttonContainer.add(askCity2);

        //Make user input
        userInput = new TextField();
        userInput.getStyle().set("height", "20%");
        userInput.getStyle().set("width", "100%");
        appContainer.add(userInput);
    }
}