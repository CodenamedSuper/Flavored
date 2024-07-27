package codenamed.flavored.helper;

import net.minecraft.util.StringIdentifiable;

public enum Color implements StringIdentifiable {

    none("none"),

    red("red"),

    pink("pink"),
    magenta("magenta"),
    purple("purple"),
    green("green"),
    lime("lime"),
    yellow("yellow"),
    orange("orange"),
    wheat("wheat"),

    brown("brown"),

    oil("oil");

    private final String name;

    private Color(String color) {
        this.name = color;
    }

    public String toString() {
        return this.name;
    }

    public String asString() {
        return this.name;
    }
}