package com.example.benjamin.guessmaster;

/**
 * this is a politician class that inherits from person. it consists of an additional party attribute
 * @see Entity
 * @see Person
 */
public class Politician extends Person{
    private String party;

    /**this constructor builds a person object that inherits from entity
     *@param name is of type string and is the name of the celebrity/country
     *@param born is of type Date and is the date of birth of the celebrity/country
     *@param difficulty is of type double and is the difficulty of the entity (range of 0-1)
     *@param gender is of type string and is the gender of the person you're adding
     *@param party is of type string and is the party that the politician is a part of
     */
    public Politician(String name, Date born, double difficulty, String gender, String party) {
        super(name, born, difficulty, gender);
        this.party = party;
    }

    /**
     this is a copy constructor
     @param pol is of type politician and will be copied
     */
    public Politician(Politician pol) {
        super(pol);
        this.party = pol.getParty();
    }

    /**
     * this is an accessor for the party attribute
     * @return outputs the string value of the party attribute
     */
    public String getParty() {
        return party;
    }

    /**
     * This is an abstract class when overriden will return the type of entity
     * @return outputs the type of this derive class as a string
     */
    @Override
    public String entityType() {
        return "This is a politician";
    }

    /**
     * Abstract class that will be overriden to return a proper clone of the entity that inherits from this
     * @return deep copy clone of this derive class
     */
    @Override
    public Politician clone() {
        return new Politician(this);
    }

    /**
     * This is overrides the toString method in Entity
     * @return calls the super, and adds the party attribute to it
     */
    @Override
    public String toString() {
        return super.toString() + "\nParty: " + party;
    }
}
