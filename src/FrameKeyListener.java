import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JOptionPane;

public class FrameKeyListener implements KeyListener {

	// REFERENCES TO OTHER CLASSES
	private GameLogic gl;
	private Display d;
	
	// CONSTRUCTOR
	public FrameKeyListener(GameLogic gl, Display d)
	{
		this.gl = gl;
		this.d = d;
	}

	// OTHER METHODS
	@SuppressWarnings("deprecation")
	private void changeGridSize(String tempGridSize)
	{
		if(!gl.gameFinished)
		{
			int response = JOptionPane.showConfirmDialog(d.f, "Changing to the new grid will abandon the current game.\n" +
					"Press 'Yes' if you wish to proceed");
			if(response == 0)
			{
				d.f.dispose();
				gl.timerThread.interrupt();
				gl.timerThread.stop();
				new Display(tempGridSize, d.animate);
			}
		}
		else
		{
			d.f.dispose();
			gl.timerThread.interrupt();
			new Display(tempGridSize, d.animate);				
		}
	}
	private void newGame() {
		if(!gl.gameFinished)
		{
			int response = JOptionPane.showConfirmDialog(d.f, "The current game has not been finished.\n" +
					"Are you sure you want to start a new one?");
			if(response == 0)
			{
				gl.resetGame();
			}
		}
		else
		{
			gl.resetGame();			
		}
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		if(arg0.isControlDown() && arg0.getKeyCode() == 84) { // Ctrl & t key pressed
			d.animate = (d.animate) ? false : true;
			d.animationStatus.setText(d.animate ? "Animations: On" : "Animations: Off");
		}

		if((arg0.isControlDown() && arg0.getKeyCode() == 83) // Ctrl & s key pressed
				|| (arg0.isControlDown() && arg0.getKeyCode() == 77) // Ctrl & m key pressed
				|| (arg0.isControlDown() && arg0.getKeyCode() == 76)) { // Ctrl & l key pressed
			
			String tempGridSize = "";
			if (arg0.getKeyCode() == 83) 
				tempGridSize = "small"; 
			else if (arg0.getKeyCode() == 77) 
				tempGridSize = "medium"; 
			else if (arg0.getKeyCode() == 76) 
				tempGridSize = "large"; 
			
			changeGridSize(tempGridSize);
		}
		if((arg0.isControlDown() && arg0.getKeyCode() == 78) // Ctrl & n key pressed
				|| (arg0.isControlDown() && arg0.getKeyCode() == 32)) { // Ctrl & space pressed
			newGame();
			
		}
	}
	@Override
	public void keyReleased(KeyEvent arg0) {		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {	
	}
	
}
