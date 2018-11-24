package com;

import lombok.Value;

import java.util.List;

/**
 * Created by spasoje on 21-Nov-18.
 */

@Value
public class ScrapEvents implements Command {
    private List<String> linksToScrap;
}
