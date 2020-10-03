package codeinfoextractor.utils;

public class Pair<T1, T2> {
    private T1 one;
    private T2 two;

    public Pair(T1 one, T2 two) {
        this.one = one;
        this.two = two;
    }

    public T1 getOne() {
        return one;
    }

    public void setOne(T1 one) {
        this.one = one;
    }

    public T2 getTwo() {
        return two;
    }

    public void setTwo(T2 two) {
        this.two = two;
    }
}
