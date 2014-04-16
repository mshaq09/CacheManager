Android Cache Manager is an android library that help to cache JSONObjects and JSONArrays using files.

How to add the Library Project to your Project:
-In the Package Explorer, right-click the dependent project and select Properties.
-In the Properties window, select the "Android" properties group at left and locate the Library properties at right.
-Click Add to open the Project Selection dialog.
-From the list of available library projects, select Android Cache Managerand click OK.
-When the dialog closes, click Apply in the Properties window.
-Click OK to close the Properties window

How to use the Library Project:

CacheManagerMainActivity cache = new CacheManagerMainActivity(your Activity);
	cache.getJSONObject("url", "filename");
