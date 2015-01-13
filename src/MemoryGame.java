import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.animation.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class MemoryGame extends Application{
    Player player;
    private String playerName = player.getName();
    private int difficulty = 18; //initial difficulty
    private String cardType = "fruit"; //initial cardType
    private Label lblPlayer;
    
    private int seconds = 0;
    private int tries = 0;
    private Text txtTimer = new Text(seconds + ""); //initial time
    private Text txtTries = new Text(tries + ""); //initial tries
    
    private RadioButton rbEasy = new RadioButton("Easy");
    private RadioButton rbNormal = new RadioButton("Normal");
    private RadioButton rbHard = new RadioButton("Hard");
    private RadioButton rbType1 = new RadioButton("Fruits & Vegetables");
    private RadioButton rbType2 = new RadioButton("Figures");
    private Button btStop = new Button("Stop");
    private Button btNewGame = new Button("New Game");
    BorderPane pane = new BorderPane();
    Timeline animation;
    
    @Override
    public void start(Stage memoryStage){
       
        ToggleGroup group1 = new ToggleGroup();
        rbEasy.setToggleGroup(group1);
        rbNormal.setToggleGroup(group1);
        rbHard.setToggleGroup(group1);
        rbNormal.setSelected(true);
        
        ToggleGroup group2 = new ToggleGroup();
        rbType1.setToggleGroup(group2);
        rbType2.setToggleGroup(group2);
        rbType1.setSelected(true);
        
        //lower screen - Button and Radio buttons
        VBox difficultyBox = new VBox(10);
        difficultyBox.setPadding(new Insets(5));
        Text titleDif = new Text("DIFFICULTY");
        titleDif.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        difficultyBox.getChildren().addAll(titleDif, rbEasy, rbNormal, rbHard);
        
        VBox cardTypeBox = new VBox(10);
        cardTypeBox.setPadding(new Insets(5));
        Text titleCT = new Text("CARD TYPE");
        titleCT.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        cardTypeBox.getChildren().addAll(titleCT, rbType1, rbType2);
       
        HBox buttonsBox = new HBox(10);
        buttonsBox.setPadding(new Insets(25));
        btStop.setMinWidth(80);
        btNewGame.setMinWidth(80);
        buttonsBox.getChildren().addAll(btNewGame, btStop);
        
        HBox lowerBox = new HBox(25);
        lowerBox.getChildren().addAll(difficultyBox, buttonsBox, cardTypeBox);
        lowerBox.setAlignment(Pos.BOTTOM_CENTER);
        
        
        //leftside of screen - player name, score, time
        VBox scoreBox = new VBox(80);
        scoreBox.setPadding(new Insets(100, 20, 5, 20));
        Text txtPlayerName = new Text(playerName);
        txtPlayerName.setFont(Font.font("Cooper Black", 20));
        Label lblTimer = new Label("Seconds");
        Label lblTries = new Label("Tries");
        lblTimer.setFont(Font.font("Cooper Black", 18));
        lblTries.setFont(Font.font("Cooper Black", 18));
        scoreBox.getChildren().addAll(txtPlayerName, lblTries, txtTries, lblTimer, txtTimer);
        txtTries.setFont(Font.font("Cooper Black", FontPosture.REGULAR, 40));
        txtTimer.setFont(Font.font("Cooper Black", FontPosture.REGULAR, 50));
        scoreBox.setAlignment(Pos.TOP_CENTER);
                
       //Memory Pane
        MemoryPane memoryPane = new MemoryPane(difficulty, cardType); 
        
        //style        
        pane.setBottom(lowerBox);
        pane.setLeft(scoreBox);
        pane.setCenter(memoryPane);

        
        lowerBox.setStyle("-fx-background-color: #4097f4");
        scoreBox.setStyle("-fx-background-color: FA2436");
        btNewGame.setStyle("-fx-base: paleturquoise");
        btStop.setStyle("-fx-base: paleturquoise");
        btNewGame.setOnMouseEntered(e -> btNewGame.setEffect(new DropShadow()));
        btNewGame.setOnMouseExited(e -> btNewGame.setEffect(null));
        btStop.setOnMouseEntered(e -> btStop.setEffect(new DropShadow()));
        btStop.setOnMouseExited(e -> btStop.setEffect(null));
        txtPlayerName.setFill(Color.ANTIQUEWHITE);
        txtTimer.setFill(Color.ANTIQUEWHITE);
        txtTries.setFill(Color.ANTIQUEWHITE);
        titleDif.setFill(Color.ANTIQUEWHITE);
        titleCT.setFill(Color.ANTIQUEWHITE);
        rbEasy.setTextFill(Color.ANTIQUEWHITE);
        rbNormal.setTextFill(Color.ANTIQUEWHITE);
        rbHard.setTextFill(Color.ANTIQUEWHITE);
        rbType1.setTextFill(Color.ANTIQUEWHITE);
        rbType2.setTextFill(Color.ANTIQUEWHITE);
        
        //set Scene
        Scene scene = new Scene(pane, 1360, 768);
        memoryStage.setTitle("Memory");
        memoryStage.setScene(scene);
        memoryStage.show();
           
        //Timer 
        animation = new Timeline(
                new KeyFrame(Duration.millis(1000), e->{
                    seconds++;
                    txtTimer.setText(seconds +  "");
                }));
        animation.setCycleCount(Timeline.INDEFINITE);
        

        //Button functions
        btNewGame.setOnAction(e-> reset()); 
        
        btStop.setOnAction(e->{
            reset();
            //terug naar suite
            //verder te implementeren
        });         
        
        rbEasy.setOnAction(e->{
            if(rbEasy.isSelected()){
                difficulty = 8;
                reset();
            }
            
        }); 
        
        rbNormal.setOnAction(e->{
            if(rbNormal.isSelected()){
                difficulty = 18;
                reset();
            }
        }); 
        
        rbHard.setOnAction(e->{
            if(rbHard.isSelected()){
                difficulty = 32;
                reset();
            }
        }); 
        
        rbType1.setOnAction(e->{
            if(rbType1.isSelected()){
                cardType = "fruit";
                reset();
            }
        }); 
        
        rbType2.setOnAction(e->{
            if(rbType2.isSelected()){
                cardType = "figures";
                reset();
            }
        }); 

    }
    
    public void reset(){
        tries = 0;
        txtTries.setText(tries + "");
        seconds = 0;
        txtTimer.setText(seconds + "");
        
        pane.setCenter(new MemoryPane(difficulty, cardType));
    }
    
      
   /* public MemoryGame(Player player){
        this.player = player;
    }*/
    
    /* public Player getPlayer(){
        return player;
    }*/
    
    
    public static void main(String[] args){
        launch(args);
    }
    
    
    
public class MemoryPane extends GridPane {
	private int[][] board;
	private String cardType;
	private Long startTime;
	private Cell[][] cell;
	private ImageView imageView;
	boolean first = true;
	private int numberOfCouples;
       	int i1;
	int j1;
	int i2;
	int j2;
	int count = 0;

	public MemoryPane(int numberOfCouples) {
		this(numberOfCouples, "animals");
	}

	public MemoryPane(int numberOfCouples, String cardType) {
		this.numberOfCouples = numberOfCouples;
		this.cardType = cardType;
		this.setAlignment(Pos.CENTER); // set center
                this.setStyle("-fx-background-color: antiquewhite");
		// creeer matrix board met integers
		int numberRows = (int) Math.sqrt(2 * numberOfCouples);
		int numberColumns = (2 * numberOfCouples) / numberRows;
		board = new int[numberRows][numberColumns];
		cell = new Cell[numberRows][numberColumns];
		int couple = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j] = couple / 2;
				couple++;
			}
		}
		shuffleCards();

		setPadding(new Insets(5, 5, 5, 5));
		setHgap(15);
		setVgap(15);

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				add(cell[i][j] = new Cell(i, j), i, j);
				this.setAlignment(Pos.CENTER);
			}
		}

		startTime = System.currentTimeMillis();
		
		if(checkEndGame())
			System.out.println("Game over!!");
		
	}
	private boolean checkEndGame() {
		return count == numberOfCouples;
	}
	
	private void displayBoard() {
		this.getChildren().clear();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				add(cell[i][j], i, j);
			}
		}
	}

	private void shuffleCards() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				int temp = board[i][j];
				int randomI = (int) (Math.random() * board.length);
				int randomJ = (int) (Math.random() * board[i].length);
				board[i][j] = board[randomI][randomJ];
				board[randomI][randomJ] = temp;
			}
		}
	}

	public class Cell extends StackPane {
		private boolean flipped;
		private int i;
		private int j;

		public Cell(int i, int j) {
			this.i = i;
			this.j = j;

			this.setPrefSize(200, 150);
			this.setOnMouseClicked(e -> handleMouseClick());

			if (!flipped) {
				setBack();
			} else {
				setFront();
			}
		}

		private void setBack() {
			imageView = new ImageView(new Image("/image/" + cardType
					+ "/backCard.jpg"));
			this.getChildren().add(imageView);
		}

		private void setFront() {
			imageView = new ImageView(new Image("/image/" + cardType + "/"
					+ board[getI()][getJ()] + ".jpg"));
			this.getChildren().add(imageView);
		}

		public boolean isFlipped() {
			return flipped;
		}

		private void handleMouseClick() {
                    animation.play();
			if (first) {
				if (!flipped) {
					if (!cell[i1][j1].flipped) 
						cell[i1][j1].setBack();
					if (!cell[i2][j2].flipped) 
						cell[i2][j2].setBack();
					
					cell[getI()][getJ()].flipped = true;
					setStyle("-fx-border-color: skyblue; -fx-border-width: 5px");
					i1 = getI();
					j1 = getJ();
					setFront();
					first = false;
				} else
					System.out.println("Pick another card");
			} else {
                                if (!flipped) {
					i2 = getI();
					j2 = getJ();
					cell[i2][j2].flipped = true;
					setStyle("-fx-border-color: skyblue; -fx-border-width: 5px");
					setFront();
					if (match(i1, j1, i2, j2)) { 
                                                tries++;
                                                txtTries.setText(tries +"");
						System.out.println("match!" + " Tries " + tries);
						count++;
						// This part is a self check, will be replaced
						System.out.println("Count is "+ count);
						if (checkEndGame()) {
                                                    System.out.println("Game is over");
                                                    System.out.println("Time taken: " + (System.currentTimeMillis() - startTime));
                                                    animation.stop();
                                                    pane.setCenter(new EndPane());
                                                    setScore();	
						}
						
					} else { 
                                                tries++;
                                                txtTries.setText(tries +"");
						System.out.println("No match!" + " Tries " + tries);
						cell[i1][j1].flipped = false;
						cell[i2][j2].flipped = false;
						//startTime -= 3000;		// penalty for wrong guess
					}
					first = true;
					cell[i1][j1].setStyle("-fx-border-color: white; -fx-border-width: 0px");
					this.setStyle("-fx-border-color: white; -fx-border-width: 0px");
				}
				else {
					System.out.println("Pick another card");
					cell[i2][j2].flipped = false;
				}
			}
		}
		private void setScore() {
			// needs to implemented
		}
		private boolean match(int i1, int j1, int i2, int j2) {
			System.out.println(board[i1][j1]);
			System.out.println(board[i2][j2]);
			return board[i1][j1] == board[i2][j2];
		}
		
		public int getI() {
			return i;
		}

		public int getJ() {
			return j;
		}

	}
        public int getTries(){
            return tries;
        }
}

public class EndPane extends VBox{
    private Text txtGameOver = new Text("Game is over!");
    private Text txtTriesNeeded = new Text("Number of tries needed: " + tries);
    private Text txtTimeNeeded = new Text("Number of seconds needed: " + seconds);
    
    public EndPane(){
        txtGameOver.setFont(Font.font("Cooper Black", FontPosture.REGULAR, 50));
        txtTriesNeeded.setFont(Font.font("Cooper Black", FontPosture.REGULAR, 50));
        txtTimeNeeded.setFont(Font.font("Cooper Black", FontPosture.REGULAR, 50));
        this.getChildren().addAll(txtGameOver, txtTriesNeeded, txtTimeNeeded);
        this.setAlignment(Pos.CENTER);
        this.setStyle("-fx-background-color: antiquewhite");
        
    }
}


}
