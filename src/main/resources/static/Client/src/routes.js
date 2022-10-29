// Pages
import Dashboard from "./pages/Dashboard.svelte";
import Login from "./pages/Login.svelte";


import Menus from "./pages/Menus/Menus.svelte";
import MenuDetails from "./pages/Menus/MenuDetails.svelte";
import CreateMenu from "./pages/Menus/CreateMenu.svelte";

import Support from "./pages/SupportingComponents/Support.svelte";
import Account from "./pages/Misc/Account.svelte";

import Zutaten from "./pages/Zutaten/Zutaten.svelte";


//Dev
import ToDo from "./pages/To Do/ToDo.svelte";
import Overview from "./pages/Overview/Overview.svelte";
export default {
  // Home
  "/": Dashboard,
  "/home": Dashboard,

  "/Login": Login,

  // Menus
  "/menus": Menus,
  "/menus/:id": MenuDetails,
  "/createmenu": CreateMenu,

  //Sonstiges
  "/support": Support,
  "/account": Account,

  //Zutaten
  "/zutaten": Zutaten,

  //To Do
  "/todo": ToDo,

  //Overview
  "/overview":Overview,
};
