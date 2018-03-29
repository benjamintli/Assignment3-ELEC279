package com.example.benjamin.guessmaster;

/**
 * this is a person class that inherits from entity. it consists of an additional gender attribute
 * @see Entity
 */
public class Person extends Entity {
    private String gender;

    /**this constructor builds a person object that inherits from entity
     *@param name is of type string and is the name of the celebrity/country
     *@param born is of type Date and is the date of birth of the celebrity/country
     *@param difficulty is of type double and is the difficulty of the entity (range of 0-1)
     *@param gender is of type string and is the gender of the person you're adding
     */
    public Person(String name, Date born, double difficulty, String gender) {
        super(name, born, difficulty);
        this.gender = gender;
    }

    /**
     this is a copy constructor
     @param per is of type person and will be copied
     */
    public Person(Person per) {
        super(per);
        this.gender = per.getGender();
    }

    /**
     * This is an abstract class when overriden will return the type of entity
     * @return outputs the type of this derive class as a string
     */
    @Override
    public String entityType() {
        return "This entity is a person";
    }

    /**
     * Abstract class that will be overriden to return a proper clone of the entity that inherits from this
     * @return deep copy clone of this derive class
     */
    @Override
    public Person clone() {
        return new Person(this);
    }

    /**
     * this method is an accessor for gender attribute
     * @return string attribute gender
     */
    public String getGender () {
        return gender;
    }

    /**
     * This is overrides the toString method in Entity
     * @return calls the super, and adds the gender attribute to it
     */
    @Override
    public String toString() {
        return super.toString() + "\nGender: " + gender;
    }
}
