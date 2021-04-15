# CS455-Final-Project
![cover](https://user-images.githubusercontent.com/79466152/114063294-b1596500-9855-11eb-9806-6b5630f2fe98.png)

<h4 align="center">This app would feature an easy way for the user to track their income expense. This app intends to help the user be more responsible for their budget and re-evaluate their spending habit.</h4>

<p align="center">
  <a href="#key-features">Key features</a> •
  <a href="#installation">Installation</a> •
  <a href="#user-manual">User manual</a> •
  <a href="#troubleshooting-tips">Troubleshooting tips</a> •
  <a href="#bug-list">Bug list</a> •
  <a href="#manifest">Manifest</a> •
  <a href="#architecture">Architecture</a> •
  <a href="#planned-features">Planned features</a> •
  <a href="#license">License</a> •
  <a href="#credits">Credits</a> •
  <a href="#thanks">Thanks</a> •
  <a href="#contact-info">Contact Info</a>
</p>

## Key Features
Track your Expense and Income.

![create new income gif](https://user-images.githubusercontent.com/79466152/114067151-b7514500-9859-11eb-968d-a2ab79f0e78a.gif)
![Create new expense gif](https://user-images.githubusercontent.com/79466152/114067157-bae4cc00-9859-11eb-801a-c3e89254208f.gif)

  
Display the list of your current list of expenses and income.

![expense list gif](https://user-images.githubusercontent.com/79466152/114067305-ea93d400-9859-11eb-914d-c8ce185507a9.gif)
![income list gif](https://user-images.githubusercontent.com/79466152/114067312-ebc50100-9859-11eb-9966-f6e5bb7e1529.gif)

  
Delete an item off the lists.

![delete income item gif](https://user-images.githubusercontent.com/79466152/114766013-1e23a200-9d23-11eb-9f3e-76bd1116c2f9.gif)
![delete expense item gif](https://user-images.githubusercontent.com/79466152/114766018-1ebc3880-9d23-11eb-8039-a0fc7c822bbd.gif)


Delete all the items off the list.

![delete all items in list gif](https://user-images.githubusercontent.com/79466152/114633264-756f3700-9c7d-11eb-824c-acf59e32a4b7.gif)


Dark mode.

![dark mode gif](https://user-images.githubusercontent.com/79466152/114291715-c812de00-9a46-11eb-9a6a-76b9f0157bec.gif)


## Installation

You can install Mo Money 101 by downloading the [APK](https://github.com/laanh200/CS455-Final-Project/releases/) from GitHub and install it.

*This will require Android Studio already installed on your machine.

## User manual
To add a new expense or income item
* Select the according button on the landing screen.

![Add new expense button](https://user-images.githubusercontent.com/79466152/114067853-83c2ea80-985a-11eb-939e-099a38507e65.png)![Add new income button](https://user-images.githubusercontent.com/79466152/114067864-858cae00-985a-11eb-9ee6-7db2996d61e0.png)


* New Expense or Income – Enter all the required fields and select save.

![new expense](https://user-images.githubusercontent.com/79466152/114068109-c389d200-985a-11eb-88d1-ed6df7bb077f.PNG)![new income](https://user-images.githubusercontent.com/79466152/114068113-c5539580-985a-11eb-957f-169021fa2028.PNG)

To access the current list of expenses or income.
* Select the navigation drawer icon on the landing screen.

![Click on navigation drawer menu](https://user-images.githubusercontent.com/79466152/114068227-ed42f900-985a-11eb-8481-10e284aac1b9.png)![nav view - expense list](https://user-images.githubusercontent.com/79466152/114068246-f3d17080-985a-11eb-9c86-4ce5df6bb856.png)![nav view - income](https://user-images.githubusercontent.com/79466152/114068256-f633ca80-985a-11eb-8313-5c4fa55ec26f.png)

To delete an item from a list. Swipe left or right.

![swipe to delete](https://user-images.githubusercontent.com/79466152/114291754-1627e180-9a47-11eb-898e-4146c6332bde.png)
![delete item](https://user-images.githubusercontent.com/79466152/114327670-3adb9200-9af7-11eb-9f5d-868459e42564.PNG)

To delete all the items from a list.

![delete all item](https://user-images.githubusercontent.com/79466152/114633330-90da4200-9c7d-11eb-8b4d-6317d2cc5dac.PNG)
![delete all item question](https://user-images.githubusercontent.com/79466152/114633336-920b6f00-9c7d-11eb-82ef-ad227d9ef7ef.PNG)


To turn on dark mode. 
* The switch in the navigation drawer. Turn on the switch.

![dark mode](https://user-images.githubusercontent.com/79466152/114291737-fa244000-9a46-11eb-97b1-0376864b9cc0.PNG)
![dark mode show](https://user-images.githubusercontent.com/79466152/114327734-78401f80-9af7-11eb-9932-daff3d5d49ba.PNG)

## Troubleshooting tips

Restart the application.

## Bug list

The expense and income total can take sometime to update due to main thread query.

## Manifest

![Mo Money 101 Manifest](https://user-images.githubusercontent.com/79466152/114445715-a2442100-9b8d-11eb-9ff5-480448febdee.png)


## Architecture

This application is based on the MVC (Model View Controller) paradigm.

![Expense VCM diagram](https://user-images.githubusercontent.com/79466152/114067565-3181c980-985a-11eb-84a9-92cef9b7ad20.jpg)

![Income VCM diagram](https://user-images.githubusercontent.com/79466152/114067595-3a729b00-985a-11eb-9c4f-2775f5b946b0.jpg)


## Planned features
*	Landscape layout. - Done
*	The ability to modify an item in the Expense and Income list. Add Fragment UI for better reusability.
*	The ability to restore deleted item.
*	Improve display layout for Expense and Income List. - Done
*	Display the current total expense and income in Main Activity view.
*	Run advertisement at the bottom.
*	Improve backend code for Database access. Update the date field inputs.
*	Add more types into the Expense item type.
*	Add a name column to the Income list.
*	The ability to sort list item by amount, date, name, type.
*	Push notification to enter daily expenses and income.
*	The ability to export the list into CSV. An option on the list view.
*	The ability to backup current data to the cloud.
*	Toggle for dark theme layout. - Done
*	Create pie charts to show the spending habits. A menu option on the navigation view.

## License
All rights are reserved to [@laanh200]( https://github.com/laanh200). Nobody else can use, copy, share, or change it. The only rights granted are the rights to look at it and fork the project to look at it in their own account. 

## Credits
This application was created from the following packages:
- [Markdownify](https://github.com/amitmerchant1990/electron-markdownify) – Readme file reference
-	[Room Database](https://developer.android.com/reference/android/arch/persistence/room/RoomDatabase)
-	[Androidx Lifecycle](https://developer.android.com/jetpack/androidx/releases/lifecycle)
-	[Kotlin Coroutines](https://developer.android.com/kotlin/coroutines)
-	[Reference Room Database video](https://www.youtube.com/watch?v=lwAvI3WDXBY)
-	[Reference Navigation Drawer video](https://www.youtube.com/watch?v=do4vb0MdLFY)

## Thanks
Dr. T's hamster.

## Contact Info
>GitHub [@laanh200]( https://github.com/laanh200)

![cover1](https://user-images.githubusercontent.com/79466152/114063330-bae2cd00-9855-11eb-93b0-993b6e1c492e.png)
