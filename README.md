# CyberRent - A House Renting System

Built as a part of Object Oriented Analysis and Design class at Multimedia University, Cyberjaya.
## Built by -
| Name                     | ID                |
| -----------              | -----------       |
| Anirban Bala Pranto      | 1181202317        |
| Lee Jia Rou              | 1191101639        |
| Tee Song Hee             | 1191101539        |
| Chew Shu Zhang           | 1191303059        |

## How to run
* Clone the repo
* The project can be opened in vscode by modifying you folder name and path in launch.json
* The project can be opened in intellij by modifying you folder name and path
* The project can be compiled and run in BlueJ without any modification

## High Level Design of the System
* GlobalState model is a singleton that binds the entire system together. The singleton object maintains consistency of data across all controllers.
* The view folder contains .fxml views that bind with controllers in controller folder.
* The controllers can process data received from the view and can update the view, they can also create new objects from model and mutate the global data.
* Everytime there is a mutation in globalstate, we rewrite our database which is stored as .csv files.
* Models are the entities that exist in the system (including the GlobalState itself)

## Preview
* Home Page
<br/>

![Homepage](/assets/homepage.gif)

* Login
<br/>

![Login](/assets/login.gif)

* Favorites
<br/>

![Favorites](/assets/favorites.gif)

* My List
<br/>

![My List](/assets/mylist.gif)