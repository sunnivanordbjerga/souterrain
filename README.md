# Souterrain

*A narrative-driven dungeon crawler inspired by games like
Esoteric Ebb, RoadWarden and tabletop RPGs.*

---

## Status

Initially developed as part of INF101 (Spring 2026). The project has since been expanded and polished independently.

---

## Gameplay

The player progresses through a dungeon by making choices
during exploration and surviving autmated combat encounters,
shaping their path through the game.

## Features

* Graph-based progression system, with branching choices
* Automated combat encounters with semi-random outcomes
* Inventory and equipment management
* Health and stat tracking

---

## Tech Stack

* Java 25
* Swing
* Maven
* JUnit 5
* JaCoco

---

## Installation and Running

### Requirements

* [JDK 25](https://jdk.java.net/25/) or newer
* [Maven 3.9+](https://maven.apache.org/download.cgi#CurrentMaven)

### Clone:

```bash
git clone https://github.com/sunnivanordbjerga/souterrain.git
```

### Build:

```bash
mvn clean install
```

### Run:

```bash
mvn compile
java -cp target/classes Main
```

### Test

To run unit tests

```bash
mvn test
```

[JaCoCo](https://www.jacoco.org/jacoco) coverage report will be generated in:
`target/site/jacoco/index.html`.

---

## Controls
Use the mouse to select choices as they appear on the screen

---

## Project Structure
* [src/main/java](src/main/java) → Source code
* [src/test/java](src/test/java) → Unit tests

---

## Future Improvements
* Additional enemy and item variety
* Expanded story content
* Save/load functionality
* Improved UI presentation

---

## Authors
Sunniva Nord Bjerga

---

## License
* Code is licensed under a [MIT License](/LICENSE.md)
* Story, text and artwork is licensed under [CC BY-NC 4.0](https://creativecommons.org/licenses/by-nc/4.0/)
