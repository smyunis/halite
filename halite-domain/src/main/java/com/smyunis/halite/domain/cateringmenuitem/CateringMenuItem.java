package com.smyunis.halite.domain.cateringmenuitem;

import com.smyunis.halite.domain.cateringmenu.CateringMenuId;

import java.net.URL;
import java.util.List;
import java.util.Set;

public class CateringMenuItem {
    private CateringMenuItemId id;
    private CateringMenuId cateringMenuId;
    private String name;
    private double price;
    private Set<String> ingredients;
    private List<URL> images;
}
