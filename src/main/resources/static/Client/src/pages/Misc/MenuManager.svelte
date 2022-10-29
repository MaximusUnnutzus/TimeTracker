<script>
  import axios from "axios";
  import { X } from "lucide-svelte";
  import Unsplash from "../SupportingComponents/SearchUnsplash.svelte";
  import { onMount } from "svelte";
  import { fade } from "svelte/transition";

  export let titleText = "This is a Title:)";
  export let bottomTitle = "This is a BottomText";
  export let bottomText = "Loren Ipsum Dipsum";
  export let buttonText = "leerer Button";
  export let halo;
  export let visibilityBild = true;

  export let visibilityGerichte = false;

  let zutatenliste = [];
  let ergebnisliste = [];
  let userInput;

  export let menu = {
    _id: "",
    gerichtName: "",
    Preis: "",
    Kategorie: "",
    zutaten: [],
    BildUrl: "",
  };

  //ALL AXIOS REQUESTS
  async function getZutaten() {
    axios.get("http://localhost:3001/api/zutaten").then((response) => {
      zutatenliste = response.data;
    });
  }

  function addMenu() {
    let tempId = "";
    axios
      .post("http://localhost:3001/api/menus", menu)
      .then((response) => {
        tempId = response.data;
        putGerichtNameIntoZutat(tempId);
        resetAll();
        alert("Menu wurde erfolgreich hinzugefügt");
      })
      .catch((error) => {
        console.log(error);
        alert(error);
      });
  }

  async function updateMenu() {
    axios
      .put("http://localhost:3001/api/menus/" + halo, menu)
      .then((response) => {});
    deleteSingleGerichtFromIngredient();
    putGerichtNameIntoZutat();
    alert("Menu wurde erfolgreich aktualisiert");
  }

  //Basefunctions of MenuManager
  function InputChecker() {
    if (!menu.Preis.includes(".")) {
      if (isNaN(menu.Preis)) {
        alert(
          "Bitte nur Zahlen verwenden. Anstelle eines Kommas bitte einen Punkt ;)"
        );
      }
    }
  }

  function checkForSimilarity() {
    ergebnisliste.splice(0, ergebnisliste.length + 1);
    zutatenliste.forEach((zutat) => {
      if (
        zutat.zutatName.toLowerCase().includes(userInput.toLowerCase()) &&
        ergebnisliste.length < 10
      ) {
        ergebnisliste.push(zutat);
        ergebnisliste = ergebnisliste;
      }
    });
  }

  function addZutat(ergebnis) {
    let counter = 0;
    checkVisibility();
    if (menu.zutaten) {
      if (!menu.zutaten.includes(ergebnis)) {
        menu.zutaten.push(ergebnis);
        menu.zutaten = menu.zutaten;
      }
    }
    userInput = "";
    progressBar();
  }

  let deletedIngredients = [];
  function deleteSingleIngredient(inp) {
    const index = menu.zutaten.indexOf(inp);
    deletedIngredients.push(menu.zutaten[index]);
    menu.zutaten.splice(index, 1);
    menu.zutaten = menu.zutaten;
  }

  //Anzeige der Container => CreateMenu oder Menu Details
  function decisions() {
    if (halo == 2) {
      addMenu();
    } else if (halo !== 2) {
      updateMenu();
    }
  }

  function checkIfDetailsFromMenuDetails() {
    if (halo !== 2) {
      visibilityBild = false;
    } else {
      visibilityBild = true;
    }
  }

  export function checkVisibility() {
    visibilityGerichte = true;
    if (!visibilityGerichte) {
      visibilityGerichte = true;
    }
  }

  //m:n Gericht in Zutat hinzufügen
  async function putGerichtNameIntoZutat(inp) {
    //Durch alle Zutaten des Menus iterieren (eineZutatAusMenu = ObjectID von der Zutat)
    menu.zutaten.forEach((eineZutatAusMenu) => {
      let tempId = "";

      if (inp) {
        tempId = inp;
        console.log("im Here");
      } else {
        tempId = menu._id;
      }
      let einzelneZutat = {
        _id: "",
        zutatName: "",
        inGerichten: [],
      };
      //die eine Zutat vom Server holen
      axios
        .get("http://localhost:3001/api/zutaten/" + eineZutatAusMenu)
        .then((response) => {
          einzelneZutat = response.data;
          console.log(einzelneZutat.zutatName);
          //Wenn der Array der einzelneZutat für die GerichteNamen noch nicht existiert, neu erstellen
          if (!einzelneZutat.inGerichten) {
            einzelneZutat.inGerichten = [];

            //Check, ob im Array der Name existiert, wenn ja dann Push
          }
          if (!einzelneZutat.inGerichten.includes(tempId)) {
            einzelneZutat.inGerichten.push(tempId);
          }

          //Das neue Array einzelneZutat per put-Request überschreiben
          axios
            .put(
              "http://localhost:3001/api/zutaten/" + einzelneZutat._id,
              einzelneZutat
            )
            .then((response) => {});
        });
      einzelneZutat.inGerichten = [];
    });
  }

  //m:n Gericht aus Zutat löschen

  async function deleteSingleGerichtFromIngredient() {
    deletedIngredients.forEach((eineZutatAusMenu) => {
      console.log(eineZutatAusMenu);
      let einzelneZutat = {
        _id: "",
        zutatName: "",
        inGerichten: [],
      };
      axios
        .get("http://localhost:3001/api/zutaten/" + eineZutatAusMenu)
        .then((response) => {
          einzelneZutat = response.data;

          if (einzelneZutat.inGerichten.includes(menu._id)) {
            //Array Filtern und alle die nicht = menu._id sind in inGerichten
            einzelneZutat.inGerichten = einzelneZutat.inGerichten.filter(
              (item) => {
                return item != menu._id;
              }
            );
          }
          axios
            .put(
              "http://localhost:3001/api/zutaten/" + einzelneZutat._id,
              einzelneZutat
            )
            .then((response) => {});
        });
      einzelneZutat.inGerichten = [];
    });
  }

  function resetAll() {
    menu = {
      gerichtName: "  ",
      Preis: "",
      zutaten: [],
      BildUrl: "",
    };
    visibilityBild = true;
    visibilityGerichte = false;
    progress = 0;
  }

  //ProgressBar
  let progress = 0;
  let progressText;

  let name = 0;
  let preis = 0;
  let kategorie = 0;
  let zutat = 0;

  function progressBar() {
    console.log(progress);

    if (name != 1 && menu.gerichtName != "") {
      progress += 25;
      name = 1;
      console.log("gericht");
    }
    if (preis != 1 && menu.Preis != "") {
      progress += 25;
      preis = 1;
      console.log("preis");
    }
    if (kategorie != 1 && menu.Kategorie != "-- Kategorie auswählen --") {
      progress += 25;
      kategorie = 1;
      console.log("kateg");
    }
    if (zutat != 1 && menu.zutaten.length >= 1) {
      progress += 25;
      zutat = 1;
      console.log("zut");
    }

    progressText = "width: " + progress + "%";
    console.log(progressText);
  }

  onMount(checkIfDetailsFromMenuDetails, getZutaten());
</script>

<!--Titel (wird dynamisch angepasst)-->
<div class="container" id="component">
  <div class="row" id="firstrow">
    <div class="col" id="col-CM">
      <h1>{titleText}</h1>
    </div>
    {#if menu.zutaten.length >= 1 && menu.gerichtName != "" && menu.Preis != "" && menu.Kategorie != "-- Kategorie auswählen --"}
      <div class="col" id="col-CM">
        <button type="submit" class="btn" on:click={decisions}
          >{buttonText}</button
        >
      </div>
    {/if}
  </div>
  <!--ProgressBar (wird dynamisch angezeigt: bei erstelltem Menu ausgelassen)-->
  {#if halo == 2}
    <div class="progress">
      <div
        class="progress-bar"
        role="progressbar"
        style={progressText}
        aria-valuenow="25"
        aria-valuemin="0"
        aria-valuemax="100"
      />
    </div>
  {/if}

  <!--Erste Box mit Preis, Name, Kategorie und Suche-->
  <div class="row align-items-start" id="secondrow">
    <div class="col-xl-6 cold-flex align-items-center" id="smaller-col">
      <div class="box">
        <div class="input-group input-group mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text" id="inputname">Name </span>
          </div>
          <input
            type="text"
            class="form-control"
            aria-label="Small"
            aria-describedby="inputGroup-sizing-sm"
            bind:value={menu.gerichtName}
            on:change={progressBar}
          />
        </div>

        <div class="input-group input-group mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text" id="inputname">Preis </span>
          </div>
          <input
            type="text"
            class="form-control"
            aria-label="Small"
            aria-describedby="inputGroup-sizing-sm"
            bind:value={menu.Preis}
            on:input={InputChecker}
            on:change={progressBar}
          />
        </div>
        <div class="input-group input-group mb-3">
          <div class="input-group-prepend">
            <span class="input-group-text" id="inputname">Kategorie </span>
          </div>
          <div class="mb-3">
            <select
              id="inputState"
              class="form-select"
              bind:value={menu.Kategorie}
              on:change={progressBar}
            >
              <option selected disabled>-- Kategorie auswählen --</option>
              <option>Vorspeise</option>
              <option>Hauptspeise</option>
              <option>Dessert</option>
              <option>Getränke</option>
            </select>
          </div>
        </div>

        <!-- Input Zutat, es braucht die zweite Array List "ergebnisliste", in diese werden in der Funktion
            checkForSimilarity alle Zutaten geladen, die den Anforderungen entsprechen-->
        <div class="search-wrapper">
          <label for="search">Zutaten durchsuchen</label>
          <input
            type="text"
            class="form-control"
            aria-label="Small"
            aria-describedby="inputGroup-sizing-sm"
            id="Searchbar"
            bind:value={userInput}
            on:input={checkForSimilarity}
          />
        </div>
        <div class="zutat">
          {#if userInput !== ""}
            {#each ergebnisliste as ergebnis}
              <button class="btn" on:click={addZutat(ergebnis._id)}
                >{ergebnis.zutatName}</button
              >
            {/each}
          {/if}
        </div>
      </div>
    </div>
    <!-- Ende -->

    <!--zweite Box mit Anzeige aller zugefügten Zutaten (wird dynamisch angezeigt, je nachdem ob CreateMenu oder MenuDetails)-->
    {#if visibilityGerichte}
      <div
        class="col-xl-6 cold-flex align-items-center"
        id="smaller-col"
        transition:fade
      >
        <div class="box">
          <div class="container" id="smallboi-top">
            <div>Bisher hinzugefügte Zutaten:</div>
            <br />

            <!--@Bewertende Person: Ich kann hier leider nicht mit der MongoDb Beziehung arbeiten, da es auch für noch nicht erstellte Menus funktionieren muss-->
            <ul>
              {#each menu.zutaten as zutat}
                {#each zutatenliste as liste}
                  {#if zutat === liste._id}
                    <li>
                      <div class="row">
                        <div class="col-10  align-items-center">
                          {liste.zutatName}
                        </div>
                        <div class="col-2  align-items-center">
                          <button
                            class="btn"
                            on:click={deleteSingleIngredient(zutat)}
                            id="hinzugefügteLöschen"><X /></button
                          >
                        </div>
                      </div>
                    </li>
                  {/if}
                {/each}
              {/each}
            </ul>
          </div>
        </div>
      </div>
    {/if}
  </div>
  <!--dritte Box UnsplashAPI (wird dynamisch angezeigt, je nachdem ob CreateMenu oder MenuDetails)-->
  <!--Hier wird über die Component Unsplash ein Bild hochgeladen(Effektiver Ablauf alles in SearchUnsplash)-->
  {#if visibilityGerichte}
    <div class="container" id="thirdcontainer" transition:fade>
      <div class="row" id="thirdrow">
        <div class="col-12">
          {#if !visibilityBild}
            <h2>Dein ausgewähltes Bild</h2>
          {:else}
            <h2>Wähle noch ein Bild aus</h2>
          {/if}
        </div>
        <div class="col-12">
          <Unsplash bind:ausgewähltesBild={menu.BildUrl} bind:visibilityBild />
        </div>
      </div>
    </div>
  {/if}

  <div class="row">
    <h2>{bottomTitle}</h2>
    <p>{bottomText}</p>
  </div>
</div>

<style>
  #component {
    display: flexbox;
  }
  h1 {
    margin-bottom: 25px;
  }

  h2 {
    margin-top: 45px;
  }

  p {
    margin-top: 5px;
    margin-left: 15px;
  }

  #hinzugefügteLöschen {
    max-width: fit-content;
    background-color: #dc3545;
    color: white;
  }

  #smaller-col {
    min-height: 400px;
    padding: 25px;
  }

  #inputname {
    min-width: 75px;
  }

  #Searchbar {
    margin-bottom: 20px;
  }

  .box {
    background-color: white;
    border-radius: 2%;
    padding: 25px;
    min-height: 400px;
  }

  .row {
    min-width: 100%;
  }

  .btn {
    background-color: whitesmoke;
    margin: 2px;
  }
</style>
