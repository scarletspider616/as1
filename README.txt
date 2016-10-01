Welcome to joeymich-HabitTracker!
Name: Joey-Michael Fallone
CCID: joeymich
VIDEO URL: https://youtu.be/zV9Yzjt8H7I
Usage is quite simple. 

Front Page (MainHabitActivity):
	When you load the app you will see the current day of the week displayed at the top. 
	You will also see a list of currently existing habits that are supposed to be completed
	on the current day of the week. If you don't see any habits yet, don't worry, you can
	add some later! 

	Habits displayed in red have not been completed in the past week! Habits in black
	text have been completed in the past week (ie - have been completed recently)
		(Black was chosen just for appearence, the code directly checks if it's been 
		completed. You can see it in RecentColoredAdapter)

	To view habits that are supposed to be completed on a different day of the week, 
	click on the CHANGE DAY button in the top right corner and then cick on the day
	of the week you wish to view. 

	To view more details about a habit click on the habit in the list (See habit detail)

	To add a new habit, click on New Habit (see New Habit)

Habit Detail (HabitDetailActivity):
	Here you will see a list (as well as count) of completions. 

	To add a completion for the current time/date click/touch on the COMPLETE button

	To delete a completion simply click/touch it and a dialog will ask if you want to 
	delete it. Hit YES to confirm. 

	To change the original creation/start date of the habit, click/touch the MODIFY
	button and choose your date in the dialog that pops up. Hit OK to confirm.

	To delete a habit simply click/touch the DELETE button. Hit YES to confirm. 

New Habit (NewHabit):
	After clicking NEW HABIT on the main page you will be prompted to enter the habit
	description. Once you have typed in the description, hit the SUBMIT button. 
	After hitting SUBMIT, you will be asked what days the habit should be completed on. 
	Check as many days as you want and hit the SUBMIT button when you're ready!






Attributions (Common Knowledge):
	As per forum discussion, sources where "common knowlege" was gained are listed here. 
	Places in the code where these things were used are also commented with the links
	if you would like to see what specifically was used and how. 

	For licensing of original work and it's derivations, please see the license.txt file
	in this directory. 

	lonelyTwitter class code helped me learn the following: 
		- file I/O
		- listview
		- unit testing
		- button clicking

	http://stackoverflow.com/questions/5574673/what-is-the-easiest-way-to-get-the-current-day-of-the-week-in-android
	- How to get the current day of the week in java

	http://theopentutorials.com/tutorials/android/listview/android-multiple-selection-listview/
	- How to get data from checked list (SparseBooleanArray)

	https://developer.android.com/reference/android/widget/Button.html
    - How to use an automated onClick listener through XML attribute

    http://stackoverflow.com/questions/4721626/how-to-get-the-current-context
    - How to get current application context

    http://stackoverflow.com/questions/11213699/is-it-possible-to-make-a-checkbox-radio-button-uncheckable
    - android:clickable XML property