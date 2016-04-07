package io.github.plenglin.sandvich.food;

import io.github.plenglin.sandvich.util.IntVector;

public interface FoodDefinition {

    Food create(IntVector position);

}
