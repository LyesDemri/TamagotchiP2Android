# TamagotchiP2Android

November 2026: <br/>
Added a mute functionality (Holding A then pressing C like on the real tamas. You need to press on the shell, not the grey rectangular buttons). Similarly, I added a pause function (Also A and C while in the clock). Pausing the tama desyncs the Tamagotchi from real world time. You can then resync it by entering debug mode (go to the clock and press C 10 times). While in debug mode, navigate to the menu, hit B, then select "Resync Tama". Be careful, the Tama will be fast forwarded to the current time and any thing that was supposed to happen will all happen at once. For example, if your tama was sick before resyncing, it might die during the resyncing process. I encourage you to fast forward time in increments of 5 minutes until the Tama time is close to the real time, before resyncing. That way if anything was to happen to your tama, you can care for them as things happen. <br/>
The ability to fast forward also means your tama's time can be at some point in the future. Fast forwarding the tama also desyncs the tama. If your tama time is in the future, and you resync it, the tama will basically be paused while the app is closed, until the real world time catches up to your tama time. Basically, if your tama is in advance by 1 hour, and you resync it and leave the app, nothing more will happen to your tama for the next hour while the app is closed.<br/>
I know this all sounds very confusing, but please keep in mind, pausing and fast forwarding time was never the intended behavior of the app (and of the actual tamagotchis).

October 2026: <br/>
The Santaclautchi is now be completely playable. <br/>

April 2026 Update: <br/>

I've now extensively reviewed the code to make it a lot less terrible. I cut up sections of the program into classes of less than 100 lines of code. I've also put in place subfolders to start working on the Santaclautchi <br/>

------- <br/>

This is a reproduction of the P2 and Santaclautchi models of Bandai's 1997 tamagotchis <br/>
This isn't the prettiest code I've written as I wrote it almost entirely on my phone (didn't use Android Studio) (I used APK Builder, Java-NIDE and JStudio). <br/>
Also, I wasn't very good at Java when I started this project (back in 2020). I'm still getting the hang of it. <br/>
There's an apk of the project in the main directory ( [https://github.com/LyesDemri/TamagotchiP2Android/blob/main/MyTama_211120261935.apk] ).<br/>
The apk contains functioning versions of the P2 of the Santaclautchi. <br/>

If you wish to look at the code in Android Studio, you'll have to set up a project and add the files manually, you'll have to know what you're doing.<br/>

To reset it, navigate through the icons using the A button, until "Menu" appears underneath the shell of the Tamagotchi. Hit the B button and then you'll be able to select "Reset" using the A button. Then just hit the B button to reset. You can also create a new Tama from that menu and switch from a tama to the other, although it is simpler to just hit C while no icon is selected to go back to the Tama Select Screen.<br/>
There's a debug mode that gives you more options such as fast forwarding time and viewing variables in real-time. You can access debug mode by either: <br/>
-naming your tama with a name that starts with "DEBUG" <br/>
-for other tamas (whose name don't start with DEBUG), enter the clock by pushing "B" and when in the clock, push "C" 10 consecutive times. <br/>
Fast-forwarding time might produce unexpected results.
