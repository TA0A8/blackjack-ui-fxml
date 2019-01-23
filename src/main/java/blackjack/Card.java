package blackjack;

public class Card {
    private boolean face;
    private boolean ace;
    private String name;
    private int value;
    private int suite; //1=Spades, 2=Heart, 3=Clubs, 4=Diamonds

    public Card(String name, int suite) {
        this.name = name;
        this.suite = suite;
        this.ace = false;
        this.face = false;
        if (this.name.equals("Ace")) {
            this.ace = true;
            this.setValue(11);
        } else if (this.name.equals("Jack") || this.name.equals("Queen") || this.name.equals("King")) {
            this.face = true;
            this.setValue(10);
        } else {
            this.setValue(Integer.valueOf(name));
        }
    }

    public boolean isFace() {
        return face;
    }

    public boolean isAce() {
        return ace;
    }

    public String getName() {
        return name;
    }

    public int getSuite() {
        return suite;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String toString() {
        String suiteString = "";
        switch (suite) {
            case 1:
                suiteString = "Spades";
                break;
            case 2:
                suiteString = "Hearts";
                break;
            case 3:
                suiteString = "Clubs";
                break;
            case 4:
                suiteString = "Diamonds";
                break;
        }
        return (name + " of " + suiteString);
    }

}
