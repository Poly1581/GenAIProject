Brandon Paulsen and Carissa Co - Gen-AI project for USF CS514 bridge software development class

Project description: 

In this project, we created a website to ask questions to two cities: Hanoi and Madrid, and a response from each city is generated using openAI.
This website would be helpful if you are debating on traveling to two different cities, and instead of doing separate Google searches on each one, you could have one webiste where you can compare side by side the differences between each city. Currently, our project is set on two cities, but if we were to expand, we would add a feature where the user can choose which two cities they want to compare. In addition, the questions that the user can ask are basically limitless, as there are no restrictions on tye type or length of input from the user. We used Vadiin to build this webiste.

There is an option to ask a question to once city, or both cities at the same time. We asked openAI to use this as context to generate a response to each question: this.context = "Use up to date information. You are the city " + cityName + ". Respond in the first person from the perspective of " + cityName + ".";. This ensured that each AI response would be from the perspective of the specified city. This was very similar to our peoplecodeAI lab where you ask questions to Socrates. Creating the logic for asking one city or both cities a question was quite simple: for aking a question to one city, we modified the event to only generate an answer for one city based on the user input, but for asking a question to both cities, a local varaible called question was created to hold the user input, and the event was modified to ask the question to both cities. Since the logic for generating a question and answer for this website was similar to the lab we did in class, most of the time spent on the project was figuring out how to make the layout for the website. This was accomplished through making a main vertical layout for the whole app, the adding the components sich as the title, images, and subtitles as needed, then making a horizonal layout inside the vertical layout in order to show the cities side by side, along with the questions buttons, and the responses. Another feature that we struggled with was figuring out how to make the app do nothing if a question was not entered. In order to resolve this issue, we modified out askQuestion method:
     public void askQuestion(String question) { // method to ask question to each city and generate the response
            if (!question.trim().equals("")){
                this.addMessageDiv("You: " + question);
                String answer = this.conversation.askQuestion(this.context, question); // generates AI answer
                this.addMessageDiv(cityName + ": " + answer); // displays AI answer
            }

        }

We added an if statement if (!question.trim().equals("")){ to this method so that a question and answer would only be displayed if the question was not an empty string. This helped us clean up our website during testing, so that it looked neater and provided a better user experience.
Another feature that we could expand on in order to improve the user experience is to organize the page so that the questions start on the same spot on the screen. This would be helpful if the user were to ask many questions to each city, so that if each city generates responses of different lengths, the user would not have to keep scrolling up and down to look at each response. 
