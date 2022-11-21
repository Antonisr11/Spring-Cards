## Description

Assume an application named Cards that allows users to create and manage tasks in the form of cards:

* Application users are identified uniquely by their mail address and use a password to authenticate themselves before accessing their
cards
* A user creates a card by providing a name for it and, optionally, a description and a color
  * Name is mandatory
  * Color, if provided, should conform to a “6 alphanumeric characters prefixed with a #“ format
  * Upon creation, the status of a card is To Do
* A user can search through cards they created
  * Filters include name, color, status and date of creation
* A user can request a single card they created
* A user can update the name, the description, the color and/or the status of a card they created
  * Contents of the description and color fields can be cleared out
  * Available statuses are To Do, In Progress and Done
* A user can delete a card they created

## Known problems / potential fixes

1. This project focuses in back-end development so I had to make some assumptions such as:
  * UI is the bare minimum; It can be improved.
  * User always types corrent data (for instance color-hex field is not checked if # is its first character).
2. There are some serious open security issues some of which are:
  * Any logged user can see any card stored in database by changing the url to "http://localhost:8080/card/show/{AnyIDHere}".
  * Any logged user can change any card by changing the url to "http://localhost:8080/card/show/{AnyIDHere}" and clicking update.
  * Any logged user can delete any card by changing the url to "http://localhost:8080/card/show/{AnyIDHere}" and clicking delete.
  * User's password is saved as plain text without hashing it before.
  
  I ignored those problems because a well-round security without any Security Dependency might be out of the scope of the project.
  
## How to install & Login Screen

To start the application make sure that you have imported .sql file to MySQL. Then, import the Cards file to IntelliJ and click run.

When the application starts, it opens a window in your default browser showing a login screen with a form (link will be http://localhost:8080). In the latter form you have to type email and password (if you had already register).

![image](https://user-images.githubusercontent.com/76475823/201341493-a60d6588-73cd-41b2-8257-8b6d6d0b0345.png)

If credentials are right, a token is generated for you and it is stored in CardsApplication class and you are redirected to showCards. Otherwise a message will appear and you'll have to type your credentials again.

## ShowCards 

Before loading, application checks if token is valid (if token is invalid, user logs out and returns to Login Screen). When accessing ShowCards, user can see all their cards with various filters. By clicing inside a card, this card opens and application redirects user to showCard. Always, the last card will shows 'Click to add card' and if clicked, user is redirected to insert page.

![image](https://user-images.githubusercontent.com/76475823/201350754-b82b7255-964f-4ba5-acab-74fc428f42b0.png)

User can use filters to search cards, for example by entering their creation date. **Attention**: User can apply only one filter at the time, for instance if user enter '11-11-2022' as date and '#3b95e6' as color but click at 'Filter by Color' button, then, only color filter will taken into account. 

An extra small note is that search by name works as name contains \[...\] so if a user has 3 cards with names 'House of Memories' 'High Hopes' 'Victorious' respectively, and fill the Name Search field with 'Ho', then 2 cards will be returned to him; '**Ho**use of Memories' and 'High **Ho**pes'.

## ShowCard

In this screen user can see all card's information. To name but a few, they can select update in order to change Name/Color/Status/Description and they can click in Delete Card button to delete this card.

![image](https://user-images.githubusercontent.com/76475823/201357321-4283c424-4a1c-4337-90c5-b4b5f4bcf0ae.png)


## Insert Or Update

If user chooses to update the card, they can modify almost all fields (ID cannot be changed, Name is mandatory, Status will be selected and all other fields are optional). To save the changes user must click the Commit button.

![image](https://user-images.githubusercontent.com/76475823/201352418-5b89bcd0-a6f1-488b-862f-0787f876224f.png)

In insert card, things are similar. If user forgots to set Name, browser will notify him to do so. 

![image](https://user-images.githubusercontent.com/76475823/201352462-2e1338f3-bb82-4eed-b60c-9ca211c446b1.png)


## Database 

Database schema contains 2 tables: users and cards.
* User Table stores email and password and primary key is email, because two users cannot share the same email.
* Cards Table stores an unique ID for each card, a color hex, the creation date, a description that can be null, the name of the card, a int status (0 stands for 'To Do', 1 for 'In Progress', 2 for 'Done') and owner_email that reffers to user's email. I choose CASCADE both on update and on delete.

![image](https://user-images.githubusercontent.com/76475823/201353877-8d46f5f0-1166-42f3-af2a-7bef8c00c135.png)

## Easter Egg

In this project there is a small easter egg hidden! Can you find it?
