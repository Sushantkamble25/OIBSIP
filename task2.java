import java.util.Random;
import java.util.Scanner;

public class task2 {
    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 100;
    private static final int MAX_ATTEMPTS = 10;
    private static final int MAX_ROUNDS = 3;

    public static void main(String[] args) {
        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        int totalScore = 0;

        System.out.println("WELCOME TO THE NUMBER GUESSING GAME!");
        System.out.println("You will play 3 rounds.");
        System.out.println("Each round gives you 10 chances to guess the correct number.\n");

        for (int i = 1; i <= MAX_ROUNDS; i++) {
            int number = random.nextInt(MAX_RANGE) + MIN_RANGE;
            int attempts = 0;

            System.out.printf("Round %d: Guess a number between %d and %d. You have %d attempts.\n", 
                              i, MIN_RANGE, MAX_RANGE, MAX_ATTEMPTS);
            while (attempts < MAX_ATTEMPTS) {
                System.out.print("Enter your guess: ");
                int guess_number = scanner.nextInt();
                attempts++;

                if (guess_number == number) {
                    int score = MAX_ATTEMPTS - attempts;
                    totalScore += score;
                    System.out.printf("Congratulations! You guessed the number in %d attempts. Round Score = %d\n", 
                                      attempts, score);
                    break;
                } else if (guess_number < number) {
                    System.out.printf("The actual number is higher than %d. Attempts remaining = %d.\n", 
                                      guess_number, MAX_ATTEMPTS - attempts);
                } else {
                    System.out.printf("The actual number is lower than %d. Attempts remaining = %d.\n", 
                                      guess_number, MAX_ATTEMPTS - attempts);
                }
            }

            if (attempts == MAX_ATTEMPTS) {
                System.out.printf("\nRound %d is over.\n", i);
                System.out.println("You have used all your attempts.");
                System.out.printf("The correct number was: %d\n\n", number);
            }
        }

        System.out.printf("Game Over! Your total score is %d.\n", totalScore);
        scanner.close();
    }
}
