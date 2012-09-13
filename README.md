SmartCommuter Web Service
=================

This web service will be used to connect to all of the external web services that provide realtime information for public transport companies. This enables SmartCommuter Android Application and Web Client to use one web service for multiple services. It also ensures that it always returns the correct content to the devices

**Service WSDL**  

The Web Service Description Language can be located using the following URL:  

[smartcommuterws.cloudfoundry.com/services/RealTimeWebService?wsdl](http://smartcommuterws.cloudfoundry.com/services/RealTimeWebService?wsdl)

**Service ERP**  

The Service ERP can be located using the following URL:  

[smartcommuterws.cloudfoundry.com/services/RealTimeWebService](http://smartcommuterws.cloudfoundry.com/services/RealTimeWebService)

Operations
-------

* **getStationData(stationType, stationApiCode):** This is the recommended operation that can be used to return the real time data for a public transport station. It takes a Station Type and Station API Code as strings and returns a list of StationData objects.  
The following example is used to get the latest station data from [Tallaght Luas Stop](http://smartcommuterws.cloudfoundry.com/services/RealTimeWebService/getStationData?stationType=Luas&stationApiCode=Tallaght).

* **getBusEireannStationData(apiCode):** This operation is used to return the real time data for a Bus Éireann station. It takes a valid Bus Éireann Station API Code as a string and returns a list of StationData objects.  
The following example is used to get the latest station data from [Ferns Bus Stop](http://smartcommuterws.cloudfoundry.com/services/RealTimeWebService/getBusEireannStationData?apiCode=Ferns).

* **getDublinBusStationData(apiCode):** This operation is used to return the real time data for a Dublin Bus station. It takes a valid Dublin Bus Station API Code as a string and returns a list of StationData objects.  
The following example is used to get the latest station data from [Lower Kilmacud Road Dublin Bus Stop 4477](http://smartcommuterws.cloudfoundry.com/services/RealTimeWebService/getDublinBusStationData?apiCode=4477).

* **getIrishRailStationData(apiCode):** This operation is used to return the real time data for an Irish Rail station. It takes a valid Irish Rail Station API Code as a string and returns a list of StationData objects.  
The following example is used to get the latest station data from [Rosslare Europort Rail Station](http://smartcommuterws.cloudfoundry.com/services/RealTimeWebService/getIrishRailStationData?apiCode=RLEPT).

* **getJJKavanaghStationData(apiCode):** This operation is used to return the real time data for a JJ Kavanagh & Sons station. It takes a valid JJ Kavanagh & Sons Station API Code as a string and returns a list of StationData objects.  
The following example is used to get the latest station data from [JJKavanagh Dublin Airport Bus Stop](http://smartcommuterws.cloudfoundry.com/services/RealTimeWebService/getJJKavanaghStationData?apiCode=7).

* **getLuasStationData(apiCode):** This operation is used to return the real time data for a Luas station. It takes a valid Luas Station API Code as a string and returns a list of StationData objects.  
The following example is used to get the latest station data from [Balally Luas Stop](http://smartcommuterws.cloudfoundry.com/services/RealTimeWebService/getLuasStationData?apiCode=Balally).

Author
-------

**Shane Doyle**

+ http://twitter.com/iamshanedoyle
+ http://github.com/iamshanedoyle

