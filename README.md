# RainForest-News

<p float="left">
<img src="https://camo.githubusercontent.com/166f8a4393a23bf0b713c32d2504a6424e076e44/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f706c6174666f726d2d416e64726f69642d677265656e2e737667" alt="Platform" data-canonical-src="https://img.shields.io/badge/platform-Android-green.svg" style="max-width:100%;">
<img src="https://camo.githubusercontent.com/498f19805b2c1326abfd0f122bb670d2929aa987/68747470733a2f2f696d672e736869656c64732e696f2f6769746875622f6c6963656e73652f646179382f72652d6672616d652e737667" alt="License" data-canonical-src="https://img.shields.io/github/license/day8/re-frame.svg" style="max-width:100%;">
<p/>


**Introduction**
> Find news about the earth and its climate

>New and Improved version can be found here: https://github.com/jmreyesortiz/GreenNewsApp

**Libraries / Tools**

* AsyncTaskLoader 
* HttpURLConnection
* Picasso
* GSON

**App Architecture**

MVC Architecture using AsynTaskLoader for JSON parsing.

**Features**
- Search news about various important topics about the earth.
- Obtain these news from the NewsApi
- Browse the news with your favorite browser

**Screenshots**
<p float="left">
<img src="images/screen2.jpg" alt="alt text" width="172.7" height="307.04">
<img src="images/screen4.jpg" alt="alt text" width="172.7" height="307.04">
<img src="images/screen5.jpg" alt="alt text" width="172.7" height="307.04">



**Installation**
1. Clone or Download the Repo
2. Go to newsapi.org, sign up and obtain an APIKEY
3. Go to `app/src/main/java/com.juanma.greennews/QueryUtils.Java` and change the variable ` private static final String API_KEY ="Your_API_Key"; ; ` to your obtained api key. Then go to 
4. After this, you'll be able to run the app.
