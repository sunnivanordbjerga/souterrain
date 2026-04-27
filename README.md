# Souterrain

*A simple narrative dungeon crawler inspired by games like
Esoteric Ebb, RoadWarden and Dungeons & Dragons.*
---

## Gameplay

The player progresses through a dungeon by making choices
about exploration and surviving combat encounters,
affecting the outcome of the story.

## Features

* Graph-based story progression, with branching choices
* Semi-random combat and exploration outcomes

---

## Tech Stack

* Java 25
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
git clone https://git.app.uib.no/ii/inf101/26v/assignments/sunniva.bjerga_empty.git
```

### Build:

```bash
mvn clean install
```

### Run:

```bash
mvn compile
java -cp target/classes no.uib.inf101.Main
```

### Test

To run unit tests

```bash
mvn test
```

This will also generate a [JaCoCo](https://www.jacoco.org/jacoco) code coverage report, which you can find
in `target/site/jacoco/index.html`.

---

## Controls
Left-click on the choice you want as they appear

---

## Project Structure
* [src/main/java](src/main/java) → Source code
* [src/main/resources](src/main/resources) → Game assets (images, audio)
* [src/test/java](src/test/java) → Unit tests

---

## Authors
* Sunniva Nord Bjerga

## Credits
No external contributors as of April 2026

---

## Bugs / Issues
No known issues as of April 2026

---

## License
* Code is licensed under a [MIT License](/LICENSE.md)
* Story, text and artwork is licensed under [CC BY-NC 4.0](https://creativecommons.org/licenses/by-nc/4.0/)