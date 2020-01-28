package com.thoughtworks.guessnumber;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {
    private Game game;

    @Before
    public void setUp() {
        String answer = "1234";
        game = new Game(answer);
    }

    @Test
    public void should_return_4A0B_when_given_correct_input() {
        String result = game.check("1234");
        assertThat(result).isEqualTo("4A0B");
    }

    @Test
    public void should_return_wrong_when_given_wrong_input() {
        String result = game.check("1567");
        assertThat(result).isEqualTo("1A0B");

        result = game.check("2478");
        assertThat(result).isEqualTo("0A2B");

        result = game.check("0324");
        assertThat(result).isEqualTo("1A2B");

        result = game.check("5678");
        assertThat(result).isEqualTo("0A0B");

        result = game.check("4321");
        assertThat(result).isEqualTo("0A4B");

        String prompt = game.generatePrompt();
        assertThat(prompt).isEqualTo(
                "input=1567 1A0B\n" +
                        "input=2478 0A2B\n" +
                        "input=0324 1A2B\n" +
                        "input=5678 0A0B\n" +
                        "input=4321 0A4B\n"
        );
    }

    @Test
    public void should_return_wrong_input_when_input_is_duplication() {
        String result = game.check("1123");
        assertThat(result).isEqualTo("Wrong Input, input again");
    }

    @Test
    public void should_return_wrong_input_when_input_is_less_then_4() {
        String result = game.check("12");
        assertThat(result).isEqualTo("Wrong Input, input again");
    }
}
