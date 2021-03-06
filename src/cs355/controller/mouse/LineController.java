package cs355.controller.mouse;

import cs355.GUIFunctions;
import cs355.model.drawing.CS355Drawing;
import cs355.model.drawing.Line;
import cs355.model.drawing.Shape;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Created by goodman on 9/10/2015.
 */
public class LineController implements IMouseEventController {

    /**
     * The index of the shape being controlled in the model.
     */
    private int index;

    public LineController() {
        index = -1;
    }

    @Override
    public void mouseClicked(MouseEvent e, CS355Drawing model, Color c) {
    }

    @Override
    public void mousePressed(MouseEvent e, CS355Drawing model, Color c) {

        // Set initial coordinates
        Point2D.Double initialCoordinates = new Point2D.Double();
        initialCoordinates.setLocation(e.getPoint());

        Point2D.Double endPoint = new Point2D.Double(0.0, 0.0);

        // Create new line
        Line line = new Line(c, initialCoordinates, endPoint);

        // Add line to model and save index
        this.index = model.addShape(line);
    }

    @Override
    public void mouseReleased(MouseEvent e, CS355Drawing model, Color c) {
    }

    @Override
    public void mouseEntered(MouseEvent e, CS355Drawing model, Color c) {
    }

    @Override
    public void mouseExited(MouseEvent e, CS355Drawing model, Color c) {
    }

    @Override
    public void mouseDragged(MouseEvent e, CS355Drawing model, Color c) {

        // Get shape at saved index from model and verify it is a line
        Shape shape = model.getShape(this.index);
        if (!(shape instanceof cs355.model.drawing.Line)) {
            GUIFunctions.printf("Invalid shape - expected cs355.model.drawing.Line at index %d", this.index);
            return;
        }

        // Cast the shape to a line
        Line line = (Line) shape;

        // Get current coordinates in object space
        Point2D.Double currentCoordinates = new Point2D.Double();
        AffineTransform worldToObj = line.getWorldToObj();
        worldToObj.transform(e.getPoint(), currentCoordinates);

        // Save the new coordinates
        line.setEnd(currentCoordinates);
    }

    @Override
    public void mouseMoved(MouseEvent e, CS355Drawing model, Color c) {
    }

    @Override
    public void close() {

    }
}
