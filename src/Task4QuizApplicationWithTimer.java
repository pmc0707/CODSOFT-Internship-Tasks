import java.util.ArrayList;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class Question {
    String question;
    String[] options;
    String correctAnswer;

    public Question(String question, String[] options, String correctAnswer) {
        this.question = question;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
}

class Quiz {
    private final ArrayList<Question> questions;
    private int currentQuestionIndex;
    private int score;
    private final Scanner scanner;
    private final Timer timer;
    private TimerTask task;
    private boolean timeUp;

    public Quiz() {
        questions = new ArrayList<>();
        scanner = new Scanner(System.in);
        currentQuestionIndex = 0;
        score = 0;
        timer = new Timer();
    }

    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        quiz.addQuestion(new Question("What is the capital of France?", new String[]{"A. Berlin", "B. Madrid", "C. Paris", "D. Rome"}, "C"));
        quiz.addQuestion(new Question("Who wrote 'Hamlet'?", new String[]{"A. Charles Dickens", "B. William Shakespeare", "C. Mark Twain", "D. Jane Austen"}, "B"));
        quiz.addQuestion(new Question("What is the largest planet in our Solar System?", new String[]{"A. Earth", "B. Mars", "C. Jupiter", "D. Saturn"}, "C"));

        quiz.start();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public void start() {
        for (currentQuestionIndex = 0; currentQuestionIndex < questions.size(); currentQuestionIndex++) {
            timeUp = false;
            task = new TimerTask() {
                public void run() {
                    timeUp = true;
                    System.out.println("\nTime's up!");
                }
            };

            timer.schedule(task, 10000); // Set timer for 10 seconds per question
            askQuestion(questions.get(currentQuestionIndex));
            task.cancel();

            if (!timeUp) {
                String answer = scanner.nextLine();
                checkAnswer(answer);
            }
        }

        System.out.println("Quiz over! Your score: " + score + "/" + questions.size());
        scanner.close();
    }

    private void askQuestion(Question question) {
        System.out.println(question.question);
        for (int i = 0; i < question.options.length; i++) {
            System.out.println((char) ('A' + i) + ": " + question.options[i]);
        }
        System.out.print("Your answer (A, B, C, D): ");
    }

    private void checkAnswer(String answer) {
        Question question = questions.get(currentQuestionIndex);
        if (question.correctAnswer.equalsIgnoreCase(answer)) {
            score++;
            System.out.println("Correct!");
        } else {
            System.out.println("Wrong! The correct answer is " + question.correctAnswer);
        }
    }
}
