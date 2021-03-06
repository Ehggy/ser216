import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class AboutState extends State{

	private Texture aboutPage;
	private Texture backUp;
	private Texture backDown;

	private int backX;
	private int backY;

	private int backLeft;
	private int backRight;
	private int backTop;
	private int backBottom;

	private boolean backPressed;
	private boolean changingState;
	private float timer;

	private SpriteBatch batch;

	public AboutState(StateManager manager, SpriteBatch batch){
		super(manager);

		this.batch = batch;

		aboutPage = new Texture(Gdx.files.internal("about_page.png"));
		backUp = new Texture(Gdx.files.internal("back_up.png"));
		backDown = new Texture(Gdx.files.internal("back_down.png"));

		backX = 50;
		backY = 50;

		backLeft = backX;
		backRight = backX + backUp.getWidth();
		backTop = backY + backUp.getHeight();
		backBottom = backY;

		timer = 0;

	}

	@Override
	public void handleInput() {

		coords.x = Gdx.input.getX();
		coords.y = Gdx.input.getY();
		camera.unproject(coords);

		if(Gdx.input.justTouched()){

			if((coords.x >= backLeft && coords.x <= backRight) && (coords.y >= backBottom && coords.y <= backTop)){

				backPressed = true;
				changingState = true;

			}

		}

	}

	@Override
	public void update(float dt) {

		handleInput();

		if(changingState) timer += dt;

		if(changingState && (timer > .1)){

			manager.setState(new MenuState(manager, batch));

		}


	}

	@Override
	public void render() {

		batch.setProjectionMatrix(camera.combined);
		batch.begin();

		batch.draw(aboutPage, 0, 0);
		if(backPressed){
			batch.draw(backDown, backX, backY);
		}else{
			batch.draw(backUp, backX, backY);
		}

		batch.end();

	}

}
