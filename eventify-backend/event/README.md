This module is core module and his responsibility is to keep information about events and provide methods for manipulationg with that informations.

Also, for now we are keeping information about places in this module. We will extract info about places in separate module soon.

Event hold next details : name, type, hosts, place, time, description, picture and prices.
 
One caveat here is that if you create event and add hosts, host will maybe have to confirm this. So here we save info which host confirmed change and which don't.(we receive info from user module about those changes)

Operations which can be done:
    
    Create event
    Event will be saved to system.
    If event contains hosts, message will be sent to user module to inform it about it.
    User module is keeping information of events that user organiza/hosts.
    
    Update event
    Simple put operation. Existing event will be overriden with new fields.(We should include hosts in UpdateEventRequest)
    We should implement EventUpdatedEvent that will inform other services about this when we include hosts.
    
    Delete event
    Simple delete event by id if exist. Message EventDeletedEvent is sent to kafka to inform other services about that.
    
    Get event by filter
    Simple get by filter operation.
    
    Get by id
    Simple get by id operation
    
    Insert place and Get places operations(will be moved to place-service soon)
    
Also, this module listen for kafka events from event-web-scraper-module and user-service module. It creates event when it receives message from web-scraper and change status on host(confirmed or not) when it receives message from user-service.

Event-service is secured. Everyone is having access to basic search form but if you want to delete/update event you have to be logged in and that you are one of the approved hosts on that event.