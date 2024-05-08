
# Airport Manager
This was "commissioned" from me by the ATC (Air Traffic Control) tower staff of Gago Coutinho Airport a while ago.

The whole point of this application is for the ATCs to have a visual aid in order to properly manage the Airfield lanes and parking of planes.

I decided to create this application using Java because I distinctly remember struggling with the GUI component of this project when it was first commissioned.
To make things simpler for me due to the complexity of the GUI, I have used the JavaFX package with SceneBuilder. This means the GUI will launch according to the parameters specified in an .xml file. 


# Logic
The idea is that many of the PushBack locations (`Lane` class) on the lane leading to the parking spaces (`Parking` class), have multiple possible parking spaces associated with them. 
They respect the logic shown below (Slice of the airport). 

It's important to note that since we have to account for wind changes, there are two possible Configurations (`AirportConfig` class) with different associations between `Lane` and `Parking` objects.

![Airport Slice](https://github.com/tmmGodinho/AirportManager/assets/39185549/777c3a7b-9975-4abe-a92e-77a68826f2cb)

The objective is therefore to have a GUI representing the desired slice of the airport, which lets you move the planes from parks to lanes and vice-versa, 
while also being explicit about which spots are not viable due to other planes or wind facing.
