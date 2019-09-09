This module holds informations about registered users of the app.
Details about user are: email, username, password, firstName, lastName, sex, roles and which events this user organize.

Operations that can be done in this module: register of users, login, confirm that you are host on event and banning users.

This module is secured so you cannot ban user if you are not admin. You cannot confirm that you are host of event if creator of event didnt mention you when he created event.

Also, this module listen on eventsTopic and sends messages. When it receives messages that event is created and event has hosts user-service takes action.

If user that is mentioned that he is host checked that he want to confirm that he is host, user-service add this event in list of events that should be confirmed. If he is fine with being host, then info is sent back to event-service that he is confirmed host of event.