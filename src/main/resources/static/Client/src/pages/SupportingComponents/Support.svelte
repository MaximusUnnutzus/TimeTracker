<script>
  import { createEventDispatcher } from "svelte";
  import { fade } from "svelte/transition";

  let visible = false;
  let visibleRick = false;
  let ausgewählteKategorie;
  let textEingabeUser = "";

  const dispatch = createEventDispatcher();
  export let isOpenModal;

  function closeModal() {
    isOpenModal = false;
    dispatch("closeModal", { isOpenModal });
  }

  function fadeInSuccess() {
    visible = true;
  }

  function notSoFastBuster() {
    visibleRick = true;
  }

  function resetAll() {
    visibleRick = false;
    visible = false;
    ausgewählteKategorie = "-- Kategorie auswählen --";
    textEingabeUser = "";
  }
</script>

<div id="bigboi">
  <div
    id="background"
    style="--display: {isOpenModal ? 'block' : 'none'};"
    on:click={closeModal}
    on:pointerleave={resetAll}
  />
  <div id="modal" style="--display: {isOpenModal ? 'block' : 'none'};">
    <form>
      <div class="mb-3">
        <label for="inputState">Position</label>
        <select
          id="inputState"
          class="form-select"
          bind:value={ausgewählteKategorie}
        >
          <option selected disabled>-- Kategorie auswählen --</option>
          <option>Unterstützung</option>
          <option>Zutat hinzufügen</option>
          <option>Vertragsänderungen</option>
          <option>Sonstige Unterstützung</option>
        </select>
      </div>
      <div class="mb-3">
        <label for="exampleInputPassword1" class="form-label"
          >Beschreibe Dein Problem</label
        >
        <textarea
          class="form-control"
          id="exampleFormControlTextarea1"
          rows="3"
          bind:value={textEingabeUser}
        />
      </div>

      {#if textEingabeUser != "" && ausgewählteKategorie != "-- Kategorie auswählen --"}
        <button
          type="submit"
          class="btn btn-success"
          on:click={fadeInSuccess}
          transition:fade>Submit</button
        >
      {/if}<br />

      {#if visible}
        <div>
          <p transition:fade>
            <br />Wir haben Deine Nachricht erhalten und melden uns
            schnellstmöglich bei Dir.<br />Üblicherweise dauert eine Antwort bis
            zu 2 Arbeitstage.
          </p>
          <button class="btn btn-danger" on:click={notSoFastBuster}
            >Ungeduldig? Klick hier, dann melden wir uns sofort</button
          >
          {#if visibleRick}
            <div class="container">
              <div transition:fade>
                <div
                  class="card card-picture"
                  style="background-image: url('https://c.tenor.com/x8v1oNUOmg4AAAAd/rickroll-roll.gif')"
                />
              </div>
            </div>
          {/if}
        </div>
      {/if}
    </form>
  </div>
</div>

<style>
  #background {
    background-color: rgb(255, 255, 255, 0.4);
  }

  .container {
    display: var(--display);
    position: fixed;
    z-index: 2;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background: black;
    filter: drop-shadow(0 0 20px #333);
    height: 500px;
    width: 700px;
    border-radius: 2%;
    padding: 15px;
  }

  p {
    color: green;
  }

  #background {
    display: var(--display);
    position: fixed;
    z-index: 1;
    top: 0;
    left: 0;
    width: 100vw;
    height: 100vh;
  }

  #modal {
    display: var(--display);
    position: fixed;
    z-index: 2;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background: #fff;
    filter: drop-shadow(0 0 20px #333);
    height: 500px;
    width: 700px;
    border-radius: 2%;
    padding: 15px;
  }

  .card {
    display: var(--display);
    position: fixed;
    z-index: 2;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background: #fff;
    filter: drop-shadow(0 0 20px #333);
    height: 500px;
    width: 700px;
    border-radius: 2%;
    padding: 15px;
  }

  .card-picture {
    background-position: center;
    background-repeat: no-repeat;
    background-size: cover;
  }
</style>
