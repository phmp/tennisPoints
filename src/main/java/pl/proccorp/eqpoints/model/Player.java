package pl.proccorp.eqpoints.model;

public enum Player {
    A,
    B;

    public Player opponent() {
        return this == A ? B : A;
    }
}
