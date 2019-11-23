package com.leagueofoop.board;

public abstract class Ground {
    boolean isOccupied = false;
    int occupantId = -1;
    GroundType type;

    public void setOccupied(int id) {
        this.occupantId = id;
        this.isOccupied = true;
    }

    public void occupantLeft() {
        this.occupantId = -1;
        this.isOccupied = false;
    }

    public String toString() {
        return "Type: " + this.type + " isOccupied: " + this.isOccupied + " id: " + this.occupantId;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public int getOccupantId() {
        return occupantId;
    }
}
