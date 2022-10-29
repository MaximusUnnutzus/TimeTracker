<script>
  import Support from "../SupportingComponents/Support.svelte";
  import { Lock } from "lucide-svelte";

  let isOpenModal = false;

  function openModal() {
    isOpenModal = true;
  }

  function closeModal() {
    isOpenModal = false;
  }
</script>

<div class="row align-items-start" id="secondrow">
  <div class="col-sm-8 cold-flex align-items-center" id="smaller-col">
    <div class="box">
      <h1>Persönliche Daten <Lock /></h1>
      <p>
        <br />Hier hast Du eine Übersicht über Deine Daten.<br
        />Namensänderungen oder sonstige Änderungen kannst Du dem Support
        melden.<br /><br />
      </p>
      <form>
        <div class="form-group row">
          <label for="staticEmail" class="col-sm-2 col-form-label"
            >Restaurant</label
          >
          <div class="col-sm-7">
            <input
              type="text"
              readonly
              class="form-control"
              id="Name"
              value="Mos Eisley Cantina"
            />
          </div>
        </div>
        <div class="form-group row">
          <label for="inputPassword" class="col-sm-2 col-form-label"
            >Strasse</label
          >
          <div class="col-sm-7">
            <input
              type="text"
              readonly
              class="form-control"
              id="Street"
              value="HighRoadStreet 66"
            />
          </div>
        </div>
        <div class="form-group row">
          <label for="inputPassword" class="col-sm-2 col-form-label">PLZ</label>
          <div class="col-sm-2">
            <input
              type="text"
              readonly
              class="form-control"
              id="PLZ"
              value="5431"
            />
          </div>
          <label for="inputPassword" class="col-sm-1 col-form-label">Land</label
          >
          <div class="col-sm-3">
            <input
              type="text"
              readonly
              class="form-control"
              id="Land"
              value="Tattooine"
            />
          </div>
        </div>

        <div class="form-group row">
          <label for="inputPassword" class="col-sm-2 col-form-label"
            >Vertragsstart</label
          >
          <div class="col-sm-2">
            <input
              type="text"
              readonly
              class="form-control"
              id="SDate"
              value="14.05.2022"
            />
          </div>
          <label for="inputPassword" class="col-sm-2 col-form-label"
            >Vertragsende</label
          >
          <div class="col-sm-2">
            <input
              type="text"
              readonly
              class="form-control"
              id="Land"
              value="14.05.2024"
            />
          </div>
        </div>
        <div class="form-group row">
          <label for="staticEmail" class="col-sm-2 col-form-label">Mail</label>
          <div class="col-sm-7">
            <input
              type="text"
              readonly
              class="form-control"
              id="Name"
              value="nagato.uzumaki@konoha.ch"
            />
          </div>
        </div>
        <div class="form-group row">
          <label for="inputPassword" class="col-sm-2 col-form-label"
            >Telefonnummer</label
          >
          <div class="col-sm-7">
            <input
              type="text"
              readonly
              class="form-control"
              id="Street"
              value="+41 79 456 32"
            />
          </div>
        </div>
      </form>
      <br />
      <button class="btn" on:click={openModal}>Support kontaktieren</button>
    </div>
  </div>
</div>

<Support {isOpenModal} on:closeModal={closeModal} />

<style>
  .btn {
    border-color: aqua;
    border-style: solid;
    background-color: aqua;
    border-radius: 2%;
  }

  label {
    margin: 5px;
  }

  input {
    margin: 5px;
  }

  .box {
    background-color: white;
    border-radius: 2%;
    padding: 25px;
    min-height: 400px;
    margin: 15px;
  }

  .btn {
    border-radius: 5%;
  }
</style>
