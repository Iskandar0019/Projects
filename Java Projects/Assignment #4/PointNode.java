/**
 * This class represents a vertex of a Polygon in a Cartesian plane.
 * @author Timor Tagiev
 * @version 27 June, 2020
 */

public class PointNode
{
   private Point _point; // Instance variables.
   private PointNode _next;

   /**
    * Constructs a new unlinked Vertex.
    * @param point The Point of the vertex.
    */

   public PointNode (Point point)
   {
      if (point != null)
      {
         this._point = new Point (point);
         this._next = null;
      }
   }

   /**
    * Constructs a new linked Vertex.
    * @param point The Point of the new Vertex.
    * @param next The next Vertex to which the new Vertex is linked.
    */

   public PointNode (Point point, PointNode next)
   {
      if (point != null)
      {
         this._point = new Point (point);
         this._next = next;
      }
   }

   /**
    * Constructs a new copy of a Vertex.
    * @param other The Vertex to be copied.
    */

   public PointNode (PointNode other)
   {
      if (other != null)
      {
         this._point = new Point (other._point);
         this._next = other._next;
      }
   }

   /**
    * Point object getter.
    * @return The Point of the Vertex.
    */

   public Point getPoint ()
   {
      return (new Point (this._point));
   }

   /**
    * Vertex object getter.
    * @return The next Vertex to which the invoking Vertex is linked.
    */

   public PointNode getNext ()
   {
      return (this._next);
   }

   /**
    * Point object setter.
    * @param point The Point to be set.
    */

   public void setPoint (Point point)
   {
      this._point = new Point (point);
   }

   /**
    * Vertex object setter.
    * @param next The Vertex to which the invoking Vertex is linked.
    */

   public void setNext (PointNode next)
   {
      this._next = next;
   }
} // End of class PointNode.