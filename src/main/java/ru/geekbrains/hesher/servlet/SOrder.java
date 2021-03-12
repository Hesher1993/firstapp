package ru.geekbrains.hesher.servlet;

public enum SOrder {
    ASC,DESC;

    private byte priority;

    SOrder() {
        priority = 0;
    }

    SOrder(byte priority) {
        this.priority = priority;
    }

    byte getPriority() {
        return priority;
    }
}
