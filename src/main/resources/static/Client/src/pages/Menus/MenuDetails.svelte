<script>
  import axios from "axios";
  import MenuManager from "../Misc/MenuManager.svelte";

  export let params;

  let id;
  id = params.id;
  let visibility = true;
  let TitleText = "Bearbeite Dein Gericht";
  let tTitle = "";
  let tText = "";
  let tButton = "Update Menu";

  let menu = {
    _id: "",
    gerichtName: "",
    Preis: "",
    zutaten: [],
    BildUrl: "",
    BildAlt: "",
  };

  $: {
    id = params.id;
    getMenu();
  }

  async function getMenu() {
    axios.get("http://localhost:3001/api/menus/" + id).then((response) => {
      menu = response.data;
    });
  }

  let halo = id;
</script>

<MenuManager
  bind:menu
  visibilityGerichte={visibility}
  titleText={TitleText}
  bind:halo
  buttonText={tButton}
  bottomTitle={tTitle}
  bottomText={tText}
/>
