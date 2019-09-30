# Eventify project
Our project is helping you to find events that you are interested in. That's it!

# Motivation
Did it happened to you that you heard about the event where your friend went and you are sad because you haven't heard about that event on time so you missed it? You don't want to follow 17 facebook pages, university page, 3 websites and add follow some guys on LinkedIn so you can be informed about events you are interested in? You would like to find and filter all those events in one place? So do I, my friend, that is the reason why I have created this project.

# Application live
Unfortunately, out application is not live for now, but it will be soon.

# Contributing
If you are a developer and want to contribute to this project, feel free to contact me on [Linkedin](https://www.linkedin.com/in/spasoje-petronijevi%C4%87/).

If you are not a developer but you have some cool feature on your mind that can help my project, please share it with me.

# Getting started

You should have java 8, maven and docker installed before you start.

**Important**: Make sure that ports ```5432, 5050, 2181, 9092, 8761, 8762, 8200, 9990, 5060, 8080, 8222, 9100, 4500, 8182, 27017``` 
are not used since our services are running on that ports.
If you have some important processes running on those ports and you don't want to stop your processes,
feel free to change port numbers in my app for testing purposes on your local machine so you can start those services.
Just make sure that if you are changing port for example 9092 to 9093 change that number on all places in app!


```
git clone https://github.com/Calimerico/eventify.git
cd eventify-backend
chmod 755 start.sh
./start.sh
```
This command will take few minutes(it need to build your project, create docker images,register services to eureka, etc.)

When you end up with this screen:

![docker-compose-success](docker-compose-success.png)

app is not ready yet, you can receive 5xx error from server. Servers should register themself to eureka. It usually last ~30 seconds. Be patient. :)


That's it! You are now able to access application on ```http://localhost:8080/```

If you have any trouble with starting application, open an issue and I will resolve it.

To stop application ``` docker-compose down ``` is enough.

Only first time when you are starting app ``` start.sh ``` script is neccesary, later you can just start it with ``` docker-compose up -d ``` since you already have jars and images created.

# Develop new features
If you want to implement new feature you can contact me through [Linkedin](https://www.linkedin.com/in/spasoje-petronijevi%C4%87/) or you can open issue. If new feature is accepted you can push it through pull request.

You don't want to stop all services and start them again for every small change you made.


You will usually not work on all services at a time. If your services are up and you are working for example on ```place-service```  and you want to try it, process for doing that is simple. Stop place service with ```docker stop place``` and then you can start ```place-service``` in your local enviroment like any spring-boot app, service will know how to communicate with rest of the services. Just make sure that you started your service with ```dev``` profile when you are starting it locally.

# Tech/Frameworks/Architecture used

We implemented microservice architecture on backand and single page application on frontend.

This project is written mainly in Java for backend and React for frontend.


Keep in mind that since project is implemented with microservice architecture in mind, we can create services in techology by choice. 

Other notable technologies / frameworks / tools / libraries:
- Java 8
- React
- Spring framework
- Java Persistence API
- Apache Kafka
- Docker
- Mongo DB
- Postgres
- Lombok
- Jsoup

I plan to include more technologies soon, most notably **Kubernetes**.

# Features:

There are 3 roles that you can possibly have in eventify system: **Administrator**, **Registered user**, **Non-registered user**.

Non-registered person can:
- Login
- Register
- View filtered list of events(sorted by popularity)
- View events that particular host is organizing(sorted by popularity)
- View events that happen on particular place(sorted by popularity)

Registered user can:
- View filtered list of events
- View events that particular host is organizing(sorted by popularity and user preferences)
- View events that happen on particular place(sorted by popularity and user preferences)
- View events where he is host or he added
- Add event
- Remove event where he is host or he added
- Update event where hi is hot or he added
- Configure that when someone mark him as host he is automatically confirmed as host
- Configure that when someone mark him as host he is not automatically confirmed as host
- Confirm that he is host of an event
- Unconfirm that he is host of an event

Admin can:
- See popularities of events(by age, gender, type, etc.)
- See popularities of hosts(by age, gender, type, etc.)

For more informations about this features, visit readme pages of appropriate services.

