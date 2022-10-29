<script>
  import axios from "axios";
  import { fade } from "svelte/transition";

  let query;
  let resultBilder = [];

  export let ausgewähltesBild = "";
  export let visibilityBild = true;

  checkIfAlreadyValue();

  async function searchPicture() {
    axios
      .get(
        "https://api.unsplash.com/search/photos/?client_id=AQgNqwoDtY8Murn43ZdkMPdsyCvs5GLzkgf0WhMlF2k&query=" +
          query
      )
      .then((response) => {
        resultBilder = response.data.results;
      });
  }

  function bildSpeichern(url) {
    ausgewähltesBild = url;

    query = "";
    searchPicture();
    visibilityBild = false;
  }

  function resetAll() {
    ausgewähltesBild = "";

    query = "";
    searchPicture();
    visibilityBild = true;
  }

  function checkIfAlreadyValue() {
    if (ausgewähltesBild != "") {
      visibilityBild = false;
    }
  }

  function getBackground(URL) {
    return "background-image: url('" + URL + "')";
  }
</script>

<!-- Searchbar-->
{#if visibilityBild}
  <div class="row" transition:fade>
    <div class="col-sm">
      <div class="input-group mb-3">
        <input
          type="text"
          class="form-control"
          placeholder="Bild suchen"
          aria-label="Bild suchen"
          aria-describedby="button-addon2"
          bind:value={query}
        />
        <button
          class="btn btn-light"
          type="button"
          id="button-addon2"
          on:click={searchPicture}>Search</button
        >
      </div>
    </div>
  </div>

  <!-- Anzeige der Bilder VERSCHWINDET NACH AUSWAHL-->
  <div class="row">
    {#each resultBilder as Bild}
      <div class="col-xl-3 d-flex justify-content-center">
        <div class="card">
          <div
            class="card-header card-picture"
            style={getBackground(Bild.urls.thumb)}
          />

          <div class="card-body">
            <h5 class="card-title">
              <button
                class="btn btn-success"
                id="btnpic"
                on:click={bildSpeichern(Bild.urls.thumb)}
                >Auswählen</button
              >
            </h5>
          </div>
        </div>
      </div>
    {/each}
  </div>
{/if}
<!-- Anzeige ausgewähltes Bild TAUCHT AUF NACH AUSWAHL-->

{#if !visibilityBild}
  <div class="row">
    <div class="col">
      <div class="card">
        <div
          class="card-header card-picture"
          style={getBackground(ausgewähltesBild)}
        />

        <div class="card-body">
          <h5 class="card-title">
            <button class="btn btn-danger" on:click={resetAll}
              >Zurücksetzen</button
            >
          </h5>
        </div>
      </div>
    </div>
  </div>
{/if}

<style>
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
    align-items: center;
  }

  .card-picture {
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
  }
</style>
