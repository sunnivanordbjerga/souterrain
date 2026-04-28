package no.uib.inf101.model.game.narration;

import no.uib.inf101.model.game.narration.node.Node;
import no.uib.inf101.model.game.narration.node.StoryNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Tests {@link Choice}.
 */
class ChoiceTest {
    private Node testNode;

    @BeforeEach
    void setup() {
        this.testNode = new StoryNode("You test Choice");
    }

    @Test
    void choiceSanityTest() {
        Choice choice = new Choice("Test", testNode);
        assertEquals("Test", choice.text());
        assertEquals(testNode, choice.nextNode());
    }

    @Test
    void choiceThrowsOnNullText() {
        assertThrows(NullPointerException.class, () -> new Choice(null, testNode));
    }

    @Test
    void choiceThrowsOnBlankText() {
        assertThrows(IllegalArgumentException.class, () -> new Choice(" ", testNode));
        assertThrows(IllegalArgumentException.class, () -> new Choice(" \n" + "\t", testNode));
    }

    @Test
    void choiceThrowsOnNullNode() {
        assertThrows(NullPointerException.class, () -> new Choice("Test", null));
    }
}