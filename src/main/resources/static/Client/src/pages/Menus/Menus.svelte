<script>
  import axios from "axios";
  import { PlusCircle } from "lucide-svelte";
  import { onMount } from "svelte";

  let message;

  let menus = [
    {
      gerichtName: "",
      Preis: "",
      zutaten: [],
      BildUrl: "",
      BildAlt: "",
    },
  ];

  async function getMenus() {
    axios.get("http://localhost:3001/api/menus").then((response) => {
      menus = response.data;
      if (menus.length >= 1) {
        message = "";
      } else {
        message =
          "Es ist so leer hier....\nFüge doch ein neues Gericht hinzu:)";
      }
    });
    console.log("im done");
  }

  async function deleteMenu(menu) {
    deleteSingleGerichtFromIngredient(menu);
    axios.delete("http://localhost:3001/api/menus/" + menu._id).then(() => {
      getMenus();
    });
  }

  function getBackground(URL) {
    return "background-image: url('" + URL + "')";
  }

  //Delete gerichtName out of every Zutat if Menu is deleted
  async function deleteSingleGerichtFromIngredient(menu) {
    menu.zutaten.forEach((eineZutatAusMenu) => {
      let einzelneZutat = {
        _id: "",
        zutatName: "",
        inGerichten: [],
      };
      axios
        .get("http://localhost:3001/api/zutaten/" + eineZutatAusMenu)
        .then((response) => {
          einzelneZutat = response.data;
          if (!einzelneZutat.inGerichten) {
            einzelneZutat.inGerichten = [];
          }
          if (einzelneZutat.inGerichten.includes(menu._id)) {
            //Array Filtern und alle die nicht = menu._id sind in inGerichten
            einzelneZutat.inGerichten = einzelneZutat.inGerichten.filter(
              (item) => {
                return item != menu._id;
              }
            );
          } else {
            console.log("blocked");
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

  onMount(getMenus);
</script>

<!--Menus aus DB hinzufügen-->

<h1>Deine Gerichte</h1>
<p>{message}</p>
<div class="row">
  {#each menus as menu}
    <div class="card" style="width: 18rem;">
      <div
        class="card-header card-picture"
        style={getBackground(menu.BildUrl)}
      />

      <div class="card-body">
        <div class="row">
          <div class="col d-flex justify-content-center">
            <h5 class="card-title">{menu.gerichtName}</h5>
          </div>
          <div class="col d-flex justify-content-center">
            <h5 class="card-title">CHF {menu.Preis}</h5>
          </div>
        </div>

        <div class="row">
          <div class="col d-flex justify-content-center">
            <a
              type="submit"
              class="btn"
              href={"#/menus/" + menu._id}
              id="editbutton">Edit</a
            >
          </div>
          <div class="col d-flex justify-content-center">
            <button
              type="button"
              class="btn btn-danger"
              id="deletebutton"
              on:click={deleteMenu(menu)}>delete</button
            >
          </div>
        </div>
      </div>
    </div>
  {/each}

  <!--Neues Gericht hinzufügen-->
  <div class="card">
    <div class="card-body" id="plussign">
      <a class="btn" id="menuhinzufügen" href={"#/createmenu"}
        ><PlusCircle color="#696969" size="120" /></a
      >
    </div>
  </div>
</div>

<style>
  #editbutton {
    background-color: #66cdaa;
  }
  #deletebutton {
    border-color: #696969;
  }
  .card {
    height: 300px;
    width: 250px;
    margin: 15px;
    padding-left: 0%;
    padding-right: 0%;
    border-radius: 2%;
  }

  .card-header {
    height: 80%;
  }
  .card-body {
    font-size: 24px;
    font-style: oblique;
    padding: 10px;
    display: flex;
    justify-content: center;
    flex-direction: column;
  }

  .card-picture {
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
  }
</style>
