package com.example.benjamin.guessmaster;

/**
 * this is a singer class that inherits from person. it consists of an additional debut album and release date attribute
 * @see Entity
 * @see Person
 */
public class Singer extends Person {
    private String debutAlbum;
    private Date debutAlbumReleaseDate;

    /**this constructor builds a singer object that inherits from entity
     *@param name is of type string and is the name of the celebrity/country
     *@param born is of type Date and is the date of birth of the celebrity/country
     *@param difficulty is of type double and is the difficulty of the entity (range of 0-1)
     *@param gender is of type string and is the gender of the person you're adding
     *@param debutAlbum is of type string and is the name of the debut album
     *@param debutAlbumReleaseDate is of type Date and is the date of the debut album release date
     */
    public Singer(String name, Date born, double difficulty, String gender, String debutAlbum, Date debutAlbumReleaseDate) {
        super(name, born, difficulty, gender);
        this.debutAlbum = debutAlbum;
        this.debutAlbumReleaseDate = debutAlbumReleaseDate;
    }

    /**
     this is a copy constructor
     @param sing is of type singer and will be copied
     */
    public Singer(Singer sing) {
        super(sing);
        this.debutAlbum = sing.getDebutAlbum();
        this.debutAlbumReleaseDate = sing.getDate();
    }

    /**
     * this is an accessor for the debut album parameter
     * @return string value of the debut album attribute
     */
    public String getDebutAlbum () {
        return debutAlbum;
    }

    /**
     * this is an accessor for the debut album date
     * @return Date value of the debut album release date attribute, as a deep copy
     */
    public Date getDebutAlbumDate () {
        return new Date (debutAlbumReleaseDate);
    }

    /**
     * This is an abstract class when overriden will return the type of entity
     * @return outputs the type of this derive class as a string
     */
    @Override
    public String entityType() {
        return "This entity is a singer.";
    }

    /**
     * Abstract class that will be overriden to return a proper clone of the entity that inherits from this
     * @return deep copy clone of this derive class
     */
    @Override
    public Singer clone() {
        return new Singer (this);
    }

    /**
     * This is overrides the toString method in Person
     * @return calls the super, and adds the album attribute to it and album release date
     */
    @Override
    public String toString() {
        return super.toString() + "\nDebut Album: " + debutAlbum + "\nRelease Date: " + getDebutAlbumDate();
    }
}
