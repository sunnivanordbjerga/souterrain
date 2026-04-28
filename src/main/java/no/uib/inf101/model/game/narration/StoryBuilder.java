package no.uib.inf101.model.game.narration;

import no.uib.inf101.model.entity.enemy.Enemy;
import no.uib.inf101.model.game.narration.node.*;

/**
 * Responsible for initializing and connecting all {@link Node}s, grouping them by {@link Scene}s.
 */
public class StoryBuilder {

    public Node buildStory(){
        return createStartScene();
    }

    private Node createStartScene() {
        Node start = new StoryNode("The air is stale and uninviting");

        Node lookAround = createLookAroundHub();

        Node proceed = new StoryNode("You move on.");

        start.addChoice(new Choice("Proceed through the door", proceed));
        start.addChoice(new Choice("Look around", lookAround));

        return start;
    }

    private Node createLookAroundHub() {
        Node lookAround = new StoryNode("""
                The stone walls are damp and overgrown with moss.
                Drip points in the ceiling form a steady, echoing rhythm,
                only interrupted by scurrying bugs stirred awake by your presence.
                In the corner, a lone suit of armour sits slumped against the wall, as if resting.
                Ahead, a carved stone entrance leads on."""
        );

        Node investigateArmor = createArmourBranch(lookAround);
        Node investigateCarvings = createCarvingsBranch(lookAround);
        Node proceed = new StoryNode("You move on.");

        lookAround.addChoice(new Choice("Investigate the armour", investigateArmor));
        lookAround.addChoice(new Choice("Investigate the stone carvings", investigateCarvings));
        lookAround.addChoice(new Choice("Proceed through the door", proceed));

        return lookAround;
    }

    private Node createArmourBranch(Node hub) {
        Node armorSuccess = new StoryNode("""
                 It is unmoving, but through the helmet slit,
                you swear you see a flicker, as if you're being watched.
                """);
        Node armorFailure = new StoryNode("""
                It is unmoving. Nothing but dust and bones remain of what
                the armour once protected.
                """);

        Enemy undeadGuardian = new Enemy("Undead Guardian", 25, 6, 10);
        Node afterCombat = new StoryNode("The knight crumples to the ground. The armor is hollow.");
        Node gameOver = new EndNode("The light leaves your eyes");
        Node guardianCombat = new CombatNode(
                "The armour stirs", undeadGuardian, afterCombat, gameOver
                );

        armorSuccess.addChoice(new Choice("Back away slowly", hub));
        armorSuccess.addChoice(new Choice("Disturb its slumber", guardianCombat));
        armorFailure.addChoice(new Choice("Step back", hub));
        armorFailure.addChoice(new Choice("Take the helmet", guardianCombat));

        return new SkillCheckNode(
                "You crouch down and look at the knight.",
                armorSuccess,
                armorFailure,
                70);
    }

    private Node createCarvingsBranch(Node hub) {
        Node carvingsSuccess = new StoryNode("""
                You recognise the letters as ancient [some cryptic lore]. Though time has
                eroded the full message, you can make out a phrase roughly translating to
                 [HINT]""");
        Node carvingsFailure = new StoryNode("What little remains legible of the carving make no sense to you.");

        Choice stepBack = new Choice("Step back", hub);
        carvingsSuccess.addChoice(stepBack);
        carvingsFailure.addChoice(stepBack);

        return new SkillCheckNode(
                "You look closer at the carved glyphs lining the entrance.",
                carvingsSuccess,
                carvingsFailure,
                50
        );
    }
}
