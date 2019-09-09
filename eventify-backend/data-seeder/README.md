This module is intended to fill whole project with dummy data. 

If you are running this app in local machine, don't forget to include spring "dev" profile.

Data fields are stored under resources folder in json format.
 
You can see that all ids are Integers and not UUID here. Actually, when you(for example) send request for event creation, you receive event resource as a response. You extract UUID from that response and connect integer from json file to that UUID. Now if you want to reference event in some other json you can put Integer in that json file. You can find example in events.json where you reference place with Integer.

Do not reorder Seeders. UserSeeder has to be first since if we don't seed users we don't have how to log in on system.

PlaceSeeder have to be before EventSeeder because we need placeId references so we can reference then in events.json.

Event, User and Place classes are nothing more than Dto Objects that are meant to deserialize from json and send request to appropriate microservice. 

You can often receive 409 http error since this microservice has problem when concrete microservices already have data.

If you experience 409 error workaround is to stop all services with "docker-compose down" command, and then "docker system prune --volumes -f". This will clear your whole db so now you can start all services again with "docker-compose up -d".