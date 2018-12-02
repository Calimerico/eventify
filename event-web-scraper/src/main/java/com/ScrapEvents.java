package com;

import com.example.demo.Command;
import lombok.Builder;

import java.util.List;

/**
 * Created by spasoje on 21-Nov-18.
 */
@Builder
public class ScrapEvents implements Command<Void> {
    private final List<String> linksToScrap;
}
