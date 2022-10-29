<script>
  import MenuManager from "../Misc/MenuManager.svelte";

  let TitleText = "Erstelle ein neues Gericht";
  let BottomTitle = "";
  let BottomText = "";
  let bText = "Menu hinzufügen";

  let menu = {
    _id: "",
    gerichtName: "",
    Preis: "",
    zutaten: [],
    BildUrl: "",
  };

  let halo = 2;
</script>

<MenuManager
  bind:menu
  titleText={TitleText}
  bottomTitle={BottomTitle}
  bottomText={BottomText}
  bind:halo
  buttonText={bText}
/>
