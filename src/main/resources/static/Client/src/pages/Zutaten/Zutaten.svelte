<script>
  import axios from "axios";

  let zutatenliste = [];

  async function getZutaten() {
    axios.get("http://localhost:3001/api/zutaten").then((response) => {
      zutatenliste = response.data;
    });
  }

  getZutaten();

  let header;
  let collapse;
  let collapseH;
  let count = 1;

  function getHeader() {
    header = "heading" + count;
    return header;
  }

  function getCollapse() {
    collapse = "collapse" + count;
    return collapse;
  }

  function getCollapseH() {
    collapseH = "#collapse" + count;
    return collapseH;
  }

  function addOneToCount() {
    count++;
  }

  getCollapseH();
</script>

<h1>Zutaten</h1>
<p>Hier siehst Du, welche Zutaten Du in welchen Gerichten verwendest.</p>

<div class="accordion" id="accordionExample">
  {#each zutatenliste as zutat}
    {#if zutat.inGerichten}
      {#if zutat.inGerichten.length}
        <div class="accordion-item">
          <h2 class="accordion-header" id={getHeader()}>
            <button
              class="accordion-button collapsed"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target={getCollapseH()}
              aria-expanded="false"
              aria-controls={getCollapse()}
            >
              <div>
                {zutat.zutatName}
                <p2
                  >(wird in {zutat.inGerichten.length} Gericht(en) verwendet)</p2
                >
              </div>
            </button>
          </h2>
          <div
            id={getCollapse()}
            class="accordion-collapse collapse"
            aria-labelledby="{getHeader()}{addOneToCount()}"
            data-bs-parent="#accordionExample"
          >
            <div class="accordion-body">
              <ul>
                {#each zutat.inGerichten as Gericht}
                  <li>{Gericht.gerichtName}</li>
                {/each}
              </ul>
            </div>
          </div>
        </div>
      {/if}
    {/if}
  {/each}
</div>

<style>
  p2 {
    font-style: oblique;
    font-size: 11px;
  }
  .accordion {
    border-radius: 2%;
  }
</style>
