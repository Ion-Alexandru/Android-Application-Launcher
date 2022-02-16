@@ -0,0 +1,50 @@
# applauncher-application-Ion-Alexandru
applauncher-application-Ion-Alexandru created by GitHub Classroom

The project above is meant to be a very basic version of an app launcher for android.

To make the project, first I used an android layout for my MainActivity to place an EdiText to later use as a search bar, a Button for searching and a ListView,
so the apps can be seen.

And also a linear layout, called applist_item, that contains a TextView for the name of the app and an ImageView for the icon.

First class created is a class called App, which is later used to create a list of said class. So that for each "App", we can attripute a string for the anme of
the app, a drawable for the icon of the app and an info of type ResolveInfo, from which we will extract data from the packagemanager of the device.

In the MainActivity class, first all the layout elements are declared as well as a list of "Apps" from the class created above.

-In the onCreate function, we are going to allocate our layout elements to the variables in our activity.

-After everything is allocated, we call a function named setAppList, which takes our list of apps.

-We also create a new adapter of type MyAdapter, that extends an array adapter and which takes as contructor a context, in our case, the MainActivity and our list
of apps.

-For the button, we allocate an OnClickListener, which will, first of all, delete all the list items in our list of apps, so that we can easily rewrite the listView
with new elements each time we want to search for a specific app.
  
  After we also convert what we wrote in the Editext to a string, we take an intent to only get the applications that are launchable and a list of type ResolveInfo,
from which we can get all activities of our intent type using a packagemanager.

  And using a for loop, we go through each element in our list, and we verify if the application is a system application or service, in which case we skip it and we
also verify if what we entered in the EditText, is contained in the name of the apps we return from the packagemanager.we make a new "app" of type App, we attribute 
to it the package label, icon and info, and we add it to the list of apps.

  In the case in which the text entered by the user in the EditText doesn't correspond to any application installed in the system, using an intent we parse an URL to
google play search URL which will include the user's typed text;

  After which the adapter is notified that changes have been made and the adapter is set to the list.
  
-To finish off MainActivity, there is the setAppList function that will also return a list of apps of type App, but with all the installed apps in the system, so
that when the app is opened, the user can see all the apps without search conditions.

The class MyAdapter extends an ArrayAdapter of the type App, because we use a list of type App as constructor.

-First, a constructor is built to allocate for every specific item of the list, the layout, the context and the list of apps.

-A function getView of type View is then called, where the current position of the app is allocated from the list and an inflater htat instantiates the contents of the
layout xml files into their corresponding View objects.

-The view is checked and inflated to the layout created to show the icon and the name of each app which are allocated to the layout from each App created.

-Then the view is returned for the adapter to set in the list.