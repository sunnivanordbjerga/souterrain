package no.uib.inf101.model.game;

import no.uib.inf101.model.entity.Enemy;
import no.uib.inf101.model.game.node.*;
import no.uib.inf101.model.loot.equipable.EquipmentType;

import java.util.Random;

/**
 * Responsible for initializing and connecting all {@link Node}s and {@link Choice}s
 */
public class StoryBuilder {
    private final Random random;
    private Node gameOver;

    public StoryBuilder(Random random){
        this.random = random;
    }

    /**
     * Builds and connects all {@link Node}s and {@link Choice}s in the story graph.
     *
     * @return the first {@link Node}
     */
    public Node buildStory() {
        Node start = new StoryNode("The air is stale and uninviting");
        Node lookAroundStartRoom = createLookAroundStartRoom();
        this.gameOver = new EndNode("The light leaves your eyes");

        start.addChoice(new Choice("Proceed through the door", proceedTo(gameOver)));//TODO: Set next room
        start.addChoice(new Choice("Look around", lookAroundStartRoom));
        return start;
    }

    private Node createLookAroundStartRoom() {
        Node lookAround = new StoryNode("""
                The stone walls are damp and overgrown with moss.
                Drip points in the ceiling form a steady, echoing rhythm,
                only interrupted by scurrying bugs stirred awake by your presence.
                In the corner, a lone suit of armour sits slumped against the wall, as if resting.
                Ahead, a carved stone entrance leads on."""
        );

        Node investigateArmor = createArmourBranch(lookAround);
        Node investigateCarvings = createCarvingsBranch(lookAround);

        lookAround.addChoice(new Choice("Investigate the armour", investigateArmor));
        lookAround.addChoice(new Choice("Investigate the stone carvings", investigateCarvings));
        lookAround.addChoice(new Choice("Proceed through the door", proceedTo(gameOver))); //TODO

        return lookAround;
    }

    private Node createArmourBranch(Node returnNode) {
        Node armorSuccess = new StoryNode("""
                It is unmoving, but through the helmet slit,
                you swear you see a flicker, as if you're being watched.
                """);
        Node armorFailure = new StoryNode("""
                It is unmoving. Nothing but dust and bones remain of what
                the armour once protected.
                """);

        Enemy undeadGuardian = new Enemy("Undead Guardian", 25, 6, 10, EquipmentType.HOLLOW_HELM, random);
        Node afterCombat = new StoryNode("The knight crumples to the ground. The armor is hollow.");
        Node guardianCombat = new CombatNode(
                "The armour stirs", undeadGuardian, afterCombat, gameOver
        );

        armorSuccess.addChoice(new Choice("Back away slowly", returnNode));
        armorSuccess.addChoice(new Choice("Disturb its slumber", guardianCombat));
        armorFailure.addChoice(new Choice("Step back", returnNode));
        armorFailure.addChoice(new Choice("Take the helmet", guardianCombat));

        return new SkillCheckNode(
                "You crouch down and look at the knight.",
                armorSuccess,
                armorFailure,
                70);
    }

    private Node createCarvingsBranch(Node returnNode) {
        Node carvingsSuccess = new StoryNode("""
                You recognise the letters as an ancient script. Though time has
                eroded the full message, you can make out a phrase roughly translating to
                ..."on his right-hand side".
                """);
        Node carvingsFailure = new StoryNode("What little remains legible of the carving make no sense to you.");

        Choice stepBack = new Choice("Step back", returnNode);
        carvingsSuccess.addChoice(stepBack);
        carvingsFailure.addChoice(stepBack);

        return new SkillCheckNode(
                "You look closer at the carved glyphs lining the entrance.",
                carvingsSuccess,
                carvingsFailure,
                50
        );
    }

    private Node proceedTo(Node nextNode){
        return new TransitionNode("You move on.", nextNode);
    }
}
