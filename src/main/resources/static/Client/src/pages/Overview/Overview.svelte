<script>
  import axios from "axios";
  import { onMount } from "svelte";

  export let params;

  let id;
  let visible = true;

  let status = "There are currently no entries, go work you wanker";

  let entries = [
    {
      startPoint: "",
      startDate: "",
      startTime: "",
      endPoint: "",
      comment: "",
      userId: "",
      bookingState: "",
      bookingType: "",
    },
  ];

  $: {
    id = params.id;
  }

  async function getEntriesByUser() {
    axios
      .get("http://localhost:8080/api/service/getAll/" + id)
      .then((response) => {
        //get the Entries
        entries = response.data;

        //Split them up by the delimineter T
        entries.forEach((entry) => {
          var stringTobeSplit = entry.startPoint.split("T");
          entry.startDate = stringTobeSplit[0];
          entry.startTime = stringTobeSplit[1];
        });
      });
  }

  onMount(getEntriesByUser);
</script>

<table class="table table-hover">
  <thead>
    <tr>
      <th scope="col">Date</th>
      <th scope="col">Start</th>
      <th scope="col">Comment</th>
      <th scope="col">Type</th>
      <th scope="col">State</th>
    </tr>
  </thead>
  <tbody>
    {#each entries as entry}
      <tr>
        <th scope="row">{entry.startDate}</th>
        <th scope="row">{entry.startTime}</th>
        <td>{entry.comment}</td>
        <td>{entry.bookingType}</td>
        <td>{entry.bookingState}</td>
      </tr>
    {/each}
  </tbody>
</table>

<style>
</style>
