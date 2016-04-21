package io.github.plenglin.sandvich.food;

import io.github.plenglin.util.IntVector;

/**
 * I don't want to go to the hassle of doing all that dynamic initialization.
 * Here's a lazy shortcut.
 */
public interface FoodDefinition {

    Food create(IntVector position);

}
