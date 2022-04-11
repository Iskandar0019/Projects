/**
 * This class represents a convex Polygon in a Cartesian plane as a singly linked list.
 * @author Timor Tagiev
 * @version 27 June, 2020
 */

public class Polygon
{
   private PointNode _head; // The head of the singly linked list.

   /**
    * Constructs a Polygon with no Vertices.
    */

   public Polygon ()
   {
      _head = null;
   }

   /**
    * Adds a Vertex to the Polygon. Time complexity - O (n). Space complexity O (1).
    * @param point The Point of the Vertex to be added.
    * @param position The Position in the list in which the new Vertex will be added. Positions begin at index 1.
    * @return True if the vertex was successfully added. False otherwise.
    */

   public boolean addVertex (Point point, int position)
   {
      boolean added;

      if ((position >= 1) && (position <= (this.totalVertices () + 1)) && (point != null)) // Checks passed parameters.
      {
         if (this._head == null) // Checks if the list is empty.
         {
            this._head = new PointNode (point);
            added = true;
         }
         else
         {
            added = true;
            PointNode pointer = this._head;

            while (pointer != null) // Checks if the Vertex is already present in the Polygon.
            {
               if (pointer.getPoint ().equals (point))
               {
                  added = false;
               }

               pointer = pointer.getNext ();
            }

            if (added) // If the Vertex is not present, inserts the Vertex in the designated position.
            {
               pointer = this._head;

               for (int i = 1; i < (position - 1); i++)
               {
                  pointer = pointer.getNext ();
               }

               pointer.setNext (new PointNode (point,pointer.getNext ()));
            }
         }
      }
      else
      {
         added = false;
      }

      return (added);
   }

   /**
    * A String representation of a Polygon object. Time complexity - O (n). Space complexity O (1).
    * @return A list of the Vertices that comprise the Polygon object.
    */

   public String toString ()
   {
      String result = "The polygon has " + this.totalVertices () + " ";

      if (this.totalVertices () == 0) // Checks if the list is empty.
      {
         result += "vertices.";
      }
      else
      {
         result += "vertices:\n(";

         PointNode pointer = this._head;

         while (pointer.getNext () != null) // Concatenates the String representation of the Polygon.
         {
            result += pointer.getPoint ().toString () + ",";
            pointer = pointer.getNext ();
         }

         result += pointer.getPoint ().toString () + ")";
      }

      return (result);
   }

   /**
    * Determines the highest Point of the Polygon. Time complexity - O (n). Space complexity O (1).
    * @return The highest Point of the Polygon. Null if the list is empty.
    */

   public Point highestVertex ()
   {
      Point highestPoint = null;

      if (this.totalVertices () != 0) // Checks if the list is empty.
      {
         PointNode pointer = this._head;
         highestPoint = this._head.getPoint ();

         while (pointer != null) // Searches for the highest Point.
         {
            if (pointer.getPoint ().isAbove (highestPoint))
            {
               highestPoint = pointer.getPoint ();
            }

            pointer = pointer.getNext ();
         }

         highestPoint = new Point (highestPoint);
      }

      return (highestPoint);
   }

   /**
    * Determines the index of a Vertex in the Polygon if present. Time complexity - O (n). Space complexity O (1).
    * @param point The Point which index is required.
    * @return The index of the passed Point. -1 if the Vertex is not in the Polygon.
    */

   public int findVertex (Point point)
   {
      int position = -1;

      if (point != null)
      {
         PointNode pointer = this._head;

         for (int i = 1; i <= this.totalVertices (); i++) // Searches for the Point.
         {
            if (pointer.getPoint ().equals (point))
            {
               position = i; // Point is found. Index is updated.
            }

            pointer = pointer.getNext ();
         }
      }

      return (position);
   }

   /** Determines the Vertex to which the passed Point is linked. Time complexity - O (n). Space complexity O (1).
    * Returns the next Vertex to which the passed Point is linked to.
    * @param point The Point which is linked to the returned Vertex.
    * @return The Vertex to which the passed Point is linked.
    */

   public Point getNextVertex (Point point)
   {
      Point nextVertex = null;

      if (point != null)
      {
         if (this._head != null) // Checks if the list is empty.
         {
            if (this.totalVertices () == 1) // Checks if there is only one Vertex in the Polygon.
            {
               nextVertex = new Point (this._head.getPoint ());
            }
            else
            {
               PointNode pointer = this._head;

               while (pointer.getNext () != null) // Searches for the Point.
               {
                  if (pointer.getPoint ().equals (point))
                  {
                     nextVertex = new Point (pointer.getNext ().getPoint ()); // The Point was found.
                  }

                  pointer = pointer.getNext ();
               }

               if (pointer.getPoint ().equals (point)) // Checks if the Point is the last Point.
               {
                  nextVertex = new Point (this._head.getPoint ());
               }
            }
         }
      }

      return (nextVertex);
   }

   /**
    * Determines the perimeter of the Polygon. Time complexity - O (n). Space complexity O (1).
    * @return The perimeter of the Polygon. 0 if the Polygon is a single Point.
    */

   public double calcPerimeter ()
   {
      double perimeter = 0;

      if (this.totalVertices () >= 2) // Checks if the Polygon contains more than 1 Vertex.
      {
         PointNode pointer = this._head;

         if (this.totalVertices () == 2) // The Polygon is a straight line.
         {
            perimeter = pointer.getNext ().getPoint ().distance (pointer.getPoint ());
         }
         else
         {
            while (pointer.getNext () != null) // Calculates the perimeter of all sides but the last one.
            {
               perimeter += pointer.getPoint ().distance (pointer.getNext ().getPoint ());

               pointer = pointer.getNext ();
            }

            perimeter += pointer.getPoint ().distance (this._head.getPoint ()); // Adds the last side of the Polygon.
         }
      }

      return (perimeter);
   }

   /**
    * Determines the area of the Polygon. Time complexity - O (n). Space complexity O (1).
    * @return The area of the Polygon. 0 if the Polygon is a single Point or a straight line.
    */

   public double calcArea ()
   {
      double area = 0, side1 = 0, side2 = 0, side3 = 0, halfPerimeter = 0; // Local variables.

      if (this.totalVertices () >= 3) // Checks if the Polygon is not a single Point or a straight line.
      {
         PointNode pointer = this._head;

         Point point1 = this._head.getPoint ();

         while (pointer.getNext ().getNext () != null) // Determines the area of the Polygon as a sum of the comprising triangles.
         {
            Point point2 = pointer.getNext ().getPoint (); // Determines the points of the triangles.
            Point point3 = pointer.getNext ().getNext ().getPoint ();

            side1 = point1.distance (point2); // Calculates the sides and half of the perimeter of a triangle.
            side2 = point1.distance (point3);
            side3 = point2.distance (point3);
            halfPerimeter = (side1 + side2 + side3) / 2.0;

            area += this.calcTriangleArea (side1,side2,side3,halfPerimeter); // Adds the area of the triangle.

            pointer = pointer.getNext ();
         }
      }

      return (area);
   }

   /**
    * Determines whether the area of the invoking Polygon object is greater than the passed Polygon object.
    * Time complexity - O (n). Space complexity O (1).
    * @param other The other Polygon to be compared.
    * @return True of the invoking Polygon's area is greater than the passed Polygon's area. False otherwise.
    */

   public boolean isBigger (Polygon other)
   {
      return ((other != null) && (this.calcArea () > other.calcArea ()));
   }

   /**
    * Determines the minimum bounding rectangle (MBR) of the Polygon. Time complexity - O (n). Space complexity O (1).
    * @return The minimum bounding rectangle (as a Polygon) of the Polygon. Null if the invoking Polygon is a single Point or a line.
    */

   public Polygon getBoundingBox ()
   {
      Polygon boundingBox = null;

      if (this.totalVertices () >= 3)
      {
         PointNode pointer = this._head;
         Point leftMost = this._head.getPoint (), rightMost = this._head.getPoint (); // Initializes extremity Points.
         Point lowest = this._head.getPoint (), highest = this._head.getPoint ();

         while (pointer != null) // Searches for the leftmost, rightmost, lowest and highest Points of the Polygon.
         {
            leftMost = (pointer.getPoint ().isLeft (leftMost)) ? (pointer.getPoint ()) : leftMost;
            rightMost = (pointer.getPoint ().isRight (rightMost)) ? (pointer.getPoint ()) : rightMost;
            lowest = (pointer.getPoint ().isUnder (lowest)) ? (pointer.getPoint ()) : lowest;
            highest = (pointer.getPoint ().isAbove (highest)) ? (pointer.getPoint ()) : highest;

            pointer = pointer.getNext ();
         }

         Point lowerLeft = new Point (leftMost.getX (), lowest.getY ()); // Determines the bounding Vertices of the bounding Polygon.
         Point lowerRight = new Point (rightMost.getX (), lowest.getY ());
         Point upperRight = new Point (rightMost.getX (),highest.getY ());
         Point upperLeft = new Point (leftMost.getX (), highest.getY ());

         boundingBox = new Polygon (); // Constructs the bounding rectangle.

         boundingBox.addVertex (lowerLeft,1);
         boundingBox.addVertex (lowerRight,2);
         boundingBox.addVertex (upperRight,3);
         boundingBox.addVertex (upperLeft,4);
      }

      return (boundingBox);
   }

   /* Private Methods */

   private int totalVertices () // Calculates the number of vertices of the Polygon.
                                // Time complexity - O (n). Space complexity O (1).
   {
      int counter = 0;

      PointNode pointer = _head;

      while (pointer != null)
      {
         counter++;
         pointer = pointer.getNext ();
      }

      return (counter);
   }

   /* Calculates the area of triangle using Herod's formula */
   /* Time complexity - O (1). Space complexity O (1). */

   private double calcTriangleArea (double side1, double side2, double side3, double halfPerimeter)
   {
      double area;

      area = Math.sqrt (halfPerimeter * (halfPerimeter - side1) * (halfPerimeter - side2) * (halfPerimeter - side3));

      return (area);
   }
} // End of class Polygon.
