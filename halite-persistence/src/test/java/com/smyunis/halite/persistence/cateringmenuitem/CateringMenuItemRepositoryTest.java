package com.smyunis.halite.persistence.cateringmenuitem;

import com.smyunis.halite.application.applicationexceptions.EntityNotFoundException;
import com.smyunis.halite.domain.caterer.CatererId;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItem;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemData;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemId;
import com.smyunis.halite.domain.cateringmenuitem.CateringMenuItemRepository;
import com.smyunis.halite.domain.shared.MonetaryAmount;
import com.smyunis.halite.persistence.DatabaseFixture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CateringMenuItemRepositoryTest {
    private static final JdbcTemplate jdbcTemplate = DatabaseFixture.createJdbcTemplate();
    private final CateringMenuItemRepository repository = new CateringMenuItemRepositoryImpl(jdbcTemplate);
    private CateringMenuItemId cateringMenuItemId;
    private CateringMenuItemData cateringMenuItemData;

    @BeforeEach
    void setup() throws MalformedURLException {
        cateringMenuItemId = new CateringMenuItemId();
        CatererId catererId = new CatererId();
        List<URL> images = new ArrayList<>();
        images.add(new URL("https://static.wikia.nocookie.net/shokugekinosoma/images/e/e8/Mitamura_Special_Napolitan_%28anime%29.png/revision/latest?cb=20160920060118"));

        Set<String> ingredients = Set.of("Noodles", "Cheese", "Tomato ketchup/Tomato-based sauce", "Meat", "Vegetables");
        cateringMenuItemData = new CateringMenuItemData()
                .setId(cateringMenuItemId)
                .setCatererId(catererId)
                .setName("Mitamura Special Napolitan")
                .setPrice(new MonetaryAmount(120))
                .setImages(images)
                .setIngredients(ingredients);
    }

    @Test
    void canSaveThenGetCateringMenuItem() {
        repository.save(new CateringMenuItem(cateringMenuItemData));

        CateringMenuItem fetchedCateringMenuItem = repository.get(cateringMenuItemId);
        var fetchedData = fetchedCateringMenuItem.getDataReadOnlyProxy();

        assertEquals(cateringMenuItemData.getId(), fetchedData.getId());
        assertEquals(cateringMenuItemData.getCatererId(), fetchedData.getCatererId());
        assertEquals(cateringMenuItemData.getName(), fetchedData.getName());
        assertEquals(cateringMenuItemData.getIngredients(), fetchedData.getIngredients());
        assertEquals(cateringMenuItemData.getImages(), fetchedData.getImages());
    }

    @Test
    void removeACateringItemAlong() {
        repository.save(new CateringMenuItem(cateringMenuItemData));

        repository.remove(cateringMenuItemId);

        assertThrows(EntityNotFoundException.class,() -> {
            repository.get(cateringMenuItemId);
        });
    }

}
