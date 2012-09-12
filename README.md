SmartCommuter Web Service
=================

This web service will be used to connect to all of the external web services that provide realtime information for public transport companies. This enables SmartCommuter Android Application and Web Client to use one web service for multiple services. It also ensures that it always returns the correct content to the devices

Operations
-------

* **getStationData:** This is the recommended operation that can be used to return the real time data for a public transport station. It takes a Station Type and Station API Code as strings and returns a list of StationData objects

* **getBusEireannStationData:** This operation is used to return the real time data for a Bus Éireann station. It takes a valid Bus Éireann Station API Code as a string and returns a list of StationData objects.

* **getDublinBusStationData:** This operation is used to return the real time data for a Dublin Bus station. It takes a valid Dublin Bus Station API Code as a string and returns a list of StationData objects.

* **getIrishRailStationData:** This operation is used to return the real time data for an Irish Rail station. It takes a valid Irish Rail Station API Code as a string and returns a list of StationData objects.

* **getJJKavanaghStationData:** This operation is used to return the real time data for a JJ Kavanagh & Sons station. It takes a valid JJ Kavanagh & Sons Station API Code as a string and returns a list of StationData objects.

* **getLuasStationData:** This operation is used to return the real time data for a Luas station. It takes a valid Luas Station API Code as a string and returns a list of StationData objects.

Author
-------

**Shane Doyle**

+ http://twitter.com/iamshanedoyle
+ http://github.com/iamshanedoyle

