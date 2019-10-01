package seedu.address.flashcard;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import static seedu.address.flashcard.DefaultFlashCards.KANETSU_CARD;
import static seedu.address.flashcard.DefaultFlashCards.MOUNT_BLANC_CARD;

import org.junit.jupiter.api.Test;

import seedu.address.flashcard.exceptions.CardNotFoundException;
import seedu.address.flashcard.exceptions.DuplicateCardException;

public class TagTest {

    @Test
    public void tagEquivalentTest() {
        Tag tunnels1 = new Tag("tunnels");
        Tag tunnels2 = new Tag("tunnels");
        tunnels1.addFlashcard(MOUNT_BLANC_CARD);
        tunnels1.addFlashcard(KANETSU_CARD);
        assertTrue(tunnels1.equals(tunnels2));
    }

    @Test
    public void tagDifferentTest() {
        Tag tunnels1 = new Tag("tunnels");
        Tag tunnels2 = new Tag("long tunnels");
        tunnels1.addFlashcard(MOUNT_BLANC_CARD);
        tunnels2.addFlashcard(MOUNT_BLANC_CARD);
        assertFalse(tunnels1.equals(tunnels2));
    }

    @Test
    public void tagDuplicateCardTest() {
        Tag tunnels = new Tag("tunnels");
        tunnels.addFlashcard(KANETSU_CARD);
        assertThrows(DuplicateCardException.class, () -> tunnels.addFlashcard(KANETSU_CARD));
    }

    @Test
    public void tagValidDeleteCardTest() {
        Tag tunnels = new Tag("tunnels");
        tunnels.addFlashcard(KANETSU_CARD);
        tunnels.addFlashcard(MOUNT_BLANC_CARD);
        tunnels.deleteFlashcard(KANETSU_CARD.getId().getIdentityNumber());
        assertEquals(MOUNT_BLANC_CARD, tunnels.getFlashcards().get(0));
    }

    @Test
    public void tagInvalidDeleteCardTest() {
        Tag tunnels = new Tag("tunnels");
        tunnels.addFlashcard(KANETSU_CARD);
        tunnels.addFlashcard(MOUNT_BLANC_CARD);
        assertThrows(CardNotFoundException.class, () -> tunnels.deleteFlashcard(15));
    }

    @Test
    public void tagDetachFromCardTest() {
        ShortAnswerFlashcard Zhongnanshan = new ShortAnswerFlashcard(
                new ShortAnswerQuestion("How long is the Zhongnanshan tunnel?"),
                new Answer("18062m"));
        Tag tunnel = new Tag("tunnels");
        tunnel.addFlashcard(Zhongnanshan);
        Zhongnanshan.addTag(tunnel);
        tunnel.detachFromAllCards();
        assertTrue(Zhongnanshan.getTags().isEmpty());
    }
}