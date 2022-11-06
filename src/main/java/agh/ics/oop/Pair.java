package agh.ics.oop;

public class Pair<A, B> {

    private final A first;
    private final B second;


    public A first() { return this.first; }


    public B second() { return this.second; }


    public String toString() {
        return String.format("(%s, %s)", this.first, this.second);
    }


    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Pair p)) return false;
        return this.first == p.first || (this.first.equals(p.first) && this.second.equals(p.second));
    }


    public Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }
}