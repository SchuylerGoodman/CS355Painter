package cs355.solution;

import cs355.GUIFunctions;
import cs355.controller.CS355Controller;
import cs355.controller.CS355ControllerImpl;
import cs355.model.drawing.CS355Drawing;
import cs355.model.drawing.CS355DrawingImpl;
import cs355.view.ViewRefresher;
import cs355.view.ViewRefresherImpl;

import java.awt.*;

/**
 * This is the main class. The program starts here.
 * Make you add code below to initialize your model,
 * view, and controller and give them to the app.
 */
public class CS355 {

	/**
	 * This is where it starts.
	 * @param args = the command line arguments
	 */
	public static void main(String[] args) {

		// Initialize base color
		Color initialColor = Color.WHITE;

		// Construct model, view, and controller
		CS355Drawing model = new CS355DrawingImpl();
		ViewRefresher view = new ViewRefresherImpl(model);
		CS355Controller controller = new CS355ControllerImpl(model, initialColor);

		// Register view with model as observer
		model.addObserver(view);

		// Initialize frame
		GUIFunctions.createCS355Frame(controller, view);

		// Set initial color in frame
		GUIFunctions.changeSelectedColor(initialColor);

		// Start first refresh
		GUIFunctions.refresh();
	}
}