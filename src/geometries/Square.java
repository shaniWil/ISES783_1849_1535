package geometries;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Square extends Polygon {
    public Square(Point firstPoint ,Point secondPoint,Point thirdPoint, Point fourthPoint) {
        super(firstPoint,secondPoint,thirdPoint, fourthPoint);
    }

    @Override
    protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
        Triangle triangle1 = new Triangle(vertices.get(2), vertices.get(1), vertices.get(0));
        Triangle triangle2 = new Triangle(vertices.get(0), vertices.get(3), vertices.get(2));
        if (triangle1.findGeoIntersections(ray) == null)
        {
            var geoList = triangle2.findGeoIntersections(ray);
            return geoList == null ? null : geoList.stream().map(gp -> (new GeoPoint(this, gp.point))).toList();
        }
        var geoList = triangle1.findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> (new GeoPoint(this, gp.point))).toList();
    }
}
