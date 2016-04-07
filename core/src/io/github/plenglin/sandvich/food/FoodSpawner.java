package io.github.plenglin.sandvich.food;

import io.github.plenglin.sandvich.util.IntVector;
import io.github.plenglin.sandvich.util.Util;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class FoodSpawner {

    private Map<FoodDefinition, Integer> weights;

    public FoodSpawner() {
        weights = new HashMap<FoodDefinition, Integer>();
    }

    public void addFood(FoodDefinition foodClass, int weight) {
        weights.put(foodClass, weight);
    }

    public TreeMap<Integer, FoodDefinition> getMaximums() {
        int n = 0;
        TreeMap<Integer, FoodDefinition> maximums = new TreeMap<Integer, FoodDefinition>();
        for (FoodDefinition foodClass: weights.keySet()) {
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
