package io.github.plenglin.sandvich.food;

import io.github.plenglin.util.IntVector;
import io.github.plenglin.util.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * A weighted random food generator.
 */
public class FoodSpawner {

    private final Map<FoodDefinition, Integer> weights;

    public FoodSpawner() {
        weights = new HashMap<>();
    }

    public void addFood(FoodDefinition foodClass, int weight) {
        weights.put(foodClass, weight);
    }

    private TreeMap<Integer, FoodDefinition> getMaximums() {
        int n = 0;
        TreeMap<Integer, FoodDefinition> maximums = new TreeMap<>();
        for (FoodDefinition foodClass : weights.keySet()) {
            int weight = weights.get(foodClass);
            n += weight;
            maximums.put(n, foodClass);
        }
        return maximums;
    }

    public Food create(IntVector position) {
        TreeMap<Integer, FoodDefinition> maximums = getMaximums();
        int number = Util.randint(0, maximums.lastKey());
        FoodDefinition selected = maximums.ceilingEntry(number).getValue();
        return selected.create(position);
    }

}
