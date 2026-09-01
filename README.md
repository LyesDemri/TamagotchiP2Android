# TamagotchiP2Android

September 2025: <br/>
The Santaclautchi should now be completely playable, but missing a few sounds. I have 2 or 3 improvement ideas I'd like to implement to spice things up compared to the original.

April 2026 Update: <br/>

I've now extensively reviewed the code to make it a lot less terrible. I cut up sections of the program into classes of less than 100 lines of code. I've also put in place subfolders to start working on the Santaclautchi <br/>

------- <br/>

This is a reproduction of the P2 and Santaclautchi models of Bandai's 1997 tamagotchis <br/>
This isn't the prettiest code I've written as I wrote it almost entirely on my phone (didn't use Android Studio) (I used APK Builder and Java-NIDE). <br/>
Also, I wasn't very good at Java when I started this project (back in 2020). I'm still getting the hang of it. <br/>
There's an apk of the project in the bin directory ( [https://github.com/LyesDemri/TamagotchiP2Android/blob/main/app/build/output/app-debug.apk] ).<br/>
The apk contains functioning versions of the P2 of the Santaclautchi. <br/>

If you wish to look at the code in Android Studio, you'll have to set up a project and add the files manually, you'll have to know what you're doing.<br/>

To reset it, navigate through the icons using the A button, until "Menu" appears underneath the shell of the Tamagotchi. Hit the B button and then you'll be able to select "Reset" using the A button. Then just hit the B button to reset. You can also create a new Tama from that menu and switch from a tama to the other, although it is simpler to just hit C while no icon is selected to go back to the Tama Select Screen.<br/>
There's a debug mode that gives you more options such as fast forwarding time and viewing variables in real-time. You can access debug mode by either: <br/>
-naming your tama with a name that starts with "DEBUG" <br/>
-for other tamas (whose name don't start with DEBUG), enter the clock by pushing "B" and when in the clock, push "C" 10 consecutive times. <br/>
Fast-forwarding time might produce unexpected results.
