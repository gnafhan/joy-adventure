package main.entity.interfaces;

import main.entity.Diamond;
import main.entity.GameObject;

import java.util.ArrayList;
import java.util.Random;

public interface Spawner<T> {
    public void spawnObjects(ArrayList<T> objects, int maxObjects);
}
