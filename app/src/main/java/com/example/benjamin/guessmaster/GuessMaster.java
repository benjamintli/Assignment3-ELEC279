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
 * This is the main class than runs the entity suite of classes. plays the guessmaster game with the user where they guess stuff
*@author Benjamin Li
*@since 2018-03-29
 * @see Person
 * @see Entity
 * @see Country
 * @see Politician
 * @see Singer
 * @see Date
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

	public GuessMaster() {
		numOfEntities = 0;
		entities = new Entity[10];
	}

	/** add entity to the ArrayList
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

		ticketTotal.setText(getString(R.string.ticket, totalTickets));
   	}

	/** plays the game with the integer id of the entity from the ArrayList
	*@param entityId index of the entity
	*/
	public void playGame(int entityId) {
		playGame(entities[entityId]);
	}

    /**plays the game with a random entity from the ArrayList
     */
	public void continueGame() {
		Random rand = new Random();
		// seeds RNG with random non-constant input
		int id = rand.nextInt(numOfEntities);

		imageSetter(id);
		entityName.setText(entities[id].getName());
		userIn.getText().clear();
		setId(id);
	}

	public void roundCornerFactory(int drawable) {
		Resources res = getResources();
		Bitmap src = BitmapFactory.decodeResource(res, drawable);
		RoundedBitmapDrawable dr =
				RoundedBitmapDrawableFactory.create(res, src);
		dr.setCornerRadius(Math.max(src.getWidth(), src.getHeight()) / 2.0f);
		entityImage.setImageDrawable(dr);
	}
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
		}
	}

	public void welcomeToGame(Entity entity) {
		AlertDialog.Builder welcome = new AlertDialog.Builder(GuessMaster.this);
		welcome.setTitle("GuessMaster_Game_V3")
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
		entityName.setText(entities[id].getName());
		imageSetter(id);
	}

	public void dialogLater() {
		AlertDialog.Builder dialogLater = new AlertDialog.Builder(GuessMaster.this);
		dialogLater.setTitle("Incorrect")
				.setMessage("Try a later date")
				.setNegativeButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {

			}
		});
		AlertDialog dialog = dialogLater.create();
		dialog.show();
	}

	public void dialogEarlier() {
		AlertDialog.Builder dialogEarlier = new AlertDialog.Builder(GuessMaster.this);
		dialogEarlier.setTitle("Incorrect")
				.setMessage("Try an earlier date")
				.setNegativeButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {

			}
		});
		AlertDialog dialog = dialogEarlier.create();
		dialog.show();
	}

	public void dialogCorrect(final Entity entity) {
		AlertDialog.Builder correct = new AlertDialog.Builder(GuessMaster.this);
		correct.setTitle("Correct!")
				.setMessage(entity.closingMessage())
				.setNegativeButton("Continue", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				Toast.makeText(getBaseContext(), "You won " + entity.getAwardedTicketNumber() + "tickets", Toast.LENGTH_SHORT).show();
				continueGame();
			}
		});
		AlertDialog dialog = correct.create();
		dialog.show();
	}

	public int getId(){
		return id;
	}

	public void setId(int id){
		this.id = id;
	}


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		android.support.v7.app.ActionBar ab = getSupportActionBar();
		ab.setTitle("Guessmaster V3");
		guessButton = (Button) findViewById(R.id.guessButton);
		nextEntity = (Button) findViewById(R.id.nextEntity);
		userIn = (EditText) findViewById(R.id.date);
		entityImage = (ImageView) findViewById(R.id.entityImage);
		ticketTotal = (TextView) findViewById(R.id.tickets);
		entityName = (TextView) findViewById(R.id.entityName);
		Politician trudeau = new Politician("Justin  Trudeau", new Date("December", 25, 1971), 0.25, "Male", "Liberal");
		Singer dion = new Singer("Celine Dion", new Date("March", 30, 1961), 0.5, "Female", "La voix du bon Dieu", new Date("November", 6, 1981));
		Country usa = new Country("United States", new Date("July", 4, 1776), 0.1, "Washington D.C.");
		Person kendrickLamar = new Person ("Kendrick Lamar", new Date ("June", 17, 1987), 0.5, "Male");
		Person patrickStarMeme = new Person ("Patrick Star", new Date ("January", 1, 1900), 1, "Male");
		Person flavorTown = new Person ("Guy Fieri", new Date("January", 22, 1968), 1, "Male");

		ticketTotal.setText(getString(R.string.ticket, 0));
		addEntity(trudeau);
		addEntity(dion);
		addEntity(usa);
		addEntity(kendrickLamar);
		addEntity(patrickStarMeme);
		addEntity(flavorTown);
		welcomeToGame(entities[0]);

		userIn.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view) {
				userIn.setText("");
			}
		});

		guessButton.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view) {
				playGame(entities[getId()]);
			}
		});

		nextEntity.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View view) {
				continueGame();
			}
		});

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
