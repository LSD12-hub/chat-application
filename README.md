# chat-application

After creating an echo application I wanted to create an application which:
- accomodates mutiple users
- route information to other users

### Basically a chat application 🤷🏿‍♂️

## What I learned

#### - Threading
 1. In order to have mutiple users you need a thread for each user in the server. I created a thread for each user in the server and capped the total amount of user at 4
 2. The Client had to receive a message and send a message at the sametime (or concurrently). I create a thread that is constantly listening to the and printing the output fron the server.

#### - Sockets
 1. I had keep the server open for users who want to connect to the join the server.
 2. I have to create a new socket for each user that connects to the server.

Everything is local for now... maybe in the future things will change.

## How to run
- Run the Chatserver first and ChatClient after.
- To exit the group chat type `bye` and you will exit from the group chat
- type `kill` to exit the server (`if you exit without closing the clients this will break the clients`)
