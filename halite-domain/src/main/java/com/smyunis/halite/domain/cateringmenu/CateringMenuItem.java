package com.smyunis.halite.domain.cateringmenu;

import java.net.URL;
import java.util.List;
import java.util.Optional;

public class CateringMenuItem {
    private CateringMenuItemId id;
    private String name;
    private List<String> ingredients;
    private double price;
    private Optional<List<URL>> images;
}
