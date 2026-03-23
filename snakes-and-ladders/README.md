# Snakes and Ladders (Java)

## Overview

This project implements a console-based Snakes and Ladders game using object-oriented design and a strategy-based rules model.

Core goals implemented:

- Board size is configurable as n x n.
- Number of players is configurable.
- Difficulty mode is selectable as easy or hard.
- The game runs in turn order with dice-based random movement.
- Snakes and ladders are generated dynamically.

Primary runnable entry point:

- [implementation/src/main/java/com/example/snakesandladders/GameTestScript.java](implementation/src/main/java/com/example/snakesandladders/GameTestScript.java)

## How to Run

Prerequisites:

- Java JDK 8+ installed and available on PATH.

From workspace root, compile all Java files:

javac (Get-ChildItem -Path implementation/src/main/java -Filter \*.java -Recurse | ForEach-Object { $\_.FullName })

Run the test script:

java -cp implementation/src/main/java com.example.snakesandladders.GameTestScript

The script will prompt for:

- n (board dimension)
- x (number of players)
- difficulty level (easy or hard)

## Requirement Mapping

### Inputs from user

- Implemented through [implementation/src/main/java/com/example/snakesandladders/GameTestScript.java](implementation/src/main/java/com/example/snakesandladders/GameTestScript.java#L10).
- Input guards enforce:
  - n must be positive
  - players must be at least 2
  - difficulty must be easy or hard

### Board numbering from 1 to n^2

- Implemented in [implementation/src/main/java/com/example/snakesandladders/core/Game.java](implementation/src/main/java/com/example/snakesandladders/core/Game.java#L27) with size = n \* n.

### Turn-by-turn movement

- Implemented with a queue of active players in [implementation/src/main/java/com/example/snakesandladders/core/Game.java](implementation/src/main/java/com/example/snakesandladders/core/Game.java#L20) and [implementation/src/main/java/com/example/snakesandladders/core/Game.java](implementation/src/main/java/com/example/snakesandladders/core/Game.java#L67).

### Dice is random and six-sided

- Implemented in [implementation/src/main/java/com/example/snakesandladders/dice/Dice.java](implementation/src/main/java/com/example/snakesandladders/dice/Dice.java#L23).

### Initial position outside board (0)

- Implemented in [implementation/src/main/java/com/example/snakesandladders/factory/PlayerFactory.java](implementation/src/main/java/com/example/snakesandladders/factory/PlayerFactory.java#L17).

### Forward movement by dice roll

- Implemented in [implementation/src/main/java/com/example/snakesandladders/core/Board.java](implementation/src/main/java/com/example/snakesandladders/core/Board.java#L35).

### Snake and ladder jumps

- Implemented via board static entity lookup in [implementation/src/main/java/com/example/snakesandladders/core/Board.java](implementation/src/main/java/com/example/snakesandladders/core/Board.java#L36).
- Entity hierarchy:
  - [implementation/src/main/java/com/example/snakesandladders/entities/BoardEntity.java](implementation/src/main/java/com/example/snakesandladders/entities/BoardEntity.java)
  - [implementation/src/main/java/com/example/snakesandladders/entities/Snake.java](implementation/src/main/java/com/example/snakesandladders/entities/Snake.java)
  - [implementation/src/main/java/com/example/snakesandladders/entities/Ladder.java](implementation/src/main/java/com/example/snakesandladders/entities/Ladder.java)

### Continue until at least 2 players are still playing

- Implemented by active-game guard in [implementation/src/main/java/com/example/snakesandladders/core/Game.java](implementation/src/main/java/com/example/snakesandladders/core/Game.java#L97).

### Overshoot should not move

- Implemented through move validation in rules strategy:
  - [implementation/src/main/java/com/example/snakesandladders/rules/RulesStrategy.java](implementation/src/main/java/com/example/snakesandladders/rules/RulesStrategy.java)
  - [implementation/src/main/java/com/example/snakesandladders/rules/EasyRules.java](implementation/src/main/java/com/example/snakesandladders/rules/EasyRules.java#L6)
  - [implementation/src/main/java/com/example/snakesandladders/rules/HardRules.java](implementation/src/main/java/com/example/snakesandladders/rules/HardRules.java#L6)

### n snakes and n ladders placed randomly

- Implemented in [implementation/src/main/java/com/example/snakesandladders/util/BoardFormatterUtil.java](implementation/src/main/java/com/example/snakesandladders/util/BoardFormatterUtil.java#L11).

### No cycle creation by snakes and ladders

- Current implementation prevents overlap of used endpoints in [implementation/src/main/java/com/example/snakesandladders/util/BoardFormatterUtil.java](implementation/src/main/java/com/example/snakesandladders/util/BoardFormatterUtil.java#L14).
- Note: it does not perform explicit graph cycle detection. It relies on placement constraints to reduce risk.

## Architecture and Design Decisions

### 1. Domain model separation

- Game orchestration lives in [implementation/src/main/java/com/example/snakesandladders/core/Game.java](implementation/src/main/java/com/example/snakesandladders/core/Game.java).
- Board mechanics live in [implementation/src/main/java/com/example/snakesandladders/core/Board.java](implementation/src/main/java/com/example/snakesandladders/core/Board.java).
- Entities model snake, ladder, player, and pawn state in [implementation/src/main/java/com/example/snakesandladders/entities/BoardEntity.java](implementation/src/main/java/com/example/snakesandladders/entities/BoardEntity.java), [implementation/src/main/java/com/example/snakesandladders/entities/Player.java](implementation/src/main/java/com/example/snakesandladders/entities/Player.java), and [implementation/src/main/java/com/example/snakesandladders/entities/BoardPlayer.java](implementation/src/main/java/com/example/snakesandladders/entities/BoardPlayer.java).

Why:

- Keeps game state transitions and board movement concerns separated.

### 2. Strategy pattern for rules

- Rules are abstracted behind [implementation/src/main/java/com/example/snakesandladders/rules/RulesStrategy.java](implementation/src/main/java/com/example/snakesandladders/rules/RulesStrategy.java).
- Modes are selected by [implementation/src/main/java/com/example/snakesandladders/factory/RulesFactory.java](implementation/src/main/java/com/example/snakesandladders/factory/RulesFactory.java).

Why:

- Makes it straightforward to add new rule sets without changing turn engine flow.

### 3. Factory pattern for player and rules construction

- [implementation/src/main/java/com/example/snakesandladders/factory/PlayerFactory.java](implementation/src/main/java/com/example/snakesandladders/factory/PlayerFactory.java)
- [implementation/src/main/java/com/example/snakesandladders/factory/RulesFactory.java](implementation/src/main/java/com/example/snakesandladders/factory/RulesFactory.java)

Why:

- Centralizes object creation conventions.

### 4. Queue-based turn scheduling

- Active players use FIFO queue in [implementation/src/main/java/com/example/snakesandladders/core/Game.java](implementation/src/main/java/com/example/snakesandladders/core/Game.java#L20).

Why:

- Naturally models round-robin turns.
- Supports reroll behavior by pushing player back to front.

### 5. Utility classes for setup and output

- Board randomization is isolated in [implementation/src/main/java/com/example/snakesandladders/util/BoardFormatterUtil.java](implementation/src/main/java/com/example/snakesandladders/util/BoardFormatterUtil.java).
- Console printing is wrapped by [implementation/src/main/java/com/example/snakesandladders/util/OutputUtil.java](implementation/src/main/java/com/example/snakesandladders/util/OutputUtil.java).

Why:

- Keeps orchestration class smaller and avoids duplicated setup code.

## SOLID Evaluation

### Single Responsibility Principle

Good:

- Dice logic is isolated in [implementation/src/main/java/com/example/snakesandladders/dice/Dice.java](implementation/src/main/java/com/example/snakesandladders/dice/Dice.java).
- Rule checks are isolated in rules classes.

Tradeoff:

- [implementation/src/main/java/com/example/snakesandladders/core/Game.java](implementation/src/main/java/com/example/snakesandladders/core/Game.java) still owns multiple concerns: lifecycle, turn engine, and result output.

### Open/Closed Principle

Good:

- New difficulty rules can be added via new RulesStrategy implementations and factory extension.

### Liskov Substitution Principle

Good:

- Snake and Ladder are substitutable as BoardEntity for board jump behavior.

### Interface Segregation Principle

Neutral:

- RulesStrategy is small and focused, but it is an abstract class rather than a Java interface.

### Dependency Inversion Principle

Partial:

- Game depends on RulesStrategy abstraction.
- Game still directly instantiates concrete Dice and Board, which limits testability and inversion.

## Known Limitations and Risks

1. Easy and hard modes currently have effectively the same behavior.

- [implementation/src/main/java/com/example/snakesandladders/rules/EasyRules.java](implementation/src/main/java/com/example/snakesandladders/rules/EasyRules.java)
- [implementation/src/main/java/com/example/snakesandladders/rules/HardRules.java](implementation/src/main/java/com/example/snakesandladders/rules/HardRules.java)

2. RulesFactory defaults unknown mode to easy, instead of rejecting invalid modes.

- [implementation/src/main/java/com/example/snakesandladders/factory/RulesFactory.java](implementation/src/main/java/com/example/snakesandladders/factory/RulesFactory.java#L8)

3. Random board setup can loop indefinitely for small n or constrained boards because placement loops are unbounded.

- [implementation/src/main/java/com/example/snakesandladders/util/BoardFormatterUtil.java](implementation/src/main/java/com/example/snakesandladders/util/BoardFormatterUtil.java#L23)
- [implementation/src/main/java/com/example/snakesandladders/util/BoardFormatterUtil.java](implementation/src/main/java/com/example/snakesandladders/util/BoardFormatterUtil.java#L37)


## Quick Class Guide

- Runner: [implementation/src/main/java/com/example/snakesandladders/GameTestScript.java](implementation/src/main/java/com/example/snakesandladders/GameTestScript.java)
- Loop controller: [implementation/src/main/java/com/example/snakesandladders/core/GameLoop.java](implementation/src/main/java/com/example/snakesandladders/core/GameLoop.java)
- Core engine: [implementation/src/main/java/com/example/snakesandladders/core/Game.java](implementation/src/main/java/com/example/snakesandladders/core/Game.java)
- Board behavior: [implementation/src/main/java/com/example/snakesandladders/core/Board.java](implementation/src/main/java/com/example/snakesandladders/core/Board.java)
- Rules abstraction: [implementation/src/main/java/com/example/snakesandladders/rules/RulesStrategy.java](implementation/src/main/java/com/example/snakesandladders/rules/RulesStrategy.java)
- Rule implementations: [implementation/src/main/java/com/example/snakesandladders/rules/EasyRules.java](implementation/src/main/java/com/example/snakesandladders/rules/EasyRules.java), [implementation/src/main/java/com/example/snakesandladders/rules/HardRules.java](implementation/src/main/java/com/example/snakesandladders/rules/HardRules.java)
- Setup utility: [implementation/src/main/java/com/example/snakesandladders/util/BoardFormatterUtil.java](implementation/src/main/java/com/example/snakesandladders/util/BoardFormatterUtil.java)
- Output utility: [implementation/src/main/java/com/example/snakesandladders/util/OutputUtil.java](implementation/src/main/java/com/example/snakesandladders/util/OutputUtil.java)
