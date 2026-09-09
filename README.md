# Souterrain

[![CI](https://github.com/sunnivanordbjerga/souterrain/actions/workflows/ci.yml/badge.svg)](https://github.com/sunnivanordbjerga/souterrain/actions/workflows/ci.yml)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=sunnivanordbjerga_souterrain&metric=coverage&token=59d2fd306068737a5636a751d6cc605bcc55200e)](https://sonarcloud.io/summary/new_code?id=sunnivanordbjerga_souterrain)
[![Quality gate status](https://sonarcloud.io/api/project_badges/measure?project=sunnivanordbjerga_souterrain&metric=alert_status&token=59d2fd306068737a5636a751d6cc605bcc55200e)](https://sonarcloud.io/summary/new_code?id=sunnivanordbjerga_souterrain)
![Java](https://img.shields.io/badge/Java-25-007396?logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.8+-C71A36?logo=apachemaven&logoColor=white)
![JUnit](https://img.shields.io/badge/JUnit-6-blue.svg)

*A narrative-driven dungeon crawler inspired by games like
Esoteric Ebb, RoadWarden and tabletop RPGs.*

---

## Status

Initially developed as part of INF101 (Spring 2026). The project has since been expanded and polished independently.

---

## Gameplay

The player progresses through a dungeon by making choices
during exploration and surviving automated combat encounters,
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
