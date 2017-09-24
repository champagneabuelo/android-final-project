# final-project-girafarig
Dennis Huang (dlh4fx)
Jason Valenzuela (jev4zs)

# Features

### Login Screen
The first screen the user will see is a login page. It has information that tells the user what is needed to work the app. The name and date fields are stored on the device, so the next time the user starts the app, the fields will already be filled out. 


### Spotify Authentication
After tapping the button on the first page, a screen will pop up asking the user to login to a Spotify account. Please note that a **Spotify Premium** account is **required**. Please ask either Jason or Dennis for a Spotify Premium account if one is not available. After connecting the app with a Spotify account, the user is then taken to the main screen. 


### Playing Songs
The user can tap on any row of the table in the main screen to start playing a song. The song's name, artist, and album will then appear at the bottom of the screen. The user can pause/resume the song by tapping the play icon. The skip forward and skip previous buttons allow the user to skip forwards/backwards in the playlist. 


### Item Info
The user can tap on one of the "i" icons on the table row. This brings the user to another screen that is a longer description of the theme. A picture is also present to help the user visualize what the theme is about. At the bottom of the screen is a list of the songs in the playlist. The user can play one of these songs by tapping on its text. 


### GPS Implementation
When the app is first started, it will ask the user's permission to use location services. The GPS was used to calculate speed for this app. After walking quickly and passing a speed threshold, an alert will pop up and ask if the user wishes to play the Fast Playlist. If the user slows down, the alert will ask to play the Slow Playlist. 

***Note: The GPS tends to work better outdoors rather than inside a building.***


### Light Sensor Feature
The app also utilizes the device's built in light sensor. In well lit areas, an alert will pop up asking to play the Day Playlist. In dimly lit areas, the alert will ask for the Night Playlist. 

***Note: Since the light sensor rapidly updates, the user will be constantly bombarded with alerts. For now, the user can select "Yes" to the Day Playlist to make it go away. This will be fixed in the final build.***


