package no.uib.inf101.model.game;

import no.uib.inf101.model.entity.Enemy;
import no.uib.inf101.model.game.node.*;
import no.uib.inf101.model.loot.equipable.EquipmentType;

import java.util.Random;

import static no.uib.inf101.model.game.node.EndNode.EndType.*;

/**
 * Responsible for initializing and connecting all {@link Node}s and {@link Choice}s
 */
public class StoryBuilder {
    private final Random random;
    private final Node gameOver;
    private final Node win;
    private final Node inventory;
    private static final String DEFAULT_RETURN = "Step Back";

    public StoryBuilder(Random random){
        this.random = random;
        this.gameOver = new EndNode("The light leaves your eyes", GAME_OVER);
        this.win = new EndNode("You are victorious!", WIN);
        this.inventory = new InventoryNode();
    }

    /**
     * Builds and connects all {@link Node}s and {@link Choice}s in the story graph.
     *
     * @return the first {@link Node}
     */
    public Node buildStory() {
        Node start = new StoryNode("The air is stale and uninviting");
        Node lookAroundStartRoom = createLookAroundStartRoom();

        start.addChoice(new Choice("Look around", lookAroundStartRoom));
        checkInventoryFrom(start);

        return start;
    }

    private Node createLookAroundStartRoom() {
        Node lookAround = new StoryNode("""
                The stone walls are damp and overgrown with moss.
                Drip points in the ceiling form a steady, echoing rhythm,
                only interrupted by scurrying bugs stirred awake by your presence.
                In the corner, a lone suit of armour sits slumped against the wall, as if resting.
                Ahead, a carved stone entrance leads on.""",
                "You glance around the room."
        );

        Node investigateArmor = createArmourBranch();
        Node investigateCarvings = createCarvingsBranch(lookAround);

        lookAround.addChoice(new Choice("Investigate the armour", investigateArmor));
        lookAround.addChoice(new Choice("Investigate the stone carvings", investigateCarvings));
        checkInventoryFrom(lookAround);

        return lookAround;
    }

    private Node createArmourBranch() {
        Node armorSuccess = new StoryNode("""
                It is unmoving, but through the helmet slit,
                you swear you see a flicker, as if you're being watched.""");
        Node armorFailure = new StoryNode("""
                It is unmoving. Nothing but dust and bones remain of what
                the armour once protected.""");

        Enemy undeadGuardian = new Enemy("Undead Guardian", 60, 6, 10, EquipmentType.HOLLOW_HELM, random);
        Node afterCombat = new StoryNode("The armor lays unmoving. It is hollow.");
        Node guardianCombat = new CombatNode(
                "The armour stirs", undeadGuardian, afterCombat, gameOver
        );

        returnFrom(armorSuccess,"Back away slowly");
        armorSuccess.addChoice(new Choice("Disturb its slumber", guardianCombat));
        returnFrom(armorFailure, DEFAULT_RETURN);
        armorFailure.addChoice(new Choice("Take the helmet", guardianCombat));
        checkInventoryFrom(afterCombat);
        afterCombat.addChoice(new Choice("Proceed through the door", proceedTo(win)));

        return new SkillCheckNode(
                "You crouch down and look at the knight.",
                armorSuccess,
                armorFailure,
                0.7);
    }

    private Node createCarvingsBranch(Node returnNode) {
        Node carvingsSuccess = new StoryNode("""
                You recognise the letters as an ancient script. Though time has
                eroded the full message, you can make out a phrase roughly translating to
                ..."ran out of time".""");
        Node carvingsFailure = new StoryNode("What little remains legible of the carving make no sense to you.");

        Choice stepBack = new Choice("Step back", returnNode);
        carvingsSuccess.addChoice(stepBack);
        carvingsFailure.addChoice(stepBack);

        return new SkillCheckNode(
                "You look closer at the carved glyphs lining the entrance.",
                carvingsSuccess,
                carvingsFailure,
                0.5
        );
    }

    private Node proceedTo(Node nextNode){
        return new TransitionNode("You move on.", nextNode);
    }

    private void returnFrom(Node node, String text){
        node.addChoice(new Choice(text, null, null, ChoiceType.BACK));
    }

    private void checkInventoryFrom(Node node){
        node.addChoice(new Choice("Check belongings", inventory));
    }
}
