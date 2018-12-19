package com.example.demo;

import lombok.Builder;
import lombok.Value;

/**
 * Created by spasoje on 15-Dec-18.
 */
@Value
@Builder
public class DeleteEvent implements Command<Void>{
    private String eventId;
}
