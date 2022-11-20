package agh.ics.oop;

import java.util.Comparator;
import java.util.SortedMap;
import java.util.TreeMap;

public class MapBoundary implements IPositionChangeObserver {

    public TreeMap<Vector2d, AbstractWorldMapElement> xAxis = new TreeMap<>(new Comparator<Vector2d>() {
        @Override
        public int compare(Vector2d o1, Vector2d o2) {
            return (o1.x > o2.x) ? 1 : ((o1.x == o2.x) ? ((o1.y > o2.y) ? 1 : -1) : -1); // will never be equal
        }
    });
    public TreeMap<Vector2d, AbstractWorldMapElement> yAxis = new TreeMap<>(new Comparator<Vector2d>() {
        @Override
        public int compare(Vector2d o1, Vector2d o2) {
            return (o1.y > o2.y) ? 1 : ((o1.y == o2.y) ? ((o1.x > o2.x) ? 1 : -1) : -1); // will never be equal
        }
    });

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        AbstractWorldMapElement obj = xAxis.get(oldPosition);
        xAxis.remove(oldPosition);
        xAxis.put(newPosition, obj);

        obj = yAxis.get(oldPosition);
        yAxis.remove(oldPosition);
        yAxis.put(newPosition, obj);
    }


    public void put(Vector2d pos, AbstractWorldMapElement obj) {
        this.xAxis.put(pos, obj);
        this.yAxis.put(pos, obj);
    }


    public void remove(Vector2d pos) {
        this.xAxis.remove(pos);
        this.yAxis.remove(pos);
    }
}
