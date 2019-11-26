package main;

class Triplet<T, U, V> {

    //Class that stores 3 types of different data.
    private final T first;
    private final U second;
    private final V third;

    Triplet(final T first, final U second, final V third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

    //Used for keeping player data as String, Integer, Integer
    final T getFirst() {
        return first;
    }
    final U getSecond() {
        return second;
    }
    final V getThird() {
        return third;
    }
}
