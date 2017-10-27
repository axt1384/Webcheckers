---
geometry: margin=1in
---

# PROJECT Design Documentation

## Team Information
* Team name: Resistance
* Team members
  * Justin Lam
  * Alan Tan
	* Jesse Chen
  * Elijah Cantella
  * Jay Gogri

## Executive Summary
This is the documentation for our project after Sprint 1. What has been implemented is creating the backend for the Sign In and also displaying the Checker board with the pieces on its respective sides.

### Purpose
To allow users to sign in, sign out, and enter a game with another player by clicking on the opponent's name in the home page.

### Glossary and Acronyms
> Provide a table of terms and acronyms.

| Term | Definition |
|------|------------|
| VO | Value Object |

### Requirements
>This section describes the features of the application.
> In this section you do not need to be exhaustive and list every story.  Focus on top-level features from the Vision document and maybe Epics and critical Stories.

During this sprint, we were given two main tasks to complete
* Implement a Sign In interface so that users can sign in and sign out
    * Name restrictions
        * No duplicate names
        * No null names such as double quotes (")
    * List out all the users currently online only if the user is sign in
* Implement a Game interface so that users can start a game with each other
    * Utliizing that list a user can click on another player and start a game with him/her
        * If selected player is in a game than, a message should show that a game cannot be started
    * The pieces on the board should be displayed properly, with challenger as red and challenged as white, with respective pieces on the bottom of each user's board
    * Pieces should be draggable and droppable on valid places on the baord

### Definition of MVP
The Minimun Viable Product should be a product that can sign a user in and out (if such user is already signed in), and start a game with properly aligned pieces.

### MVP Features
* Sign In
    * Player Sign-In
    * Player Sign-Out
* Game Play
    * Player Setup
    * Disc Placement

### Roadmap of Enhancements
> Provide a list of top-level features in the order you plan to consider them.

## Application Domain
This section describes the application domain.

### Overview of Major Domain Areas
> Provide a high-level overview of the

### Details of each Domain Area
> If necessary, high-light certain areas of the Domain model that have a focused purpose.  Create textual narrative that describes the purpose and how that relates to the associated domain model.

## Architecture
This section describes the application architecture.

### Summary
> Provide a brief summary of the architecture.  Also provide one or two models (diagrams) that describe the architecture.  Hint: review the Architecture lecture slides for ideas.

### Overview of User Interface
> Provide a summary of the application's user interface.
> This includes the UI state model.

### Tier X
> Provide a summary of each tier of your architecture.  Thus replicate this heading for each tier.
> In each section describe the types of components in the tier and describe their responsibilities.

## Sub-system X
> Provide a section for each major sub-system within the tiers of the architecture.  Replace 'X' with the name of the sub-system.
> A sub-system would exist within one of the application tiers and is a group of components cooperating on a significant purpose within the application.  For example, in WebCheckers all of the UI Controller components for the Game view would be its own sub-system.

This section describes the detail design of sub-system X.

### Purpose of the sub-system
> Provide a summary of the purpose of this sub-system.

### Static models
> Provide one or more static models (UML class or object diagrams) with some details such as critical attributes and methods.  If the sub-system is large (over 10 classes) then consider decomposing into multiple, smaller, more focused diagrams.

### Dynamic models
> Provide any dynamic model, such as state and sequence diagrams, as is relevant to a particularly significant user story.
> For example, in WebCheckers you might create a sequence diagram of the `POST /validateMove` HTTP request processing or you might use a state diagram if the Game component uses a state machine to manage the game.
