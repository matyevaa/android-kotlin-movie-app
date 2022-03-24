# Movie Time
Movie time is an app for Android written in Kotlin for tracking movies that the user has watched and for keeping tracking of upcoming releases.

- The app allows users to monitor and browse movies and check the list of top rated and upcoming ones. 
- Users can sign in with their Google account to track their watched movies and gain insight into their watch history. 
- Users can also be notified for when upcoming movies are released in theaters.

<img width="298" alt="Screen Shot 2022-03-15 at 10 21 55 AM" src="https://user-images.githubusercontent.com/16601367/158436143-0ec6e258-353c-4b55-acc4-240123650519.png">

## User Interface
###Navigation Tab:
- Main page (Bottom nav bar)
 - Search bar to search for movie by its title
  - Results are the movies in the list format
  - Each movie entry is clickable and opens a separate tab for each of the result movies (explicit intent)
   - Movie’s details
   - A list of similar movies on the bottom of the page (horizontally scrollable)
 - Browse
  - Top rated movies
  - Upcoming movies 
- User Profile (Top nav bar)
 - Stats and Settings
  - Show a separate tab with the user's information (explicit intent)
  - Enable sign in option with Google account
  - Ability to share stats (explicit intent) [similar to spotify’s year in review]
 - Stats info
  - Watch minutes
  - Longest watched movie
  - Favorite genre
 - Settings
  - Dark Mode
  - Delete Account
- Library of watched movies (Bottom nav bar)
 - List format of movie posters 
 - Movies that have been added to the user's watch list

###Seperate Views:
- Movie Detail
 - A detailed view of a selected movie: should display movie poster along with stats such as duration, year released, Director, Leading Actor, ect. View is reached by selecting a movie in the Library or Browse tab.

###Notifications:
- Movie
 - Now in theaters
  - Notifies the user when a movie has recently been released

Build using the MovieDB API.

![ezgif-2-65cfc86d35](https://user-images.githubusercontent.com/16601367/158436199-101f1f72-65d4-482a-9cd0-e22d6ad3d755.gif)
