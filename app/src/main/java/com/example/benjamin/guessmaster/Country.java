package com.example.benjamin.guessmaster;

/**
 * this is a country class that inherits from entity. it consists of an additional capital attribute
 * @see Entity
 */
public class Country extends Entity {
    private String capital;


    /**this constructor builds a Country object that inherits from entity
     *@param name is of type string and is the name of the celebrity/country
     *@param born is of type Date and is the date of birth of the celebrity/country
     *@param difficulty is of type double and is the difficulty of the entity (range of 0-1)
     *@param capital is of type string and is the capital of the country you're adding
     */
    public Country(String name, Date born, double difficulty, String capital) {
        super(name, born, difficulty);
        this.capital = capital;
    }

    /**
     this is a copy constructor
     @param c is of type country and will be copied
     */
    public Country(Country c) {
        super(c);
        capital = c.getCapital();
    }

    /**
     * Abstract class that will be overriden to return a proper clone of the entity that inherits from this
     * @return deep copy clone of this derive class
     */
    @Override
    public Country clone() {
        return new Country(this);
    }

    /**
     * This is an abstract class when overriden will return the type of entity
     * @return outputs the type of this derive class as a string
     */
    @Override
    public String entityType() {
        return "This entity is Country!";
    }

    /**
     * this is an accessor for the capital attribute
     * @return string capital attribute
     */
    public String getCapital() {
        return capital;
    }

    /**
     * This is overrides the toString method in Entity
     * @return calls the super, and adds the capital attribute to it
     */
    @Override
    public String toString() {
        return super.toString() + "\nCapital: " + capital;
    }
}
