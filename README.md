# WKT Processors

Library reads and writes WKT Geometry strings and geometry objects.
Following geometries are supported:
- POINT
- LINESTRING
- POLYGON
- GEOMETRYCOLLECTION
- MULTIPOINT
- MULTILINESTRING
- MULTIPOLYGON

Use `WKTReader` to read WKT string and `WKTWriter` to convert geometry object to WKT string.
Both access classes delegate to specific geometry writer/reader classes implementing either of `GeometryWKTWriter` 
and `GeometryWKTReader` interfaces.

All writers are thread safe.  