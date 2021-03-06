package cs355.model.drawing;

import cs355.model.drawing.selectable.CircleHandle;
import cs355.model.drawing.selectable.Handle;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

/**
 * Add your square code here. You can add fields, but you cannot
 * change the ones that already exist. This includes the names!
 */
public class Square extends Shape {

	// The size of this Square.
	private double size;

	/**
	 * Basic constructor that sets all fields.
	 * @param color the color for the new shape.
	 * @param center the center of the new shape.
	 * @param size the size of the new shape.
	 */
	public Square(Color color, Point2D.Double center, double size) {

		// Initialize the superclass.
		super(color, center);

		// Square has one rotation handle.
		this.setNumHandles(1);

		// Set the field.
		this.size = size;
	}

	/**
	 * Getter for this Square's size.
	 * @return the size as a double.
	 */
	public double getSize() {
		return size;
	}

	/**
	 * Setter for this Square's size.
	 * @param size the new size.
	 */
	public void setSize(double size) {
		this.size = size;

		this.setChanged();
		this.notifyObservers();
	}

	/**
	 * Add your code to do an intersection test
	 * here. You shouldn't need the tolerance.
	 * @param pt = the point to test against.
	 * @param tolerance = the allowable tolerance.
	 * @return true if pt is in the shape,
	 *		   false otherwise.
	 */
	@Override
	public boolean pointInShape(Point2D.Double pt, double tolerance) {

		// get the point in object coordinates.
		AffineTransform worldToObj = this.getWorldToObj();
		Point2D.Double ptObj = new Point2D.Double();
		worldToObj.transform(pt, ptObj);

		double halfSize = this.getSize() / 2.0;

		// check the bounds of the square.
		Point2D.Double bound = new Point2D.Double(halfSize, halfSize);
		if (Math.abs(ptObj.getX()) > bound.getX() || Math.abs(ptObj.getY()) > bound.getY()) {
			return false;
		}

		return true;
	}

	@Override
	public double getMinimumY() {
		return -1 * ( this.getSize() / 2 );
	}

	@Override
	public void updateHandles(double zoomFactor) {
		for (Handle handle : this.getHandles()) {
			handle.updateHandle(this.getCenter(), this.getCenter(), zoomFactor);
		}
	}
}
