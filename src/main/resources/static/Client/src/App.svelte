<script>
  import Router from "svelte-spa-router";
  import routes from "./routes";
  import { Sprout } from "lucide-svelte";
  import { Hourglass } from "lucide-svelte";
</script>

<div class="all container">

  <nav class="navbar navbar-expand-lg flex-column">
    <div class="container-fluid">
      <button
        class="navbar-toggler"
        type="button"
        data-bs-toggle="collapse"
        data-bs-target="#navbarSupportedContent"
        aria-controls="navbarSupportedContent"
        aria-expanded="false"
        aria-label="Toggle navigation"
      >
        <span class="navbar-toggler-icon" />
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <li class="nav-item">
            <a class="nav-link active" aria-current="page" href="#/">Home</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#/overview">Overview</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#/menus">Menus</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#/createmenu"><Hourglass size="60px" /></a
            >
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#/zutaten">Tasks</a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="#/account">Profil</a>
          </li>
        </ul>
      </div>
    </div>
  </nav>

  <!-- Here we load the page depending on the current URL -->
  <Router {routes} />
</div>

<style>
  .all.container {
    margin-bottom: 150px;
  }

  .nav-item {
    margin-left: 50px;
  }

  a {
    color: black;
    font-style: bold;
    font-size: 25px;
  }
</style>
