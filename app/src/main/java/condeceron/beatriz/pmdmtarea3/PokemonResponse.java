package condeceron.beatriz.pmdmtarea3;

import java.util.List;

public class PokemonResponse {

    private int id;
    private String name;
    private List<TypeWrapper> types;
    private Sprites sprites;
    private int height;
    private int weight;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<TypeWrapper> getTypes() {
        return types;
    }

    public Sprites getSprites() {
        return sprites;
    }

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public static class TypeWrapper {
        private Type type;

        public Type getType() {
            return type;
        }
    }

    public static class Type {
        private String name;

        public String getName() {
            return name;
        }
    }

    public static class Sprites {
        private String front_default;

        public String getFrontDefault() {
            return front_default;
        }
    }
}