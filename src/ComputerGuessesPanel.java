import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

/**
 * UI screen for when the computer is guessing a number
 *
 * Displays the computer's guesses and processes human's answers
 * Tracks the computer's guesses
 *
 * TODO: refactor this class
 */
public class ComputerGuessesPanel extends JPanel {

    private ComputerGuessesGame game;

    private final JPanel cardsPanel;
    private final Consumer<GameResult> gameFinishedCallback;

    public ComputerGuessesPanel(JPanel cardsPanel, Consumer<GameResult> gameFinishedCallback){
        // Init class variables
        game = new ComputerGuessesGame();

        this.cardsPanel = cardsPanel;
        this.gameFinishedCallback = gameFinishedCallback;

        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

        JLabel guessMessage = new JLabel("I guess ___.");
        guessMessage.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(guessMessage);
        guessMessage.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.add(Box.createRigidArea(new Dimension(0, 40)));

        // User prompt
        JLabel prompt = new JLabel("Your number is...");
        this.add(prompt);
        prompt.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(Box.createRigidArea(new Dimension(0,10)));

        // Add "is lower" button
        addButton("Lower", GuessResult.LOW, guessMessage);
        this.add(Box.createRigidArea(new Dimension(0,10)));

        // Add "is equal" button
        addButton("Equal", GuessResult.CORRECT, guessMessage);
        this.add(Box.createRigidArea(new Dimension(0,10)));

        // Add "is higher" button
        addButton("Higher", GuessResult.HIGH, guessMessage);

        this.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent e) {
                game.reset();
                guessMessage.setText("I guess " + game.getLastGuess() + ".");
            }
        });
    }

    /**
     * Adds a button with function that runs on click
     *
     * @param label Label displayed on button
     * @param res Guess result case the button handles
     * @param guessMessage JLabel element to update
     */
    private void addButton(String label, GuessResult res, JLabel guessMessage) {
        JButton btn = new JButton(label);
        btn.addActionListener(e -> handleGuess(res, guessMessage));
        this.add(btn);
        btn.setAlignmentX(Component.CENTER_ALIGNMENT);
    }

    /**
     * Handles a guess
     *
     * @param res Current GuessResult
     * @param guessMessage JLabel element to update
     */
    private void handleGuess(GuessResult res, JLabel guessMessage) {
        if (res == GuessResult.CORRECT) endGame(guessMessage);
        else guessMessage.setText("I guess " + game.nextGuess(res) + ".");
    }

    /**
     * Ends the game
     *
     * @param guessMessage JLabel element to update
     */
    private void endGame(JLabel guessMessage) {
        guessMessage.setText("I guess ___.");

        // Send the result of the finished game to the callback
        GameResult result = new GameResult(false, game.getLastGuess(), game.getNumGuesses());
        gameFinishedCallback.accept(result);

        CardLayout cardLayout = (CardLayout) cardsPanel.getLayout();
        cardLayout.show(cardsPanel, ScreenID.GAME_OVER.name());
    }

}
