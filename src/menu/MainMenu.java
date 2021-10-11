package menu;

import answers.Answer;
import questions.Question;
import util.Utility;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Documentation code that will be shown
 *
 * @author Vija
 * @2021., Riga, Latvia, Earth
 */

public class MainMenu {
    // for the Menu print loop boolean value to decide if we want to print the menu again.
    public static boolean doWeWantToContinue = true;
    // predefined questions, so that we have already some data when we start
    public static Question question1 = new Question("Some test question text.", "Al", "first");
    public static Question question2 = new Question("Some more test question text.", "Ali", "second");
    public static Question question3 = new Question("Some new test question text.", "Ali-Baba", "third");
    //public static Question question4;
    public static Question[] qArray = {question1, question2, question3, null, null, null, null, null, null, null};
    // the counter of actually existing questions
    public static int numberOfQuestions = 3;

    public static void printMenuAndCallSelectedAction() {
        Scanner scanner = new Scanner(System.in);

        System.out.println(String.format("Hello! Welcome to the Question App %n"
                + "Please select an action from below, by writing in the number: %n"
                + "1: See existing questions %n"
                + "2: See existing answers %n"
                + "3: Create a new question %n"
                + "4: Answer a question %n"
                + "5: Change existing answer %n"
                + "6: Search a word in answers%n"
                + "0: Exit"));
        int menuNumber = scanner.nextInt();
        scanner.nextLine();

        switch (menuNumber) {
            case 1:
                System.out.println("Here will be questions");
                System.out.println(Utility.GREEN);
                System.out.println(Arrays.toString(qArray));
                System.out.println(Utility.VIOLET + "----");
                break;
            case 2:
                printOutAllAnswers(scanner, menuNumber);
                break;
            case 3:
                //creating new question logic
                createNewQuestion(scanner);
                // Answer answer1 = new Answer("Some test answer text.", "Max", "identifier");
                break;
            case 4:
                answerMenu(scanner);
                break;
            case 5:
                answerUpdateMenu(scanner);
                break;
            case 0:
                exit();
                break;
            default:
                System.out.println("Did not recognize this selection, please, try again!");
                break;
        }
    }


    private static void answerUpdateMenu(Scanner scanner) {
        System.out.println("For which of the questions do you want to change your answer? [1-" + numberOfQuestions + "]");
        int menuNumber = scanner.nextInt() - 1;
        scanner.nextLine();

        if (menuNumber >= 0
                && menuNumber <= numberOfQuestions - 1) {
            System.out.println(Arrays.toString(qArray[menuNumber].getAnswer()));
            System.out.println(String.format("For which answer do you want to change the information? [1-%d]", qArray[menuNumber].getAnswer().length));
            int answerSelection = scanner.nextInt() - 1;
            scanner.nextLine();
            Answer selectedAnswer = qArray[menuNumber].getAnswer()[answerSelection];
            if (selectedAnswer != null) {
                System.out.println("What is the number of likes?");
                answerSelection = scanner.nextInt();
                scanner.nextLine();
                selectedAnswer.setLikes(answerSelection);

                System.out.println("What is the number of dislikes?");
                answerSelection = scanner.nextInt();
                scanner.nextLine();
                selectedAnswer.setDislikes(answerSelection);

                System.out.println("Is this answer acceptable one? Y/N: ");
                String stringInput = scanner.nextLine();

                selectedAnswer.setAcceptedAnswer(// Yes / Y  / yes / y
                        stringInput.toLowerCase().startsWith("y"));
                System.out.println("The new version of the answer: " + selectedAnswer);
            } else {
                System.out.println("This answer doesn't exist!");
            }
        }
    }


    public static void exit() {
        doWeWantToContinue = false;
    }


    public static void answerMenu(Scanner scanner) {
        System.out.println("Welcome to the answer creator! What is your name?");
        String name = scanner.nextLine();

        System.out.println(String.format("Which of the questions do you want to answer? [1-%d]", numberOfQuestions));
        int inputSelection = scanner.nextInt() - 1;
        scanner.nextLine();

        if (inputSelection >= 0
                && inputSelection <= numberOfQuestions - 1) {
            String answerText;
            Answer answer;
            System.out.println("You are answering this question: " + qArray[inputSelection]);
            System.out.println(String.format("Thank you %s ,please write your answer: ", name));
            answerText = scanner.nextLine();
            answer = new Answer(answerText, name, qArray[inputSelection].identifier);
            qArray[inputSelection].setAnswer(answer);
        } else {
            System.out.println("There is no questions with this number, please try again");
        }
    }


    public static void printOutAllAnswers(Scanner scanner, int menuNumber) {
        System.out.println(String.format("Here will be answers - what question do you want to check? [1-%d]", numberOfQuestions));
        int questionSelection = scanner.nextInt() - 1;
        scanner.nextLine();
        if (questionSelection >= 0 //can be also extended with a question to user  which of the answers to show (not all)
                && menuNumber <= numberOfQuestions - 1) {
            System.out.println(Arrays.toString(qArray[questionSelection].getAnswer()));
        } else {
            System.out.println("There is no questions with this number, please try again.");
        }
    }


    public static void createNewQuestion(Scanner scanner) {
        // creating new question
        System.out.println(numberOfQuestions);
        System.out.println(qArray.length);
        System.out.println(" ---- ");
        if (numberOfQuestions == qArray.length) {
            System.out.println("Sorry, our data space is full already");
        } else {
            System.out.println("Welcome to the question creator, what is your name:");
            String name = scanner.nextLine();

            System.out.println(String.format("Thank you %s, please write in your question: ", name));
            String questionText = scanner.nextLine();
            Question newlyCreatedQuestion = new Question(questionText, name, "id-" + numberOfQuestions);

            qArray[numberOfQuestions] = newlyCreatedQuestion;
            numberOfQuestions++;
        }
    }

}

