package com.example.benjamin.guessmaster;//---Assignment 1--//
//---Benjamin-Li---//
//----20014716-----//
//-----ELEC279-----//

/**
This class is an abstract class of Entity which will be inherited from
 */
public abstract class Entity {
    private String name;
    private Date born;
    private double difficulty;

    /**this constructor builds an object using name and born date
    @param name is of type string and is the name of the celebrity/country
     @param born is of type Date and is the date of birth of the celebrity/country
     @param difficulty is of type double and is the difficulty of the entity (range of 0-1)
     */
    public Entity(String name, Date born, double difficulty) {
        this.name = name;
        this.born = born;
        this.difficulty = difficulty;
    }

    /**
    this is a copy constructor
    @param ent is of type Entity and will be copied
     */
    public Entity(Entity ent) {
        if (ent == null) {
            System.out.println("Error");
            System.exit(0);
        }
        name = ent.getName();
        born = ent.getDate();
        difficulty = ent.getDifficulty();
    }

    /** this is an mutator for name
     * @param name is the name of the celebrity
     */
    public void setName(String name) {
        this.name = name;
    }

    /** this is an mutator for birth date
     * @param born is the birthday (of type date) of the celebrity
     */
    public void setBornDate(Date born) {
        this.born = born;
    }

    /** this is an accessor for name
     * @return name of the celebrity
     */
    public String getName() {
        return name;
    }

    /** this is an accessor for the date that returns a deep copy
     * @return birth date of the entity
     */
    public Date getDate() {
        return new Date(born);
    }

    /** this is an accessor for the difficulty attribute
     * @return difficulty of the entity
     */
    public double getDifficulty() {
        return difficulty;
    }

    /**this is a comparison method that compares if two entities are logically identical
     * @param otherEnt of type entity, is the entity that will be compared to the current entity
     * @return true is they are equivalent, false if not
     */
    public boolean equals(Entity otherEnt) {
        return (name.equals(otherEnt.name) && born.equals(otherEnt.born));
    }

    /** this is a method that returns the awarded tickets for getting something right
    @return the difficulty times 100 and casted to an int
     */
    public int getAwardedTicketNumber() {
        return (int) (100 * difficulty);
    }

    /**this is a toString to output the attributes of the object
     * @return string of the name and born date for debugging and display purposes
     */
    public String toString() {
        return ("Name: " + name + "\nBorn at: " + born.toString());
    }

    /**
     * This is an abstract class when overriden will return the type of entity
     */
    public abstract String entityType();

    /**
     * Abstract class that will be overriden to return a proper clone of the entity that inherits from this
     */
    public abstract Entity clone();

    /**
     * this is a welcome message for the user
     * @return this returns a string for the message that welcomes the user. also calls the entityType method
     */
    public String welcomeMessage() {
        return ("Welcome! Let's start the game! " + entityType());
    }

    /**
     * this is a closing message for the user
     * @return returns a congrats message and calls the toString to show the user what he/she got right
     */
    public String closingMessage() {
        return ("Congratulations! The detailed information of the entity you guess is:\n" + toString());
    }
}
