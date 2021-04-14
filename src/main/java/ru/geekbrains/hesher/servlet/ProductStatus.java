package ru.geekbrains.hesher.servlet;

public enum ProductStatus {
    CREATE {
        @Override
        public String toString() {
            return "CREATE";
        }
    }
    , PAID, DELIVERED, CANCELED;

}