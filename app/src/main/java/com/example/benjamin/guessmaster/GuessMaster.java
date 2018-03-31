package com.example.benjamin.guessmaster;
//---Assignment 1--//
//---Benjamin-Li---//
//----20014716-----//
//-----ELEC279-----//

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Random;

/**
 * This is the main class than runs the entity suite of classes. displays information on an XML GUI.
 * This activity is opened via button click from the StartScreen activity
*@author Benjamin Li
*@since 2018-03-29
 * @see Person
 * @see Entity
 * @see Country
 * @see Politician
 * @see Singer
 * @see Date
 * @see StartScreen
 */
public final class GuessMaster extends AppCompatActivity{
	private int numOfEntities;
	private Entity[] entities;
	private int totalTickets;
	private TextView ticketTotal;
	private TextView entityName;
	private Button guessButton;
	private Button nextEntity;
	private ImageView entityImage;
	private EditText userIn;
	private int id;

    /**constructor, initializes entity list to 10
     *
     */
	public GuessMaster() {
		numOfEntities = 0;
		entities = new Entity[10];
	}

	/** add entity to the array
	*@param entity is of the type Entity
	*/
	public void addEntity(Entity entity) {
		entities[numOfEntities++] = entity.clone();
	}


	public Entity[] getEntity () {
		return entities;
	}

	/**	accessor for number of entities
	@return number of entities in the arrayList */
	public int getNumOfEntities() {
		return numOfEntities;
	}


	/** plays the game using some of the functions from the date class when passed an entity type
	*@param entity is of type Entity
     */
	public void playGame(Entity entity) {
		String in = userIn.getText().toString();
		try {
			Date date = new Date(in);
			if (date.precedes(entity.getDate())) {
				dialogLater();
			}
			else if (entity.getDate().precedes(date)) {
				dialogEarlier();
			}
			else {
				totalTickets += entity.getAwardedTicketNumber();
				dialogCorrect(entity);
			}
		}
		catch (Exception e){
			incorrectDialog();
		}
		ticketTotal.setText(getString(R.string.ticket, totalTickets));
   	}

	/** plays the game with the integer id of the entity from the ArrayList
	*@param entityId index of the entity
	*/
	public void playGame(int entityId) {
		playGame(entities[entityId]);
	}

    /**continue game and changeEntity() combined into one method
     *
     */
	public void continueGame() {
		Random rand = new Random();
		// seeds RNG with random non-constant input
		int id = rand.nextInt(numOfEntities);

		imageSetter(id);
		entityName.setText(entities[id].getName());
		userIn.getText().clear();
		userIn.setText("MM/DD/YYYY");
		setId(id);
	}

    /**formats the image so that the image is displayed as a circle
     *
     * @param drawable R.drawable id of the image that needs to be formatted
     */
	public void roundCornerFactory(int drawable) {
		Resources res = getResources();
		Bitmap src = BitmapFactory.decodeResource(res, drawable);
		RoundedBitmapDrawable dr =
				RoundedBitmapDrawableFactory.create(res, src);
		dr.setCornerRadius(Math.max(src.getWidth(), src.getHeight()) / 2.0f);
		entityImage.setImageDrawable(dr);
	}

    /**sets the imageView to the correct image based on the index that is passed in.
     *
     * @param index index of the entity in the entity[] array
     */
	public void imageSetter(int index){
		switch (index){
			case 0:
				roundCornerFactory(R.drawable.justint);
				break;
			case 1:
				roundCornerFactory(R.drawable.celidion);
				break;
			case 2:
				entityImage.setImageResource(R.drawable.usaflag);
				break;
			case 3:
				entityImage.setImageResource(R.drawable.kendricklamar);
				break;
			case 4:
				roundCornerFactory(R.drawable.patrickstar);
				break;
			case 5:
				roundCornerFactory(R.drawable.guyfieri);
				break;
			case 6:
				roundCornerFactory(R.drawable.kermit);
		}
	}

    /** opens up a welcome message dialog when the activity starts. informs the user of the entity name and type
     *
     * @param entity used to get the entity type
     */
	public void welcomeToGame(Entity entity) {
		AlertDialog.Builder welcome = new AlertDialog.Builder(GuessMaster.this);
		welcome.setTitle("GuessMaster Game V3")
				.setMessage(entity.welcomeMessage())
				.setCancelable(false)
				.setNegativeButton("Start game", new DialogInterface.OnClickListener(){
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				Toast.makeText(getBaseContext(), "Game is starting", Toast.LENGTH_SHORT).show();
			}
		});
		AlertDialog dialog = welcome.create();
		dialog.show();
		Typeface font = Typeface.createFromAsset(getAssets(), "lato_regular.ttf");  //set the font to something nicer looking
		TextView message = (TextView) dialog.getWindow().findViewById(android.R.id.message);
		TextView title = (TextView) dialog.getWindow().findViewById(android.R.id.message);
		message.setTypeface(font);
		title.setTypeface(font);
		entityName.setText(entities[id].getName()); //set the textView to the right entity name
		imageSetter(id); //call the image setter to make sure the image underneath the dialog is correct
	}

    /** opens a dialog that informs the user that the date he/she chose was incorrect.
     *
     */
	public void dialogLater() {
		AlertDialog.Builder dialogLater = new AlertDialog.Builder(GuessMaster.this);
		dialogLater.setTitle("Incorrect")
				.setMessage("Try a later date")
				.setNegativeButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
                //do nothing
			}
		});
		AlertDialog dialog = dialogLater.create();
		dialog.show();
		Typeface font = Typeface.createFromAsset(getAssets(), "lato_regular.ttf"); //change the font to something nicer
		TextView message = (TextView) dialog.getWindow().findViewById(android.R.id.message);
		TextView title = (TextView) dialog.getWindow().findViewById(android.R.id.message);
		message.setTypeface(font);
		title.setTypeface(font);
	}

    /** opens a dialog that informs the user that the date he/she chose was incorrect.
     *
     */
	public void dialogEarlier() {
		AlertDialog.Builder dialogEarlier = new AlertDialog.Builder(GuessMaster.this);
		dialogEarlier.setTitle("Incorrect")
				.setMessage("Try an earlier date")
				.setNegativeButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
                //do nothing
			}
		});
		AlertDialog dialog = dialogEarlier.create();
		dialog.show();
		Typeface font = Typeface.createFromAsset(getAssets(), "lato_regular.ttf"); //make font something nicer
		TextView message = (TextView) dialog.getWindow().findViewById(android.R.id.message);
		TextView title = (TextView) dialog.getWindow().findViewById(android.R.id.message);
		message.setTypeface(font);
		title.setTypeface(font);
	}

    /**informs user through dialog box that their answer was correct. prints out additional info about the entity
     *
     * @param entity the entity that the user was trying to guess is passed into here to get the additional information
     */
	public void dialogCorrect(final Entity entity) {
		AlertDialog.Builder correct = new AlertDialog.Builder(GuessMaster.this);
		correct.setTitle("Correct!")
				.setMessage(entity.closingMessage())
				.setNegativeButton("Continue", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				Toast.makeText(getBaseContext(), "You won " + entity.getAwardedTicketNumber() + "tickets", Toast.LENGTH_SHORT).show();
				continueGame(); //when the user hits continue, the next entity is loaded up through calling the continue game method
			}
		});
		AlertDialog dialog = correct.create();
		dialog.show();
		Typeface font = Typeface.createFromAsset(getAssets(), "lato_regular.ttf"); //make the font look nice
		TextView message = (TextView) dialog.getWindow().findViewById(android.R.id.message);
		TextView title = (TextView) dialog.getWindow().findViewById(android.R.id.message);
		message.setTypeface(font);
		title.setTypeface(font);
	}

    /**Informs the user of an incorrect formal
     * if there is an exception thrown when the user types in the date (i.e. incorrect format) the onCreate() calls
     */
	public void incorrectDialog(){
		AlertDialog.Builder welcome = new AlertDialog.Builder(GuessMaster.this);
		welcome.setTitle("Invalid Format")
				.setMessage("Enter date as MM/DD/YYYY")
				.setCancelable(false)
				.setNegativeButton("OK", new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface dialogInterface, int i) {
						userIn.getText().clear();
						userIn.setText(R.string.format);
					}
				});
		AlertDialog dialog = welcome.create();
		dialog.show();
        Typeface font = Typeface.createFromAsset(getAssets(), "lato_regular.ttf"); //set the text to be something nicer looking
        TextView message = (TextView) dialog.getWindow().findViewById(android.R.id.message);
        TextView title = (TextView) dialog.getWindow().findViewById(android.R.id.message);
        message.setTypeface(font);
        title.setTypeface(font);
	}

    /**
     * getter for the random ID generated
     * @return integer of the id
     */
	public int getId(){
		return id;
	}

    /**
     * setter for the ID, called from the continueGame() method
     * @param id integer id
     */
	public void setId(int id){
		this.id = id;
	}


    /**
     * oncreate method is called once when the activity loads. grabs the views from all the UI elements, declares functionality of buttons
     * @param savedInstanceState default variable
     */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		android.support.v7.app.ActionBar ab = getSupportActionBar();
		ab.setTitle(R.string.app_name);
		guessButton = (Button) findViewById(R.id.guessButton);
		nextEntity = (Button) findViewById(R.id.nextEntity);
		userIn = (EditText) findViewById(R.id.date);
		entityImage = (ImageView) findViewById(R.id.entityImage);
		ticketTotal = (TextView) findViewById(R.id.tickets);
		entityName = (TextView) findViewById(R.id.entityName);
		Typeface font = Typeface.createFromAsset(getAssets(), "lato_regular.ttf"); //set font to something nicer
		entityName.setTypeface(font);
		ticketTotal.setTypeface(font);
		guessButton.setTypeface(font);
		nextEntity.setTypeface(font);
		userIn.setTypeface(font);


		Politician trudeau = new Politician("Justin Trudeau", new Date("December", 25, 1971), 0.25, "Male", "Liberal");
		Singer dion = new Singer("Celine Dion", new Date("March", 30, 1961), 0.5, "Female", "La voix du bon Dieu", new Date("November", 6, 1981));
		Country usa = new Country("United States", new Date("July", 4, 1776), 0.1, "Washington D.C.");
		Person kendrickLamar = new Person ("Kendrick Lamar", new Date ("June", 17, 1987), 0.5, "Male");
		Person patrickStarMeme = new Person ("Patrick Star", new Date ("January", 1, 1900), 1, "Male");
		Person flavorTown = new Person ("Guy Fieri", new Date("January", 22, 1968), 1, "Male");
		Person kermit = new Person ("Kermit the Frog", new Date("May", 9, 1955), 1, "Male");

		ticketTotal.setText(getString(R.string.ticket, 0)); //ensure that the ticket total is reset to 0
		addEntity(trudeau);
		addEntity(dion);
		addEntity(usa);
		addEntity(kendrickLamar);
		addEntity(patrickStarMeme);
		addEntity(flavorTown);
		addEntity(kermit);
		welcomeToGame(entities[0]);//open the welcome to game dialog with first entity added to the array

        //this is an inner class that declares the functionality of the EditText. in this case,
        // it clears the text field when clicked if the default "MM/DD/YYYY" text is there
		userIn.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view) {
			    if (userIn.getText().toString().equals("MM/DD/YYYY"))
			        userIn.setText("");
			}
		});

		//this is an inner class that declares the functionality of the "submit guess" button
        // when clicked, it calls the playGame method and passes it the ID of the image and entityName that
        // is currently loaded up on the screen
		guessButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view) {
				playGame(entities[getId()]);
			}
		});

		//this calls the continue game method when clicked, changing the imageView and text on screen to the next
        // random entity
		nextEntity.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view) {
				continueGame();
			}
		});

		//this is an inner class that listens for the enter key on the virtual keyboard. this way the user
        // can submit their guessed date using the enter key as well as the submit button
		userIn.setOnKeyListener(new View.OnKeyListener(){
			@Override
			public boolean onKey(View view, int i, KeyEvent keyEvent) {
				if(keyEvent.getAction() == KeyEvent.ACTION_DOWN) {
					if(i == KeyEvent.KEYCODE_ENTER) {
						guessButton.callOnClick();
						return true;
					}
				}
				return false;
			}
		});
    }
}
