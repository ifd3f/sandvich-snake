package io.github.plenglin.sandvich.food;

import io.github.plenglin.sandvich.IntVector;

public interface FoodDefinition {

    Food create(IntVector position);

}
